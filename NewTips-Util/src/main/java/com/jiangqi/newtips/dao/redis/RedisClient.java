package com.jiangqi.newtips.dao.redis;

import com.jiangqi.newtips.exception.redis.RedisExpireException;
import com.jiangqi.newtips.exception.redis.RedisGetNullException;
import com.jiangqi.newtips.exception.redis.RedisHandleException;
import com.jiangqi.newtips.exception.redis.RedisKeyRepeatException;

public interface RedisClient {
	
	/**
	 * 关键redis数据库
	 */
	public void disconnect() ;

	/**
	 * 设置单个值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value)throws RedisHandleException,RedisKeyRepeatException ;
	
	/**
	 * 设置单个值
	 * 
	 * @param key
	 * @param value
	 * @param seconds 过期秒数
	 * @return
	 */
	public void setnx(String key, String value,int seconds)  throws RedisHandleException,RedisKeyRepeatException,RedisExpireException;
	
	/**
	 * 向指定set中添加值
	 * @param set set名称
	 * @param value 需添加的值
	 * @throws RedisHandleException redis系统错误
	 * @throws RedisKeyRepeatException value已经存在，添加失败
	 */
	public void addSet(String set, String value) throws RedisHandleException,RedisKeyRepeatException;
	
	/**
	 * 获得指定的hashes的某一field值
	 * @param hash hashes的名字
	 * @param field 所需字段名
	 * @return 查询得到的值
	 * @exception RedisHandleException redis系统错误
	 * @exception RedisKeyRepeatException value已经存在，插入失败
	 */
	public String getHashField(String hash,String field)throws RedisHandleException,RedisGetNullException;
	
	/**
	 * 获取单个值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) throws RedisHandleException,RedisGetNullException;
	
	//////////////////////////////////////////////////////////////////////////////////////////////

	

	public Boolean exists(String key) ;

	public String type(String key) ;

	/**
	 * 在某段时间后失效
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long expire(String key, int seconds) ;

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expireAt(String key, long unixTime);
	public Long ttl(String key);
	public boolean setbit(String key, long offset, boolean value);
	public boolean getbit(String key, long offset) ;
	public long setRange(String key, long offset, String value) ;
	public String getRange(String key, long startOffset, long endOffset);
	
	
}

