package com.jiangqi.newtips.exception.redis;

import lombok.Getter;

/**
 * redis主键重复
 * @author jiangqi
 *
 */
@Getter  
@SuppressWarnings("serial")
public class RedisKeyRepeatException extends  Exception{
	private String key;
	
	public RedisKeyRepeatException(String key){
		super("主键已经存在，key="+key);
		this.key=key;
	}
}
