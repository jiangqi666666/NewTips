package tipstest;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class MessageConsumer implements MessageListener {
	private static Logger logger = Logger.getLogger(MessageConsumer.class); 
	
	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		String logmsg="get msg ,tradeid=%s, msg=%s";
		String tmp=String.format(logmsg, (String)msg.getMessageProperties().getHeaders().get("tradeId"),new String(msg.getBody()));
		logger.info(tmp);
		
	}
}
