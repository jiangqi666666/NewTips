package com.jiangqi.newtips.dao.database;

import org.apache.log4j.Logger;
import org.jfaster.mango.partition.TablePartition;

/**
 * mango分表策略，共分5张表
 * @author jiangqi
 *
 */
public class ModFiveTablePartition implements TablePartition<Integer> {
	private static Logger logger = Logger.getLogger(ModFiveTablePartition.class);

    @Override
    public String getPartitionedTable(String table, Integer shardParam, int type) {
    	logger.info("getPartitionedTable start");
        return table + "_" + (shardParam % 5);
    }

    
}