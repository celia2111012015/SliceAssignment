package com.slice.assign.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.slice.assign.Services.KeyWordSearchService;


public class TaskExcutorTest {
	 private ThreadPoolTaskExecutor executor;
	 private final Logger logger = LoggerFactory.getLogger(TaskExcutorTest.class);
	
	 @Before
	    public void setUp() throws Exception {
	        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
	        executor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
	        
	    } 
	@Test
	public void test() {
		String[] test={"apple", "york", "new", "next", "york", "apple", "next", "slice", "full", "miss", "tomorrow", "get"};
		for(String str: test){
			System.out.println("thread start with input: "+str+"|"+"Active Thread Nums is:"+executor.getActiveCount()
			+"|"+"current Queue Size is:"+executor.getThreadPoolExecutor().getPoolSize()+"|"+new Date());
			KeyWordSearchService ks=new KeyWordSearchService();
			executor.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ks.getRequest(str);
				}
				
			} );
		
			
		}
	}

}
