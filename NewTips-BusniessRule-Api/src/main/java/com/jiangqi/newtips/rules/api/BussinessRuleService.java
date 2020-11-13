package com.jiangqi.newtips.rules.api;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 业务规则服务
 * @author JQ
 *
 */
public interface BussinessRuleService {
	/**
	 * 业务规则校验
	 * @param rules  所需校验的业务规则名称
	 * @param paras 交易所需参数集合
	 * @param isAll true 全部规则均校验后返回结果； false 校验到第一个不通过后就返回结果
	 * @return 返回结果集合
	 */
	public RetResultSet checkRule(LinkedList<String> rules,HashMap<String,Object> paras, boolean isAll);
}
