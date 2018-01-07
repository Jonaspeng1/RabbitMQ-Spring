package com.Jonas;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.Jonas.config.SpringConfig;

public class App {
	
	private static AnnotationConfigApplicationContext ctx;
	
	public static void main(String [] args){
		ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfig.class);     
        ctx.refresh();
        ctx.registerShutdownHook();
	}

}
