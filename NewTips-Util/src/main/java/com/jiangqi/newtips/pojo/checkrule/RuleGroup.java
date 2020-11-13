package com.jiangqi.newtips.pojo.checkrule;

import java.util.HashMap;
import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;

/**
 * 规则组POJO，包括组id，包括的多个业务规则，规则用参数池
 * @author jiangqi
 *
 */
@Getter  
@Setter
public class RuleGroup {
	private String groupId;
	private LinkedList<String> rules=new LinkedList<String>();
	private HashMap<String,Object> params=new HashMap<String,Object>();
}
