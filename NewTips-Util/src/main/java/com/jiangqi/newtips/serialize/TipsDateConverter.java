package com.jiangqi.newtips.serialize;

import com.thoughtworks.xstream.converters.basic.DateConverter;

/**
 * xstream日期转换
 * @author jiangqi
 *
 */
public class TipsDateConverter extends DateConverter {
    public TipsDateConverter(String dateFormat) {
        super(dateFormat, new String[] { dateFormat });
    }
}