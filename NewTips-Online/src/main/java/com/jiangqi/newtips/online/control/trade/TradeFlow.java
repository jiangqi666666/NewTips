package com.jiangqi.newtips.online.control.trade;

import com.jiangqi.newtips.online.context.OnlieContext;

/**
 * 交易流程接口
 * @author jiangqi
 *
 */
public interface TradeFlow {
	/**
	 * 运行交易流程
	 * @param context spring的context
	 * @return true 成功； false 失败
	 */
	public boolean runFlow(OnlieContext onlieContext);
}
