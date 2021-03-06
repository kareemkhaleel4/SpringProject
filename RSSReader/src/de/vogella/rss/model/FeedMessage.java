package de.vogella.rss.model;

/*
 * Represents one RSS message
 */
public class FeedMessage {

    String title;
    String description;
    String link;
    String author;
    String guid;
    String category;
    String pubdate;

    public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "FeedMessage [category" + category + ", title=" + title + ", description=" + description
                + ", link=" + link + ", author=" + author + ", guid=" + guid
                + ", category" + category +"]";
    }

}