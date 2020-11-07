package com.ron99.ReactiveExperiments.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsSqsConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.access-key}")
//    @Value("access.key")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
//    @Value("secret.key")
    private String secretKey;

    @Bean
    @Primary
    public QueueMessagingTemplate queueMessagingTemplate(@Autowired AmazonSQSAsync amazonSQSAsync){
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(@Autowired AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainerFactory msgListenerContainerFactory = new SimpleMessageListenerContainerFactory();
        msgListenerContainerFactory.setAmazonSqs(amazonSQSAsync);
        return msgListenerContainerFactory;
    }

    @Bean
    @Primary
    public AmazonSQSAsync getAmazonSqsAsync(){
        return AmazonSQSAsyncClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

}
