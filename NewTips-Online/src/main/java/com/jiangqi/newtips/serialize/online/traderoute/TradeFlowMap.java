package com.jiangqi.newtips.serialize.online.traderoute;

import java.util.HashMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("map")
public class TradeFlowMap {
	@XStreamAlias("tradeflow")
	private HashMap<String,String> tradeFlow=new HashMap<String,String>();
}
