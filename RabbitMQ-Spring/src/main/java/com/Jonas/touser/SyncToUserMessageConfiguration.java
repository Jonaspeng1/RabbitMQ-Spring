/**
 * Copyright 1993-2013 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.Jonas.touser;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import com.Jonas.common.AbstractAMQPConfiguration;


@Configuration
public class SyncToUserMessageConfiguration extends AbstractAMQPConfiguration  {
    private static final Logger logger = LoggerFactory.getLogger(SyncToUserMessageConfiguration.class);

    private static String MESSAGE_SEND_QUEUE_NAME = "syncToUserMessageMQ";
    private static String MESSAGE_SEND_EXCHANGE_NAME = "syncToUserMessageExchange";
    private static String UUID_SYNC = "";
    
    
    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    static {
        try {
        	UUID_SYNC = "0003e2ad-b3fb-47d6-bab6-1375166eb942123";
            MESSAGE_SEND_QUEUE_NAME += "_" + UUID_SYNC;
            MESSAGE_SEND_EXCHANGE_NAME += "_"
                    + System.getProperty("syncMessageExchange", UUID_SYNC);
            
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Override
    @Bean
    public RabbitTemplate rabbitTemplate () {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        return template;
    }

    @Bean
    public SyncToUserMessageSender syncTobehavioranalysisMessageClient() {
        return new SyncToUserMessageSender(MESSAGE_SEND_EXCHANGE_NAME, rabbitTemplate());
    }

  
    @Bean
    public TopicExchange syncToUserMessageExchange() {
        return new TopicExchange(MESSAGE_SEND_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue syncToUserMessageRequestQueue() {
        Queue queue = new Queue(MESSAGE_SEND_QUEUE_NAME, true, false, false);
        return queue;
    }

    @Bean
    public Binding syncUserMessageBind() {
        return BindingBuilder.bind(syncToUserMessageRequestQueue()).to(syncToUserMessageExchange()).with("syncToUserMessage");
    }

    @Bean
    public MessageListener syncToUserMessageConsumerListener() {
        return new SyncToUserMessageListener();
    }
    
    @Bean
    public Executor syncToUserMessageConsumerTaskExecutor(){
    	return new SyncTaskExecutor();
    }
    
}
