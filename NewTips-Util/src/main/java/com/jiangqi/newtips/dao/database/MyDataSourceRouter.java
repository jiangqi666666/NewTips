package com.jiangqi.newtips.dao.database;

import org.apache.log4j.Logger;
import org.jfaster.mango.partition.DataSourceRouter;

/**
 * mango分库策略
 * @author jiangqi
 *
 */
public class MyDataSourceRouter implements DataSourceRouter<Integer> {
	private static Logger logger = Logger.getLogger(MyDataSourceRouter.class);

	/**
	 * 分成2个库的分库规则
	 * @param shardParam 分库id
	 * @param type	暂时无用
	 * @return
	 */
	public static String getName(Integer shardParam, int type){
		logger.info("getName start");
		
		String ret=shardParam % 5 < 3 ? "ds1" : "ds2";
        return ret;
	}
	
    @Override
    public String getDataSourceName(Integer shardParam, int type) {
    	logger.info("getDataSourceName start");
    	
    	return MyDataSourceRouter.getName(shardParam, type);
    }

}