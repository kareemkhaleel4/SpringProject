package de.vogella.rss.read;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import de.vogella.rss.model.Feed;
import de.vogella.rss.model.FeedMessage;

public class ReadTest {
	static Map<String, ArrayList<FeedMessage>> ma = new HashMap <>();
	//static Map<String, ArrayList<ArrayList<String>>>
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "https://www.aljazeera.net/aljazeerarss/a7c186be-1baa-4bd4-9d80-a84db769f779/73d0e1b4-532f-45ef-b135-bfdff8b8cab9");
        Feed feed = parser.readFeed();

        ArrayList<String> d = new ArrayList<>();
        ArrayList<String> categoryArrayList = new ArrayList<>();
        Map<String, Map> dateToMap = new HashMap<String, Map>();
        for (FeedMessage message : feed.getMessages()) {
        	String pubDate = message.getPubdate();
        	String category = message.getCategory();
        	if (!d.contains(chage_date_to_str(pubDate)))
            	d.add(chage_date_to_str(pubDate));

        	if(!categoryArrayList.contains(category))
        		categoryArrayList.add(category);
        }
        for (String s : d) {
        	ArrayList<FeedMessage> fm = new ArrayList<FeedMessage>();
        	for (FeedMessage message : feed.getMessages()) {
        		if (chage_date_to_str(message.getPubdate()).equals(s)) {
        			fm.add(message);
        		}
        	}
        	ma.put(s, fm);
        }
        for (String key : ma.keySet()) {
        	Map<String, ArrayList<String>> catToGuidMap = new HashMap<>();
        	for (String cat : categoryArrayList) {
        		ArrayList<String> tempGuids = new ArrayList<String>();
        		for (FeedMessage m : ma.get(key)) {
        			if(m.getCategory().equals(cat)) {
        				tempGuids.add(m.getGuid());
        			}
        		}
        		if (tempGuids.size() != 0)
        			catToGuidMap.put(cat, tempGuids);
        	}
        	dateToMap.put(key, catToGuidMap);
        }
        
        for(String key: dateToMap.keySet()) {
        	System.out.println(key);
        	Map<String, ArrayList<String>> catToGuidMap = dateToMap.get(key);
        	for (String key2: catToGuidMap.keySet()) {
        		System.out.println("\t *"+key2);
        		for(String guid: catToGuidMap.get(key2)) {
        			System.out.println("\t\t -"+ guid +".xml");
        		}
        	}
        }


    }
    static String chage_date_to_str(String dateString){
    	String[] dateStringList = dateString.split(" ");
    	String date = ""+dateStringList[3] + "-" + convert_month_name_to_num(dateStringList[2]) + "-" + dateStringList[1];
    	return date;
    }
 
    static String convert_month_name_to_num(String mName) {
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