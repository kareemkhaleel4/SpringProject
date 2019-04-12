package com.sprint.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sprint.test.models.FeedParser;

public class Exec{

	HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> myMap = new HashMap<>();
	final static String strUrl = "https://www.aljazeera.net/aljazeerarss/a7c186be-1baa-4bd4-9d80-a84db769f779/73d0e1b4-532f-45ef-b135-bfdff8b8cab9";
	private String dirLink;

	public Exec(String dirLink) {
		this.dirLink = dirLink;
		new File(dirLink).mkdirs();
	}

	public  Object startRssReader() {
		FeedParser fp = new FeedParser(strUrl);	
		Message<?> me = MessageBuilder.withPayload(fp).setHeader("dirLink", dirLink).build();
	return me;
	}
	
}