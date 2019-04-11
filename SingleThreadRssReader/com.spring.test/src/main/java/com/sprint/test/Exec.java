package com.sprint.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sprint.test.models.Feed;
import com.sprint.test.models.FeedMessage;
import com.sprint.test.models.FeedParser;

public class Exec{

	HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> myMap = new HashMap<>();
	final static String strUrl = "https://www.aljazeera.net/aljazeerarss/a7c186be-1baa-4bd4-9d80-a84db769f779/73d0e1b4-532f-45ef-b135-bfdff8b8cab9";
	private String dirLink;
	
	public Exec(String dirLink) {
		this.dirLink = dirLink;
		new File(dirLink).mkdirs();
	}

	public  String startRssReader() {
		FeedParser fp = new FeedParser(strUrl);
		Feed feed = fp.readFeed();
		ExecutorService executor = Executors.newFixedThreadPool(5);
		HashMap<String, HashMap<String, ArrayList<FeedMessage>>> messagesByDate = changeDataFormat(feed.getEntries());
		
		for (String key : messagesByDate.keySet()) {
			for (String cat: messagesByDate.get(key).keySet()) {
				for(FeedMessage fm : messagesByDate.get(key).get(cat)) {
					Runnable worker = new SimpleThreadWorker(dirLink+"\\"+key, cat, fm.getGuid());
					executor.execute(worker);
				}
			}
		}
		executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
 
		System.out.println("doSomthing elese");
	return "Success";
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