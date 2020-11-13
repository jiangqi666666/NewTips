package com.jiangqi.newtips.online.context;

import java.util.LinkedList;
import lombok.Getter;
import lombok.Setter;

/**
 * 联机交易流转过程中的上下文环境
 * @author jiangqi
 *
 */
@Getter  
@Setter
public class OnlieContext {

	/**
	 * 接收消息的上下文环境
	 */
	private RcvMsgContext rcvMsgContext=new RcvMsgContext();
	
	/**
	 * 发送消息的上下文环境
	 */
	private LinkedList<SendMsgContext> sendMsgContext =new LinkedList<SendMsgContext>();
	
	/**
	 * 上下文环境初始化
	 */
	public void refresh(){
		this.rcvMsgContext.refresh();
		this.sendMsgContext.clear();
	}
}
