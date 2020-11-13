package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;
@Getter  
@Setter
public class MsgReturn9120 {
	@XStreamAlias("OriMsgNo")
	private String oriMsgNo;
	@XStreamAlias("Result")
	private String result;
	@XStreamAlias("AddWord")
	private String addWord;
	@XStreamAlias("Information")
	private String information;
}
