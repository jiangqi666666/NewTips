package tipstest;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

import com.jiangqi.newtips.online.context.SendMsgContext;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class MessageWork {
	private static Logger logger = Logger.getLogger(MessageWork.class);
	
	private AmqpTemplate amqpTemplate;
	
	public void send(String queue,SendMsgContext context){
		logger.debug("send msg=["+context.getMsg()+"]");
		logger.info("send msg");
		
		MessageProperties msgPro=new MessageProperties();
		msgPro.setContentEncoding("GBK");
		msgPro.setHeader("tradeId", context.getTradeId());
		msgPro.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		Message msg=new Message(context.getMsg().getBytes(),msgPro);
		this.amqpTemplate.convertAndSend(queue,msg);
	}
}
