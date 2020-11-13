package com.jiangqi.newtips.serialize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.expression.ParseException;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * xstream日期转换
 * @author jiangqi
 *
 */
public class XStreamYMDDateConverter extends AbstractSingleValueConverter {  
	  
    private static final DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat(  
            "yyyyMMdd");  
  
    @Override  
    public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {  
        return type.equals(Date.class);  
    }  
  
    @Override  
    public Object fromString(String str) {  
        // 这里将字符串转换成日期  
        try {  
            return DEFAULT_DATEFORMAT.parseObject(str);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        throw new ConversionException("Cannot parse date " + str);  
    }  
  
    @Override  
    public String toString(Object obj) {  
        // 这里将日期转换成字符串  
        return DEFAULT_DATEFORMAT.format((Date) obj);  
    }  
  
}  
