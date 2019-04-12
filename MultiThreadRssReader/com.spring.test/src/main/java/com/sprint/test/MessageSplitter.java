package com.sprint.test;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import com.sprint.test.models.Feed;
import com.sprint.test.models.FeedMessage;
import com.sprint.test.models.FeedParser;

public class MessageSplitter extends AbstractMessageSplitter{

	@Override
	protected Object splitMessage(Message<?> message) {
		FeedParser fp = (FeedParser) message.getPayload();
		Feed feed = fp.readFeed();
		String dirLink = (String) message.getHeaders().get("dirLink");
		ArrayList<Message<?>> messages = new ArrayList<Message<?>>();
		for (FeedMessage fm : feed.getEntries()) {
			Message<?> me = MessageBuilder.withPayload(fm).setHeader("dirLink", dirLink).build();
			messages.add(me);
		}
		
		return messages;
	}
	
	HashMap<String, HashMap<String, ArrayList<FeedMessage>>> changeDataFormat(ArrayList<FeedMessage> feedMessages) {
		ArrayList<String> dates = new ArrayList<String>();
		ArrayList<String> catagories = new ArrayList<String>();
		for (FeedMessage fm: feedMessages) {
			//System.out.println( fm.getPubdate());
			if (!dates.contains(fm.getPubdate())) {
				dates.add(fm.getPubdate());
			}
			if (!catagories.contains(fm.getCategory())) {
				catagories.add(fm.getCategory());
			}
		}
		HashMap<String, ArrayList<FeedMessage>> messagesByDate = new HashMap<String, ArrayList<FeedMessage>>();
		for (String date : dates) {
			ArrayList<FeedMessage> arrFM = new ArrayList<FeedMessage>();
			for (FeedMessage fm: feedMessages) {
				if (!date.equals(fm.getPubdate()))
					continue;
				arrFM.add(fm);
			}
			messagesByDate.put(date, arrFM);
		}
		HashMap<String, ArrayList<FeedMessage>> catToMessage = new HashMap<String, ArrayList<FeedMessage>>();
		HashMap<String, HashMap<String, ArrayList<FeedMessage>>> dateToCat = new HashMap<String, HashMap<String,ArrayList<FeedMessage>>>();
		for (String key: messagesByDate.keySet()) {
			for (String cat : catagories) {
				ArrayList<FeedMessage> catFM = new ArrayList<FeedMessage>();
				for (FeedMessage fm : messagesByDate.get(key)) {
					if (!cat.equals(fm.getCategory()))
						continue;
					catFM.add(fm);
				}
				if (!catFM.isEmpty())
					catToMessage.put(cat, catFM);
			}

			dateToCat.put(key, catToMessage);
		}
		return dateToCat;
	}

}
