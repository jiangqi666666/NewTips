package com.jiangqi.newtips.online.services;

import java.util.HashMap;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import com.jiangqi.newtips.online.exception.onlie.OnlieException;
import com.jiangqi.newtips.rules.api.BussinessRuleService;
import com.jiangqi.newtips.rules.api.RetResultSet;

import lombok.Getter;
import lombok.Setter;

/**
 * 远程业务规则校验服务
 * @author jiangqi
 *
 */
@Getter  
@Setter
public class RemoteBussinessRuleService {
	private static Logger logger = Logger.getLogger(RemoteBussinessRuleService.class);
	
	/**
	 * dubbo的业务规则校验服务
	 */
	private BussinessRuleService rule;
	
	/**
	 * 调用dubbo，验证业务规则
	 * @param rules 需校验的全部业务规则名称
	 * @param paras 业务规则校验所需参数池
	 * @param isAll true:全部规则在服务端都需校验，并返回全部校验结果  false：服务端业务规则校验发现错误后立刻返回，不再进行后续校验
	 * @return	业务规则校验处理结果集
	 * @throws Exception
	 */
	public  RetResultSet checkRule(LinkedList<String> rules, HashMap<String, Object> paras,boolean isAll) throws OnlieException{
		logger.info("checkRule");
		
		try{
			return rule.checkRule(rules, paras,isAll);
		}catch (Exception e){
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90001","dubbo调用失败","");
		}
	}
}
