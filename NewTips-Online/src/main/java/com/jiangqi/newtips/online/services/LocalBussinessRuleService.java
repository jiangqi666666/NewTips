package com.jiangqi.newtips.online.services;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.log4j.Logger;

/**
 * 本地业务规则校验服务
 * @author jiangqi
 *
 */
public class LocalBussinessRuleService {
	private static Logger logger = Logger.getLogger(LocalBussinessRuleService.class);
	
	/**
	 * 交易业务规则
	 * @param rules  需校验的全部业务规则名称
	 * @param paras  业务规则校验所需参数池
	 */
	public void checkRule(LinkedList<String> rules, HashMap<String, Object> paras) {
		// TODO Auto-generated method stub
		logger.info("checkRule");
		
	}
}
