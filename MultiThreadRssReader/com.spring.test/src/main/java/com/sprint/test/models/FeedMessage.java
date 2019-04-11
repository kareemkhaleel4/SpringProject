package com.sprint.test.models;


public class FeedMessage {
	private String title;
	private String category;
	private String pubdate;
	private String guid;
	private String description;

	public FeedMessage(String title, String category, String pubdate, String guid, String description) {
		super();
		this.title = title;
		this.category = category;
		this.pubdate = pubdate;
		this.guid = guid;
		this.description = description;
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

	@Override
	public String toString() {
		return "feedMessage [title=" + title + ", category=" + category + ", pubdate=" + pubdate + ", guid=" + guid
				+ ", description=" + description + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}