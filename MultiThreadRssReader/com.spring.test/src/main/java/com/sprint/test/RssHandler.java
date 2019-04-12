package com.sprint.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.sprint.test.models.FeedMessage;

public class RssHandler implements MessageHandler {

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		// TODO Auto-generated method stub
		String dirLink = (String) message.getHeaders().get("dirLink");
		FeedMessage fm = (FeedMessage) message.getPayload();
		
		Path p = Paths.get(dirLink);
		if (!Files.exists(p)) {
			new File(dirLink).mkdirs();
		}
		String dateDirLink = dirLink + "\\" + fm.getPubdate();
		p = Paths.get(dateDirLink);
		if (!Files.exists(p)) {
			new File(dateDirLink).mkdir();
		}
		String catDirLing =dateDirLink + "\\" + fm.getCategory();
		p = Paths.get(catDirLing);
		if (!Files.exists(p)) {
			new File(catDirLing).mkdir();
		}
		try {
			new File(catDirLing + "\\" +fm.getGuid() + ".xml").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
