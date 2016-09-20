package com.slice.assign.Services;
/*
 * @Author: Celia Kang
 * Class: KeyWordSearchService 
 * Function: find request word total Appear number in resource stored in server
 * Description: this service will first look into cache, check the word is searched before or not,
 *  if not it will be searched through the list of file resource iteratively.  
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import com.slice.assign.beans.RequestBean;


@Service
public class KeyWordSearchService {
	
	private static final String PROPERTYFILE= "config.properties";
	// For local test I place the resource file in the classpath of the project, the location is referred
	// from properties file, config.properties, so it can be changed to the server location in the future.
	private static final String RESOURCELOCATION ="resourceLocation";	
	 private final Logger logger = LoggerFactory.getLogger(KeyWordSearchService.class);
	//method to search into resource
	private RequestBean searchResource(String input){
		
		return search(input);
		
	}

	// method to look into cache first, if no find, then search into resource, return RequestBean Object
	@Cacheable(value="requestCache", key="#input")
	public RequestBean getRequest(String input){
		logger.info("get from cache");
		return searchResource(input);
	}
	
	
// method to clean cache when needed
    @CacheEvict(value="requestCache",allEntries=true)
    public void reload() {
    }

// method to search into the file lists
	private RequestBean search(String input){
		logger.info("get from resource search");
		ClassLoader classLoader = getClass().getClassLoader();
		Properties prop = new Properties();
    	InputStream in = null;
    	RequestBean rb=new RequestBean();
    	rb.setInput(input);
		long value=0;
		try{
			
			in = getClass().getClassLoader().getResourceAsStream(PROPERTYFILE);
			if (in!= null) {
				prop.load(in);
			} else {
				throw new FileNotFoundException("property file '" + PROPERTYFILE + "' not found in the classpath");
			}
			String resourceLocation= prop.getProperty(RESOURCELOCATION);
			File folder = new File(classLoader.getResource(resourceLocation).getFile());
			
			File[] ef=folder.listFiles();
			for(File file: ef){
				try {
					Scanner sc=new Scanner(file);
					while(sc.hasNextLine()){
						String line=sc.nextLine();
						line=line.replaceAll("[^a-zA-Z/s]", " ");
						String[] words=line.split("\\s+");
						for(String word: words){
							if(word.trim().toLowerCase().equals(input)){
								value++;
							}
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rb.setAppearNumber(value);
		return rb;
	}
}
