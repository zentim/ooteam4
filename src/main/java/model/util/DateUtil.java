package main.java.model.util;

import java.util.Date;
import java.text.ParseException;  
import java.text.SimpleDateFormat;  

public class DateUtil {

    public static java.sql.Timestamp d2t(java.util.Date d) {
        if (null == d) {
            return null;
        }
        return new java.sql.Timestamp(d.getTime());
    }

    public static java.util.Date t2d(java.sql.Timestamp t) {
        if (null == t) {
            return null;
        }
        return new java.util.Date(t.getTime());
    }
    
    public static Date s2d(String s) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	    
    	if (s.equals("")) {
    		return null;
    	}
    	
    	try {  
	        Date date = formatter.parse(s);  
	        System.out.println("Date is: " + date);
	        return date;
	    } catch (ParseException e) {e.printStackTrace();}  
	    
    	return new Date();
    }
    
}
