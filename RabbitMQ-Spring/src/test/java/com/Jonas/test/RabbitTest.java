package com.Jonas.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.Jonas.config.SpringConfig;
import com.Jonas.touser.SyncToUserMessageSender;
import com.Jonas.touser.ToUserConsumerType;
import com.Jonas.vo.UserVO;

public class RabbitTest {
	
	private SyncToUserMessageSender syncToUserMessageSender;
	
	
	private AnnotationConfigApplicationContext ctx;
	
	@Before
	public void before(){
		ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfig.class);     
        ctx.refresh();
        ctx.registerShutdownHook();
        syncToUserMessageSender = (SyncToUserMessageSender) ctx.getBean("syncTobehavioranalysisMessageClient");
	}
	
	
	@Test
	public void send(){	
		UserVO userVO = new UserVO();
		userVO.setAge(2);
		userVO.setSyncType(ToUserConsumerType.TOUSER.name());
		userVO.setUsername("dujunpeng");
		syncToUserMessageSender.send(userVO);
		
	}
	


}
