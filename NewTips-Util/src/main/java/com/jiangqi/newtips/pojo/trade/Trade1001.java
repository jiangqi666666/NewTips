package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("CFX")
public class Trade1001 {
	@XStreamAlias("HEAD")
	private Head head;
	@XStreamAlias("MSG")
	private Msg1001 msg;
	
	public void setPk(Integer pk){
		this.msg.setPk(pk);
	}
}
