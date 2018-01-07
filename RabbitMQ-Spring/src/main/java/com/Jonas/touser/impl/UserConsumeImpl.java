package com.Jonas.touser.impl;

import org.springframework.stereotype.Component;
import com.Jonas.common.SyncConsumeService;
import com.Jonas.touser.ToUserConsumerType;


@Component
public class UserConsumeImpl implements SyncConsumeService{

	public static final String CONSUMER_TYPE = ToUserConsumerType.TOUSER.name();
	
	public String getType() {
		return CONSUMER_TYPE;
	}

	public void process(String message) {
		System.out.println("consume--------------");
		System.out.println(message);
	}

}
