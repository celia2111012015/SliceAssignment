package com.slice.assign.Controllers;

import java.util.Date;
import java.util.concurrent.Callable;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.google.common.util.concurrent.AtomicLongMap;
import com.slice.assign.Services.KeyWordSearchService;


@Controller

public class RequestController {
	private AtomicLongMap<String> rm = AtomicLongMap.create();
	@RequestMapping(method={RequestMethod.GET}, value="/")
	public ModelAndView getRequest(HttpServletRequest req, HttpServletResponse res){
		
		
		return new ModelAndView("index");
		
	}
	@RequestMapping(method={RequestMethod.GET, RequestMethod.POST}, value="/submit")
	public @ResponseBody Callable<String> submitRequest(HttpServletRequest req, HttpServletResponse res){
		String in=req.getParameter("keyWord");
		String input=in.trim().toLowerCase();
		
		Callable<String> callable = new Callable<String>() {
            @Override
            public String call () throws Exception {
            	
            	KeyWordSearchService ks=new KeyWordSearchService();
        		long n1=ks.getRequest(input).getAppearNumber();
        		long requestNumber=getRequestNumber(input);
        	
       // 		RequestCountService rs=new RequestCountService();
        //		int n2=rs.getRequestNumber(input);
        
        	String	output="Input Key word: "+input+"|"+
        			"RequestNumber: "+requestNumber+"|"+"AppearTimes: "+n1+"|"+"At "+new Date();
                return output;
            }
        };
    //    req.setAttribute("result", callable);
        req.setAttribute("result", callable);
     
        return callable;
	}
	
	private long getRequestNumber(String input){
		long requestNumber=0;
		if(rm.containsKey(input)){
			requestNumber=rm.incrementAndGet(input);
		}else{
			requestNumber=rm.addAndGet(input, 1);
		}
		return requestNumber;
	}
	
}
