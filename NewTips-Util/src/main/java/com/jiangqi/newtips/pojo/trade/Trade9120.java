package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("CFX")
public class Trade9120 {
	@XStreamAlias("HEAD")
	private Head head;
	@XStreamAlias("MSG")
	private MsgReturn9120 msg;
}
