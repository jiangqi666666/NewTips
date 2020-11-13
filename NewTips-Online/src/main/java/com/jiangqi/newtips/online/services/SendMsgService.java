package com.jiangqi.newtips.online.services;

import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import com.jiangqi.newtips.online.communication.rabbitmq.MessageWork;
import com.jiangqi.newtips.online.context.OnlieInfoContext;
import com.jiangqi.newtips.online.context.SendMsgContext;

import lombok.Getter;
import lombok.Setter;

/**
 * 报文发送服务
 * @author jiangqi
 *
 */
@Getter  
@Setter
public class SendMsgService {
	private static Logger logger = Logger.getLogger(SendMsgService.class);

	/**
	 * MQ报文发送服务
	 */
	private MessageWork messageWork ;
	
	/**
	 * 发送报文给外联机构
	 * @param context 报文发送上下文环境
	 */
	public void sendMsg(SendMsgContext context){
		logger.info("sendMsg");
		
		String queue=OnlieInfoContext.queueNodeMap.getQueueNodeMap().get(context.getSendNode());
		messageWork.send(queue,context,false);
	}
	
	/**
	 * 发送接收凭证给凭证库
	 * @param context 报文发送上下文环境
	 */
	public void sendVoucher(String msg)throws AmqpException{
		logger.info("sendVoucher");
		
		messageWork.sendVoucher(msg);
	}
}
