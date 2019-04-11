package com.sprint.test.models;

import java.util.ArrayList;

public class Feed {
	public int getNumOfGuids() {
		return numOfGuids;
	}
	private String title;
	private String category;
	private String pubdate;
	private String guid;
	private String description;
	private int numOfGuids = 0;
	final ArrayList<FeedMessage> entries = new ArrayList<FeedMessage>();
	public Feed(String title, String category, String pubdate, String guid, String description) {
		super();
		this.title = title;
		this.category = category;
		this.pubdate = pubdate;
		this.guid = guid;
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public ArrayList<FeedMessage> getEntries() {
		numOfGuids = entries.size();
		return entries;
	}
	public Feed() {
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getDescpetion() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void clear() {
		this.title = "";
		this.category = "";
		this.pubdate = "";
		this.guid = "";
		this.description = "";
	}
	@Override
	public String toString() {
		return "feed [title=" + title + ", category=" + category + ", pubdate=" + pubdate + ", guid=" + guid
				+ ", description=" + description + "]";
	}
}