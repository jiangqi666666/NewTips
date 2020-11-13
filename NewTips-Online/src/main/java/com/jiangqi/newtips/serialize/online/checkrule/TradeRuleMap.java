package com.jiangqi.newtips.serialize.online.checkrule;


import java.util.LinkedList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("trade")
public class TradeRuleMap {
	@XStreamAsAttribute
	private String tradeId;
	@XStreamAlias("remotegroup")
	private LinkedList<CheckGroupMap> remotes=new LinkedList<CheckGroupMap>();
	@XStreamAlias("localgroup")
	private LinkedList<CheckRule> local=new LinkedList<CheckRule>();
}
