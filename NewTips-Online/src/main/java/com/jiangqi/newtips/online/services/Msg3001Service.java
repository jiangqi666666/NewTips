package com.jiangqi.newtips.online.services;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import com.jiangqi.newtips.dao.redis.RedisClient;
import com.jiangqi.newtips.exception.redis.RedisGetNullException;
import com.jiangqi.newtips.exception.redis.RedisHandleException;
import com.jiangqi.newtips.pojo.trade.Head;
import com.jiangqi.newtips.pojo.trade.Trade1001;
import com.jiangqi.newtips.pojo.trade.Trade3001;
import com.jiangqi.newtips.rules.api.MacManagerService;
import com.jiangqi.newtips.serialize.MessageParse;
import com.jiangqi.newtips.util.CreateMsg;

import lombok.Getter;
import lombok.Setter;

/**
 * 3001报文服务
 * @author jiangqi
 *
 */
@Getter
@Setter
public class Msg3001Service {
	private static Logger logger = Logger.getLogger(Msg3001Service.class);

	/**
	 * redis客户端
	 */
	private RedisClient redisClient;
	
	/**
	 * 报文创建api
	 */
	private CreateMsg createMsg;
	
	/**
	 * 签名服务
	 */
	private MacManagerService macManagerService;

	/**
	 * 创建转发的3001报文
	 * <p>
	 * 先尝试从redis中获得预生成的3001报文，如果不能获得，则重新生成3001
	 * 
	 * @param tradeUUid	交易uuid，redis中对应3001的key
	 * @param trade1001	3001报文来源的1001交易pojo
	 * @param head		3001报文头
	 * @return	创建的3001报文
	 */
	public String create3001(final String tradeUUid, Trade1001 trade1001, Head head, MessageParse msgParse)
			throws IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException,
			IntrospectionException, IOException {

		logger.info("create3001 start");

		boolean flag = false; 	//true：redis获得错误，false：redis获得成功
		int errCount = 0;		//参数redis次数, 超过此次数还是不能获得则重新生成3001报文
		final String key = "msg.3001." + tradeUUid;
		String msg = "";
		
		while (errCount < 2) {
			try {
				flag=false;
				msg = this.redisClient.get(key);
			} catch (RedisHandleException e) {
				logger.error(e, e.fillInStackTrace());
				flag = true;
				errCount++;
			} catch (RedisGetNullException e) {
				logger.error(e, e.fillInStackTrace());
				flag = true;
				errCount++;
			}

			if (flag == false)
				break;
			else if(flag == true&&errCount<1){
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			else
				return creatBean(tradeUUid, trade1001, head, msgParse);
		}

		return msg;
	}

	/**
	 * 重新根据1001报文生成3001报文
	 * 
	 * @param tradeUUid
	 * @param trade1001
	 * @param head
	 * @param msgParse
	 * @return
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IntrospectionException
	 * @throws IOException
	 */
	private String creatBean(String tradeUUid, Trade1001 trade1001, Head head, MessageParse msgParse)
			throws IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException,
			IntrospectionException, IOException {

		logger.info("creatBean start");

		Trade3001 trade3001 = this.createMsg.create3001(trade1001);
		trade3001.setHead(head);

		StringBuffer msg = new StringBuffer();
		msg.append(msgParse.saveBean(trade3001, "marshaller3001"));
		msg.append("<!--");
		msg.append(this.macManagerService.createMac(trade3001.getHead().getSrc(), "AAA"));
		msg.append("-->");

		return msg.toString();
	}
}
