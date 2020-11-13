package com.jiangqi.newtips.dao.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * redis操作接口
 * @author jiangqi
 *
 */
public interface JedisDataSource {
	/**
	 * 获得redis客户端
	 * @return
	 */
	public ShardedJedis getRedisClient();
	
	/**
	 * 释放redis客户端资源
	 * @param shardedJedis
	 */
	public void returnResource(ShardedJedis shardedJedis);
	
	/**
	 * 释放redis客户端资源
	 * @param shardedJedis
	 */
	public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
