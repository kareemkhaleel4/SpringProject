package com.sprint.test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.messaging.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.rometools.rome.feed.synd.SyndEntry;

import org.springframework.integration.feed.dsl.Feed;
import org.springframework.integration.metadata.MetadataStore;
import org.springframework.integration.metadata.PropertiesPersistingMetadataStore;
import org.springframework.integration.metadata.SimpleMetadataStore;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	//@Bean
	//public MessageSource<?> methodInvokingMessageSource() {
	//	MethodInvokingMessageSource source = new MethodInvokingMessageSource();
		
		//System.out.println("\nEnter the directory name, Press enter to use the default name<News>: ");
		//String dir = new Scanner(System.in).nextLine();
		//if (dir.equals("")) {
		//	dir = "News";
		//}
		//source.setObject(new Exec(dir));
		//source.setMethodName("startRssReader");
		//return source;
//	}
	@Bean
	public DirectChannel inputChannel() {
		return new DirectChannel();
	}
	@Value("https://www.aljazeera.net/aljazeerarss/a7c186be-1baa-4bd4-9d80-a84db769f779/73d0e1b4-532f-45ef-b135-bfdff8b8cab9")
	private URL feedResource;
   
    @Bean
    public MetadataStore metadataStore() {
    	SimpleMetadataStore metadataStore = new SimpleMetadataStore ();
      //  new File("News").mkdirs();
        //metadataStore.setBaseDirectory("News");
        return metadataStore;
    }
    @Splitter
    public ArrayList<?> split(Message<?> message){
    	SyndEntry payload = (SyndEntry) message.getPayload();
    	System.out.println(payload.getUri()+ ":");
    	System.out.println("\t" + chage_date_to_str(payload.getPublishedDate().toString()) + " - " + payload.getCategories().get(0).getName());
    	return null;
    	
    }
	@Bean
	public IntegrationFlow myFlow() {

		return IntegrationFlows.from(( Feed.inboundAdapter(this.feedResource, "feedTest"))
				//.metadataStore(metadataStore())
				
				,e -> e.poller(p -> p.fixedDelay(0)))
				.split(this)
				.channel(c -> c.executor(Executors.newFixedThreadPool(1)))
				//.handle(new RssHandler())
				.get();
	}
	private String chage_date_to_str(String dateString){
    	String[] dateStringList = dateString.split(" ");
    	String date = ""+dateStringList[dateStringList.length - 1 ] + "-" + convert_month_name_to_num(dateStringList[1]) + "-" + dateStringList[2];
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
