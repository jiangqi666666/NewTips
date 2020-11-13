package com.jiangqi.newtips.online.communication;

import org.springframework.amqp.AmqpException;

import com.jiangqi.newtips.online.context.SendMsgContext;

/**
 * 消息发送类接口
 * @author jiangqi
 *
 */
public interface MessageWorkInterface {
	/**
	 * 向指定消息队列发送消息
	 * @param queue	队列名称
	 * @param context 消息上下文
	 * @param persistent true 消息持久化 ，false 消息不持久化
	 */
	public void send(String queue,SendMsgContext context,boolean persistent); 
	
	/**
	 * 发送凭证信息给凭证库
	 * @param queue	队列名称
	 * @param context 消息上下文
	 * @param persistent true 消息持久化 ，false 消息不持久化
	 */
	public void sendVoucher(String voucher)throws AmqpException;
}
