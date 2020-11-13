package com.jiangqi.newtips.dao.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Getter
@Setter
public class JedisDataSourceImpl implements JedisDataSource {
	private static Logger logger = Logger.getLogger(JedisDataSourceImpl.class);
    
	@Autowired
    private ShardedJedisPool shardedJedisPool;
    
    public ShardedJedis getRedisClient() {
    	logger.info("getRedisClient start");
    	
        ShardedJedis shardJedis = null;
        try {
            shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
        	 logger.error(e,e.fillInStackTrace());
            if (null != shardJedis)
                shardJedis.close();
        }
        return null;
    }

    public void returnResource(ShardedJedis shardedJedis) {
    	logger.info("returnResource start");
        shardedJedis.close();
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
    	logger.info("returnResource start");
        shardedJedis.close();
    }
}