package com.ron99.ReactiveExperiments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
public class ReactiveExperimentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveExperimentsApplication.class, args);
	}

}
