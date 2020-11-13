package com.jiangqi.newtips.rules.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiangqi.newtips.dao.redis.RedisClient;
import com.jiangqi.newtips.exception.redis.RedisExpireException;
import com.jiangqi.newtips.exception.redis.RedisHandleException;
import com.jiangqi.newtips.exception.redis.RedisKeyRepeatException;
import com.jiangqi.newtips.pojo.trade.Head;
import com.jiangqi.newtips.pojo.trade.Trade1001;
import com.jiangqi.newtips.pojo.trade.Trade3001;
import com.jiangqi.newtips.rules.api.MacManagerService;
import com.jiangqi.newtips.rules.api.MsgHandleService;
import com.jiangqi.newtips.serialize.MessageParse;
import com.jiangqi.newtips.util.ApplicationContextUtil;
import com.jiangqi.newtips.util.CreateMsg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgHandleServiceImpl implements MsgHandleService {
	private static Logger logger = Logger.getLogger(MsgHandleServiceImpl.class);
	
	@Autowired
	private CreateMsg createMsg;
	
	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private ApplicationContextUtil context;
	
	@Autowired
	private MacManagerService macManagerService;

	@Override
	public void advancedMsg3001(String tradeUUid, Trade1001 trade1001,Head head) {
		// TODO Auto-generated method stub
		logger.info("advancedMsg3001 start");
	
		try {
			@SuppressWarnings("static-access")
			MessageParse msgParse=(MessageParse)this.context.getBean("msgParse");
			
			Trade3001 trade3001 = this.createMsg.create3001(trade1001);
			trade3001.setHead(head);
			
			StringBuffer msg=new StringBuffer();
			msg.append(msgParse.saveBean(trade3001,"marshaller3001"));
			msg.append("<!--");
			msg.append(this.macManagerService.createMac(trade3001.getHead().getSrc(), "AAA"));
			msg.append("-->");

			String key="msg.3001."+tradeUUid;
			this.redisClient.setnx(key, msg.toString(), 600);
			
		} catch (IllegalAccessException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (ClassNotFoundException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (InvocationTargetException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (InstantiationException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (IntrospectionException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (IOException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (RedisHandleException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (RedisKeyRepeatException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (RedisExpireException e) {
			logger.error(e,e.fillInStackTrace());
		}
	}
}
