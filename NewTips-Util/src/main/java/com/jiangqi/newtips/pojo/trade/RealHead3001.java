package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class RealHead3001 {
	@XStreamAlias("TaxOrgCode")
	private String taxOrgCode;
	@XStreamAlias("EntrustDate")
	private String entrustDate;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsTraNoConverter.class )
	@XStreamAlias("TraNo")
	private Integer traNo;
}
