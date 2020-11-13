package com.jiangqi.newtips.exception.redis;

/**
 * redis获得空值
 * @author jiangqi
 *
 */
@SuppressWarnings("serial")
public class RedisGetNullException extends  Exception{

	public RedisGetNullException(String msg){
		super("redis查询为空，result="+msg);
	}
}
