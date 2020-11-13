package com.jiangqi.newtips.serialize.online.checkrule;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Getter  
@Setter
@XStreamAlias("all")
public class AllCheckRuleMap {
	private HashMap<String,TradeRuleMap> trades=new HashMap<String,TradeRuleMap>();
}
