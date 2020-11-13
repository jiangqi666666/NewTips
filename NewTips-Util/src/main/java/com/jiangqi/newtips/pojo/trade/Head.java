package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("HEAD")
public class Head {
	@XStreamAlias("VER")
	private String ver;
	@XStreamAlias("SRC")
	private String src ;
	@XStreamAlias("DES")
	private String des;
	@XStreamAlias("APP")
	private String app;
	@XStreamAlias("MsgNo")
	private String msgNo;
	@XStreamAlias("MsgID")
	private String msgID;
	@XStreamAlias("MsgRef")
	private String msgRef;
	
	//@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDateConverter.class , strings={"yyyyMMdd"} )
	@XStreamAlias("WorkDate")
	private String workDate;
	
	@XStreamAlias("Reserve")
	private String reserve;
}
