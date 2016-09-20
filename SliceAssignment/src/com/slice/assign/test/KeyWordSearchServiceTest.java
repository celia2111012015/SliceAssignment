package com.slice.assign.test;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.slice.assign.Services.KeyWordSearchService;

public class KeyWordSearchServiceTest {

	 private KeyWordSearchService keyWordSearchService;
	 private final Logger logger = LoggerFactory.getLogger(KeyWordSearchServiceTest.class);
	 @Before
	    public void setUp() throws Exception {
	        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
	        keyWordSearchService = context.getBean("keyWordSearchService", KeyWordSearchService.class);
	    }
	@Test
	public void testGetRequest() {
		logger.info("first request");
		keyWordSearchService.getRequest("york");
		logger.info("second request");
		keyWordSearchService.getRequest("york");
	}
	
	 @Test
	    public void testReload() throws Exception {
		 keyWordSearchService.reload();
	    
		 keyWordSearchService.getRequest("apple");
		 keyWordSearchService.getRequest("new");

	     
		 keyWordSearchService.getRequest("apple");
		 keyWordSearchService.getRequest("new");
	    }

}
