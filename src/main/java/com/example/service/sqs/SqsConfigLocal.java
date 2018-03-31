package com.example.service.sqs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class SqsConfigLocal {

    @Value("${cloud.aws.region.static}")
    String awsRegion;

    @Value("${localstack.sqs.url}")
    String localStackSqsUrl;

    @Bean
    public AmazonSQSAsync amazonSqs() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(localStackSqsUrl, awsRegion))
                .build();
    }

}
