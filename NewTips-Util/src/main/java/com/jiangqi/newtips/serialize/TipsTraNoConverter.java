package com.jiangqi.newtips.serialize;

import com.thoughtworks.xstream.converters.basic.IntConverter;

/**
 * xstream交易流水号转换
 * @author jiangqi
 *
 */
public class TipsTraNoConverter extends IntConverter {

	@Override
	public String toString(Object obj) {
		// TODO Auto-generated method stub
		String keyTmp = "%08d";
		String ret = String.format(keyTmp, obj);
		return ret;
	}

	@Override
	public Object fromString(String str) {
		long value = Long.valueOf(str);
		if ((value < -2147483648L) || (value > 4294967295L)) {
			throw new NumberFormatException("For input string: \"" + str + '"');
		}
		return new Integer((int) value);
	}

}
