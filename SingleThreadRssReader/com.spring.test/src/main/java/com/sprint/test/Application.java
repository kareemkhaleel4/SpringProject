package com.sprint.test;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
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
		
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter the directory name: ");
		String dir =scan.nextLine();
		

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
        pollerMetadata.setTrigger(new PeriodicTrigger(15000));
        return pollerMetadata;
    }

	@Bean
	public IntegrationFlow myFlow() {
		return IntegrationFlows.from(this.methodInvokingMessageSource())
				.channel(this.inputChannel())
				.channel(MessageChannels.queue())
				.get();
	}

}
