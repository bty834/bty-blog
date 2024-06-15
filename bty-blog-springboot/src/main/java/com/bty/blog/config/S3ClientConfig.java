package com.bty.blog.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bty
 * @date 2023/1/21
 * @since 1.8
 **/
@Configuration
@EnableConfigurationProperties(S3ServerProperties.class)
public class S3ClientConfig {

    @Bean
    public AmazonS3 amazonS3(S3ServerProperties p){
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(p.getKey(),p.getSecret())))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(p.getEndpoint(), p.getRegion()));
        return builder.build();
    }
}
