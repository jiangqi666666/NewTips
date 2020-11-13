package com.jiangqi.newtips.online.control;

import org.apache.log4j.Logger;
import com.jiangqi.newtips.online.context.OnlieContext;
import com.jiangqi.newtips.online.context.OnlieInfoContext;
import com.jiangqi.newtips.online.context.SendMsgContext;
import com.jiangqi.newtips.online.control.trade.TradeFlow;
import com.jiangqi.newtips.online.services.SendMsgService;
import com.jiangqi.newtips.util.ApplicationContextUtil;

/**
 * 联机交易处理类<p>
 * MQ收到一个消息后，会调用此类完成业务处理<p>
 * 此类为线程安全
 * @author jiangqi
 *
 */
public class Online {
	private static Logger logger = Logger.getLogger(Online.class);

	/**
	 * 根据消息头中交易ID获得对应交易处理服务类
	 * @param tradeId 消息头中交易ID
	 * @return 对应交易具体处理服务
	 */
	private TradeFlow getTradeFlow(String tradeId) {
		logger.info("getTradeFlow");

		String tradeFlow = OnlieInfoContext.tradeFlowMap.getTradeFlow().get(tradeId);
		return (TradeFlow)ApplicationContextUtil.getBean(tradeFlow);
	}

	/**
	 * 根据onlieContext上下文环境调用后台联机处理交易
	 * @param onlieContext
	 */
	public void run(OnlieContext onlieContext) {
		logger.info("run start");
		
		TradeFlow tradeFlow = getTradeFlow(onlieContext.getRcvMsgContext().getTradeId());

		//交易id无对应交易处理类，直接丢弃本消息
		if(tradeFlow==null){
			logger.error("交易id无对应交易处理类，交易id="+onlieContext.getRcvMsgContext().getTradeId());
			return;
		}
		
		if (tradeFlow.runFlow(onlieContext) == true) {
			for (SendMsgContext msg : onlieContext.getSendMsgContext()) {
				SendMsgService sendMsgService=(SendMsgService)ApplicationContextUtil.getBean("sendMsgService");
				sendMsgService.sendMsg(msg);
			}
		}
	}
}
