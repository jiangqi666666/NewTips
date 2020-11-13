package com.jiangqi.newtips.util.log4j;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.varia.StringMatchFilter;

public class StringCatchFilter extends StringMatchFilter {  
    
	  /** 
	     @deprecated Options are now handled using the JavaBeans paradigm. 
	     This constant is not longer needed and will be removed in the 
	     <em>near</em> term. 
	   */  
	  public static final String STRING_TO_MATCH_OPTION = "StringToMatch";  
	  
	  /** 
	     @deprecated Options are now handled using the JavaBeans paradigm. 
	     This constant is not longer needed and will be removed in the 
	     <em>near</em> term. 
	   */  
	  public static final String ACCEPT_ON_MATCH_OPTION = "AcceptOnMatch";  
	    
	  boolean acceptOnMatch = true;  
	  String stringToMatch;  
	    
	  /** 
	     Returns {@link Filter#NEUTRAL} is there is no string match. 
	   */  
	  public  
	  int decide(LoggingEvent event) {  
	    String msg = event.getRenderedMessage();  
	  
	    if(msg == null ||  stringToMatch == null)  
	      return Filter.NEUTRAL;  
	      
	  
	    if( msg.indexOf(stringToMatch) != -1 ) { // 更改为只接受对应的字符串,而原来的是只过滤到相应的字符串  
	      return Filter.NEUTRAL;  
	    } else { // we've got a match  
	      if(acceptOnMatch) {  
	    return Filter.ACCEPT;  
	      } else {  
	    return Filter.DENY;  
	      }  
	    }  
	  }  
	}  