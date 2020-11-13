package com.jiangqi.newtips.rules.api;

import com.jiangqi.newtips.pojo.trade.Head;
import com.jiangqi.newtips.pojo.trade.Trade1001;

/**
 * 报文处理服务
 * @author JQ
 *
 */
public interface MsgHandleService {
	
	/**
	 * 报文提前生成服务，并编押，存入缓存
	 * @param tradeId 报文类型
	 * @param tradeUUid 交易id
	 * @param obj 生成报文所需bean
	 */
	public void advancedMsg3001(String tradeUUid,Trade1001 trade,Head head);
}
