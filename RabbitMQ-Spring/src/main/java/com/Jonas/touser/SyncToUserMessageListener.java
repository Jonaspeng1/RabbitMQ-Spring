/**
 * Copyright 1993-2013 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.Jonas.touser;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.Jonas.common.AbstractAMQPMessageBean;
import com.Jonas.common.JsonUtils;
import com.Jonas.common.SyncConsumeService;
import com.alibaba.fastjson.JSONObject;



public class SyncToUserMessageListener extends AbstractAMQPMessageBean implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(SyncToUserMessageListener.class);
    
    @Autowired
    private SyncToUserConsumerBuilder syncToUserConsumerBuilder;
    /*
     * (non-Javadoc)
     * 
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void onMessage(final Message message) {
        try {
            String body = new String(message.getBody(), "UTF-8");
            try {
            	// logger.info("Process SyncTobehavioranalysisMessageListener message:"+body);
                 JSONObject json = JsonUtils.stringToJson(body);
                 String consumeType = json.getString("syncType");
                 SyncConsumeService syncConsumeService = this.syncToUserConsumerBuilder.getFactory(consumeType);
                 if(syncConsumeService==null){
                	 logger.error("Process syncToUserConsumerBuilder error syncConsumeService is null consumeType:"+consumeType);
                 }
                 syncConsumeService.process(body);
            } catch (Throwable t) {
                logger.error("Process syncToUserConsumerBuilder [" + body + "] failed, cause by " + t.getMessage(), t);
            }
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage(), e1);
        }
    }
}
