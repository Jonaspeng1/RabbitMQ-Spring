/**
 * Copyright 1993-2014 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



package com.Jonas.touser;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import com.Jonas.common.SyncConsumeService;




@Component
public class SyncToUserConsumerBuilder implements ApplicationContextAware, SmartLifecycle{


    private HashMap<String, SyncConsumeService> factories = new HashMap<String, SyncConsumeService>();
    private volatile ApplicationContext applicationContext;
    private volatile boolean running;

    /**
     * @param type
     * @return
     */
    public SyncConsumeService getFactory(String type) {
        return factories.get(type);
    }

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;        
    }

    /* (non-Javadoc)
     * @see org.springframework.context.Lifecycle#start()
     */
    public void start() {
        synchronized (this) {
            if (this.running) {
                return;
            }
            Collection<SyncConsumeService> factoryList= this.applicationContext.getBeansOfType(SyncConsumeService.class).values();
            for (SyncConsumeService factory : factoryList) {
                factories.put(factory.getType(), factory);
            }
            this.running = true;
        }        
    }

    /* (non-Javadoc)
     * @see org.springframework.context.Lifecycle#stop()
     */
    public void stop() {
    }

    /* (non-Javadoc)
     * @see org.springframework.context.Lifecycle#isRunning()
     */
    public boolean isRunning() {
        return running;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.Phased#getPhase()
     */
    public int getPhase() {
        return Integer.MIN_VALUE;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.SmartLifecycle#isAutoStartup()
     */
    public boolean isAutoStartup() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.SmartLifecycle#stop(java.lang.Runnable)
     */
    public void stop(Runnable callback) {
        
    }

    public HashMap<String, SyncConsumeService> getFactories() {
        return factories;
    }
}
