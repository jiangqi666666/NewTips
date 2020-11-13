package com.jiangqi.newtips.online.services;


import org.apache.log4j.Logger;
import com.jiangqi.newtips.dao.redis.RedisClient;
import com.jiangqi.newtips.enuml.OrgType;
import com.jiangqi.newtips.exception.redis.RedisGetNullException;
import com.jiangqi.newtips.exception.redis.RedisHandleException;
import com.jiangqi.newtips.online.exception.onlie.OnlieException;

import lombok.Getter;
import lombok.Setter;

/**
 * 参数查询服务
 * @author jiangqi
 *
 */
@Getter  
@Setter
public class ParamSelectService {
	private static Logger logger = Logger.getLogger(ParamSelectService.class);
	
	/**
	 * rides客户端
	 */
	private RedisClient redisClient;
	
	/**
	 * 获得国库所属清算国库
	 * @param treCode 国库代码
	 * @return 清算国库代码
	 * @throws OnlieException
	 */
	public String getReckonTreCode(String treCode)throws OnlieException{
		logger.info("getReckonTreCode");
		try {
			String hash="TreCodeEntity."+treCode;
			return this.redisClient.getHashField(hash, "ReckonTreCode");
		} catch (RedisHandleException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90001","redis错误","redis错误");
		} catch (RedisGetNullException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90001","无此国库代码","无此国库代码");
		}
	}
	
	/**
	 * 获得tips工作日期
	 * @param orgCode 机构代码
	 * @param orgType 机构类型
	 * @return 节点代码
	 * @throws OnlieException
	 */
	public String getTipsDate()throws OnlieException{
		logger.info("getTipsDate");
		try {
			String hash="TipsSysEntity"; 
		    return this.redisClient.getHashField(hash, "WorkDate"); 
		} catch (RedisHandleException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90001","redis错误","redis错误");
		} catch (RedisGetNullException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90001","无tips工作日期","无tips工作日期");
		} 
	}
	
	/**
	 * 获得机构对应节点代码
	 * @param orgCode 机构代码
	 * @param orgType 机构类型
	 * @return 节点代码
	 * @throws OnlieException
	 */
	public String getNode(String orgCode,OrgType orgType)throws OnlieException{
		logger.info("getNode");
		try {
			String hash;
			
			switch(orgType){
			case  BANK_TYPE:
				hash="BankEntity."+orgCode;
				return this.redisClient.getHashField(hash, "NodeCode");
			case  TAX_TYPE:
				hash="TaxOrgEntity."+orgCode;
				return this.redisClient.getHashField(hash, "NodeCode");
			case  FINANCE_TYPE:
				hash="FinaceOrgEntity."+orgCode;
				return this.redisClient.getHashField(hash, "NodeCode");
			default:
				throw new OnlieException("9005","机构类型错误！","");
			}
		} catch (RedisHandleException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90001","redis错误","redis错误");
		} catch (RedisGetNullException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90001","无tips工作日期","无tips工作日期");
		}
	}
}
