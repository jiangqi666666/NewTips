package com.jiangqi.newtips.online.context;

import com.jiangqi.newtips.serialize.online.checkrule.AllCheckRuleMap;
import com.jiangqi.newtips.serialize.online.onlieinfo.QueueNodeMap;
import com.jiangqi.newtips.serialize.online.traderoute.TradeFlowMap;

/**
 * 联机交易配置信息上下文
 * @author jiangqi
 *
 */
public class OnlieInfoContext {
	/**
	 * 交易与流程控制对应设置
	 */
	public static TradeFlowMap tradeFlowMap=new TradeFlowMap() ;
	
	/**
	 * 业务规则校验设置
	 */
	public static AllCheckRuleMap allCheckRuleMap=new AllCheckRuleMap() ;
	
	/**
	 * 节点与队列对应关系设置
	 */
	public static QueueNodeMap queueNodeMap ;
}
