package com.slice.assign.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.slice.assign.Services.KeyWordSearchService;

public class Test {
	public static void main(String[] argus){
	//	KeyWordSearchService ks=new KeyWordSearchService();
		//System.out.println(ks.getKeyWordNumbers("york"));
	//	String line="You mention the Rotherham Address as complimenting Mr. Pitt";
	//	line=line.replaceAll("[^a-zA-Z/s]", " ");
	//	System.out.println(line);
		
		KeyWordSearchService ks=new KeyWordSearchService();
		ks.getRequest("york").getAppearNumber();
		ks.getRequest("york").getAppearNumber();
	}
}
