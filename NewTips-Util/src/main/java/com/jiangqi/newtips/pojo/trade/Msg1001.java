package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("MSG")
public class Msg1001 {
	@XStreamAlias("RealHead1001")
	private RealHead1001 realHead1001;
	@XStreamAlias("TurnAccount1001")
	private TurnAccount1001 turnAccount1001;
	@XStreamAlias("Payment1001")
	private Payment1001 payment1001;
	
	public void setPk(Integer pk){
		this.turnAccount1001.setPk(pk,realHead1001);
		this.payment1001.setPk(pk,realHead1001);
	}
	
}
