package com.jiangqi.newtips.online.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangqi.newtips.context.RedisDaoConstant;
import com.jiangqi.newtips.dao.redis.RedisClient;
import com.jiangqi.newtips.exception.redis.RedisHandleException;
import com.jiangqi.newtips.exception.redis.RedisKeyRepeatException;
import com.jiangqi.newtips.online.exception.onlie.OnlieException;


import lombok.Getter;
import lombok.Setter;

/**
 * 交易主键管理服务
 * @author jiangqi
 *
 */
@Getter  
@Setter
public class TradeKeyService {
	private static Logger logger = Logger.getLogger(TradeKeyService.class);
	
	/**
	 * redis客户端
	 */
	@Autowired
	private RedisClient redisClient;
	
	/**
	 * 保存1001业务主键
	 * @param value 业务主键
	 * @throws OnlieException
	 */
	public void trade1001SaveKey(String value)throws OnlieException{
		logger.info("trade1001SaveKey");
		
		try {
			this.redisClient.addSet(RedisDaoConstant.TRADE_1001_SET_NAME,value);
		} catch (RedisHandleException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("9001","redis异常","redis异常");
		} catch (RedisKeyRepeatException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("9002","交易主键重复","此交易的主键重复："+value);
		} 
	}
}
