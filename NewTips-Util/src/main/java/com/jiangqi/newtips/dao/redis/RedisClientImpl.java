package com.jiangqi.newtips.dao.redis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiangqi.newtips.context.RedisDaoConstant;
import com.jiangqi.newtips.exception.redis.RedisExpireException;
import com.jiangqi.newtips.exception.redis.RedisGetNullException;
import com.jiangqi.newtips.exception.redis.RedisHandleException;
import com.jiangqi.newtips.exception.redis.RedisKeyRepeatException;
import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

@Getter
@Setter
public class RedisClientImpl implements RedisClient {
	private static Logger logger = Logger.getLogger(RedisClientImpl.class);

	@Autowired
	private JedisDataSource jedisDataSource;

	@Override
	public void disconnect() {
		logger.info("disconnect start");
		
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if(shardedJedis!=null)
			shardedJedis.disconnect();
	}

	@Override
	public String set(String key, String value)throws RedisHandleException,RedisKeyRepeatException {
		logger.info("set start");
		
		String result = null;

		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) 
			throw new RedisHandleException("redis.getRedisClient失败");
		
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			throw new RedisHandleException("redis.getRedisClient失败");
		} finally {
			jedisDataSource.returnResource(shardedJedis);
		}
		return result;
	}
	
	@Override
	public void setnx(String key, String value, int seconds)
			throws RedisHandleException, RedisKeyRepeatException, RedisExpireException {
		logger.info("setnx start");

		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) 
			throw new RedisHandleException("redis.getRedisClient失败");
		
		try {
			ShardedJedisPipeline pipeline = shardedJedis.pipelined();
			pipeline.setnx(key, value);
			if (seconds > 0)
				pipeline.expire(key, seconds);
			List<Object> ret = pipeline.syncAndReturnAll();

			Long retlong;
			Object obj = ret.get(0);
			if ((Long.class.isInstance(obj)) == true) {
				retlong = (Long) obj;
				if (retlong == 0)
					throw new RedisKeyRepeatException(key);
			} else
				throw new RedisHandleException("setnx 返回类型错误，result=" + obj.toString());

			obj = ret.get(1);
			if ((Long.class.isInstance(obj)) == true) {
				retlong = (Long) obj;
				if (retlong == 0)
					throw new RedisExpireException(String.valueOf(key));
			} else
				throw new RedisHandleException("expire 返回类型错误，result=" + obj.toString());
		} finally{
			jedisDataSource.returnResource(shardedJedis);
		}
	}

	@Override
	public void addSet(String set, String value) throws RedisHandleException,RedisKeyRepeatException{
		logger.info("addSet start");
		
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) 
			throw new RedisHandleException("jedisDataSource.getRedisClient 失败");
		
		try {
			Long result=shardedJedis.sadd(set, value);
			if(result==0)
				throw new RedisKeyRepeatException(set+"."+value);
	
		} finally{
			jedisDataSource.returnResource(shardedJedis);
		}
	}
	
	@Override
	public String getHashField(String hash,String field)throws RedisHandleException,RedisGetNullException{
		logger.info("getHashField start");
		
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) 
			throw new RedisHandleException("jedisDataSource.getRedisClient 失败");
		
		try {
			String result=shardedJedis.hget(hash, field);
			if(result.equals(RedisDaoConstant.REDIS_DAO_NULL))
				throw new RedisGetNullException("");
	
			return result;
		} finally{
			jedisDataSource.returnResource(shardedJedis);
		}
	}
	
	/**
	 * 获取单个值
	 * @param key
	 * @return
	 */
	public String get(String key) throws RedisHandleException,RedisGetNullException{
		logger.info("get start");
		
		String result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) 
			throw new RedisHandleException("jedisDataSource.getRedisClient 失败");

		try {
			result = shardedJedis.get(key);
			if(result.trim().equals(RedisDaoConstant.REDIS_DAO_NULL))
				throw new RedisGetNullException("");
			
		} catch (Exception e) {
			throw new RedisHandleException("jedisDataSource.getRedisClient 失败");
		} finally {
			jedisDataSource.returnResource(shardedJedis);
		}
		return result;
	}

	/////////////////////////////////////////////////////////////////////////////////


	public Boolean exists(String key) {
		Boolean result = false;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.exists(key);
		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	public String type(String key) {
		String result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.type(key);

		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 在某段时间后失效
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long expire(String key, int seconds) {
		Long result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.expire(key, seconds);

		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expireAt(String key, long unixTime) {
		Long result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.expireAt(key, unixTime);

		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	public Long ttl(String key) {
		Long result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.ttl(key);

		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	public boolean setbit(String key, long offset, boolean value) {

		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		boolean result = false;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setbit(key, offset, value);
		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	public boolean getbit(String key, long offset) {
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		boolean result = false;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;

		try {
			result = shardedJedis.getbit(key, offset);
		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	public long setRange(String key, long offset, String value) {
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		long result = 0;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setrange(key, offset, value);
		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	public String getRange(String key, long startOffset, long endOffset) {
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getrange(key, startOffset, endOffset);

		} catch (Exception e) {

			broken = true;
		} finally {
			jedisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}


}
