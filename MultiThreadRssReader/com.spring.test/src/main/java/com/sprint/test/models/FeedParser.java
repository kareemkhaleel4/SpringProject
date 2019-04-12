package com.sprint.test.models;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class FeedParser {
	final static String TITLE = "title";
	final static String CATEGORY = "category";
	final static String PUBDATE = "pubDate";
	final static String GUID = "guid";
	final static String DESCRIPTION = "description";
	final static String ITEM = "item";
	final URL url;
	public FeedParser(String url) {
		try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
	}
	private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
	            throws XMLStreamException {
	        String result = "";
	        event = eventReader.nextEvent();
	        if (event instanceof Characters) {
	            result = event.asCharacters().getData();
	        }
	        return result;
	    }
	public Feed readFeed(){
		Feed tempFeed = null;
		try {
			boolean isFeedHeader = true;
			boolean isStartItem = false;
			String title= "";
			String category = "";
			String pubdate = "";
			String guid = "";
			String description = "";
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart() == ITEM) {
                		isStartItem = true;
                	}
				}
				if (event.isStartElement() && isStartItem == true) {
					String localPart = event.asStartElement()
							.getName().getLocalPart();
					switch (localPart) {
					case ITEM:
						if (isFeedHeader) {
                            isFeedHeader = false;
                            tempFeed = new Feed(title, category, pubdate, guid, description);
                        }
                        event = eventReader.nextEvent();
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case CATEGORY:
						category = getCharacterData(event, eventReader);
						break;
					case PUBDATE:
						pubdate = chage_date_to_str(getCharacterData(event, eventReader));
						break;
					case GUID:
						guid = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					default:
						break;
					}
				} else if (event.isEndElement()) {
					 if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						 FeedMessage message = new FeedMessage(title.trim(), category.trim(), pubdate.trim(), guid.trim(), description.trim());
						 tempFeed.getEntries().add(message);
						 isStartItem = false;
						 continue;
					 }
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return tempFeed;
	}
	private String chage_date_to_str(String dateString){
    	String[] dateStringList = dateString.split(" ");
    	String date = ""+dateStringList[3] + "-" + convert_month_name_to_num(dateStringList[2]) + "-" + dateStringList[1];
    	return date;
    }
 
    private String convert_month_name_to_num(String mName) {
    	switch(mName.toLowerCase()) {
	    	case "january":
	        case "jan":
	            mName = "01";
	        break;
	        case "febuary":
	        case "feb":
	            mName = "02";
	        break;
	        case "march":
	        case "mar":
	            mName = "03";
	        break;
	        case "april":
	        case "apr":
	            mName = "04";
	        break;
	        case "may":
	            mName = "05";
	        break;
	        case "june":
	        case "jun":
	            mName = "06";
	        break;
	        case "july":
	        case "jul":
	            mName = "07";
	        break;
	        case "august":
	        case "aug":
	            mName = "08";
	        break;
	        case "september":
	        case "sep":
	        case "sept":
	            mName = "09";
	        break;
	        case "october":
	        case "oct":
	            mName = "10";
	        break;
	        case "november":
	        case "nov":
	            mName = "11";
	        break;
	        case "december":
	        case "dec":
	            mName = "12";
	        break;
        }
    	return mName;
    }
}
