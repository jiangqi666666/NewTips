package com.jiangqi.newtips.online.context;

import lombok.Getter;
import lombok.Setter;

/**
 * 联机接收消息上下文
 * @author jiangqi
 */
@Getter  
@Setter
public class RcvMsgContext {
	/**
	 * 消息内容
	 */
	private String msg;
	
	/**
	 * 交易码
	 */
	private String tradeId;
	
	/**
	 * 消息版本
	 */
	private String version;
	
	/**
	 * 消息报文类型
	 */
	private int msgType;
	
	/**
	 * 初始化上下文环境
	 */
	public void refresh(){
		this.msg="";
		this.tradeId="";
		this.version="";
		this.msgType=0;
	}
}
