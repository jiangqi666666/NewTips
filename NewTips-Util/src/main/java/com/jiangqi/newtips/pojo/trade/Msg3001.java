package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("Msg3001")
public class Msg3001 {
	@XStreamAlias("RealHead3001")
	private RealHead3001 realHead3001;
	@XStreamAlias("TurnAccount3001")
	private TurnAccount3001 turnAccount3001;
	@XStreamAlias("Payment3001")
	private Payment3001 payment3001;
}
