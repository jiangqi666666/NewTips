package com.jiangqi.newtips.context;

import java.util.HashMap;
import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;

/**
 * 批量业务规则处理环境
 * @author jiangqi
 */
@Getter  
@Setter
public class RuleContext {
	/**
	 * 业务规则名称集合
	 */
	private LinkedList<String> ruleIds;
	
	/**
	 * 对应参数池
	 */
	private HashMap<String,Object> parameterContext;
}
