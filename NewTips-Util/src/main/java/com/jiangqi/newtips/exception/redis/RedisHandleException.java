package com.jiangqi.newtips.exception.redis;

/**
 * redis异常类
 * @author jiangqi
 *
 */
@SuppressWarnings("serial")
public class RedisHandleException extends  Exception{

	public RedisHandleException(String msg){
		super("redis操作异常，result="+msg);
	}
}
