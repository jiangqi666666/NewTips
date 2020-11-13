package com.jiangqi.newtips.context;


import java.util.LinkedList;
import lombok.Getter;
import lombok.Setter;

/**
 * 批量业务规则处理环境<p>
 * local 本地规则环境，distance 远程业务规则环境
 * @author jiangqi
 */
@Getter  
@Setter
public class CheckRuleContext {
	private LinkedList<RuleContext> local;
	private LinkedList<RuleContext> distance;
}
