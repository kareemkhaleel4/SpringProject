package com.sprint.test;

import java.util.Scanner;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public MessageSource<?> methodInvokingMessageSource() {
		MethodInvokingMessageSource source = new MethodInvokingMessageSource();
		
		System.out.println("\nEnter the directory name, Press enter to use the default name<News>: ");
		String dir = new Scanner(System.in).nextLine();
		if (dir.equals("")) {
			dir = "News";
		}
		source.setObject(new Exec(dir));
		source.setMethodName("startRssReader");
		return source;
	}
	@Bean
	public DirectChannel inputChannel() {
		return new DirectChannel();
	}

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {

        PollerMetadata pollerMetadata = new PollerMetadata();
        //Will check the file every 15 seconds.
        pollerMetadata.setTrigger(new PeriodicTrigger(15000));
        return pollerMetadata;
    }

	@Bean
	public IntegrationFlow myFlow() {

		return IntegrationFlows.from(methodInvokingMessageSource())
				.split(new MessageSplitter())
				.channel(c -> c.executor(Executors.newFixedThreadPool(5)))
				.handle(new RssHandler())
				.get();
	}

}
