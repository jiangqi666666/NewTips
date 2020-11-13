package com.jiangqi.newtips.serialize;

import java.text.DecimalFormat;

import com.thoughtworks.xstream.converters.basic.DoubleConverter;

/**
 * xstream double转换
 * @author jiangqi
 *
 */
public class TipsDoubleConverter extends  DoubleConverter{

	@Override
	public String toString(Object obj) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("0.00"); 
		 return obj == null ? null : df.format(obj);
	}
	
}
