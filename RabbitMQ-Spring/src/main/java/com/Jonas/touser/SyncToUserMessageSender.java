/**
 * Copyright 1993-2013 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.Jonas.touser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.Jonas.common.AbstractAMQPMessageBean;
import com.Jonas.common.JsonUtils;


public  class SyncToUserMessageSender extends AbstractAMQPMessageBean {
    
    private static final Logger logger = LoggerFactory.getLogger(SyncToUserMessageSender.class);
          
    private RabbitTemplate rabbitTemplate;
    private String exchangeName;
    private String routingKey;

 
    public SyncToUserMessageSender(String exhangeName, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exhangeName;
        this.routingKey = "syncToUserMessage";
    }

    public void send(final Object message) {
    	
        try {
            MessageProperties mp = new MessageProperties();
            mp.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            mp.setContentEncoding("UTF-8");
            String data = JsonUtils.toJSONString(message);
            this.rabbitTemplate.send(this.exchangeName, routingKey,
                    new Message(data.getBytes("UTF-8"), mp));
            logger.info("syncTobehavioranalysisMessage  send 最终send给讯通:"+data);
        } catch (AmqpException e) {
        	logger.error("[SyncMessageSender ] send message failed.", e);
        } catch (Exception e) {
            logger.error("[SyncMessageSender] send message failed.", e);
        }
    }

}
