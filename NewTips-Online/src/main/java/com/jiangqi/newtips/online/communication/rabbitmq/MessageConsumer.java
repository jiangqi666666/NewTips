package com.jiangqi.newtips.online.communication.rabbitmq;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import com.jiangqi.newtips.online.context.OnlieContext;
import com.jiangqi.newtips.online.control.MangoPool;
import com.jiangqi.newtips.online.control.Online;
import lombok.Getter;
import lombok.Setter;

/**
 * 从MQ接收消息<p>
 * 每次接收一个新消息需创建一个新的OnlieContext对象<p>
 * 如果为新的接收线程，需在mango资源池中创建新mango对象<p>
 * @author jiangqi
 */
@Getter
@Setter
public class MessageConsumer implements MessageListener {
	private static Logger logger = Logger.getLogger(MessageConsumer.class); 
	
	private Online Online;
	
	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		logger.info("onMessage start ");
		
		MangoPool.getMangoBean(Thread.currentThread().getId());
		
		OnlieContext onlieContext = new OnlieContext();
		onlieContext.getRcvMsgContext().setMsg(new String(msg.getBody()));
		onlieContext.getRcvMsgContext().setTradeId((String)msg.getMessageProperties().getHeaders().get("tradeId"));
		
		this.Online.run(onlieContext);
	}
}
