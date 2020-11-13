package com.jiangqi.newtips.online.context;

import lombok.Getter;
import lombok.Setter;

/**
 * 发送消息上下文环境
 * @author jiangqi
 */
@Getter  
@Setter
public class SendMsgContext {
	
	/**
	 * 发送消息内容，
	 */
	private String msg;
	
	/**
	 * 发送消息的交易码，
	 */
	private String tradeId;
	
	/**
	 * 发送消息的目标节点
	 */
	private String sendNode;
	
	/**
	 * 消息版本
	 */
	private String version;
	
	/**
	 * 消息报文类型
	 */
	private int msgType;
	
	/**
	 * 上下文环境初始化
	 */
	public void refresh(){
		this.msg="";
		this.tradeId="";
		this.sendNode="";
		this.version="";
		this.msgType=0;
	}
}
