package com.jiangqi.newtips.online.communication.rabbitmq;

import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import com.jiangqi.newtips.online.communication.MessageWorkInterface;
import com.jiangqi.newtips.online.context.SendMsgContext;
import lombok.Getter;
import lombok.Setter;

/**
 * 向MQ发送信息
 * @author jiangqi
 */
@Getter
@Setter
public class MessageWork implements MessageWorkInterface {
	private static Logger logger = Logger.getLogger(MessageWork.class);

	private AmqpTemplate amqpTemplate;
	private AmqpTemplate amqpVoucher;

	@Override
	public void send(String queue, SendMsgContext context, boolean persistent) throws AmqpException {
		// TODO Auto-generated method stub
		logger.info("send");
		logger.info("----------------------------------------------------------------------");
		logger.info("");
		logger.info("");

		MessageProperties msgPro = new MessageProperties();
		msgPro.setContentEncoding("GBK");
		msgPro.setHeader("tradeId", context.getTradeId());
		if(persistent==true)
			msgPro.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		else
			msgPro.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		
		Message msg = new Message(context.getMsg().getBytes(), msgPro);
		this.amqpTemplate.convertAndSend(queue, msg);
	}
	
	@Override
	public void sendVoucher(String voucher) throws AmqpException{
		// TODO Auto-generated method stub
		logger.info("sendVoucher");

		MessageProperties msgPro = new MessageProperties();
		msgPro.setContentEncoding("GBK");
		msgPro.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		
		Message msg = new Message(voucher.getBytes(), msgPro);
		this.amqpVoucher.convertAndSend(msg);
	}
}
