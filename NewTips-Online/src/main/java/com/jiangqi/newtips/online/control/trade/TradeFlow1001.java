package com.jiangqi.newtips.online.control.trade;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import com.jiangqi.newtips.context.OnlineConstant;
import com.jiangqi.newtips.enuml.OrgType;
import com.jiangqi.newtips.online.context.SendMsgContext;
import com.jiangqi.newtips.online.exception.onlie.OnlieException;
import com.jiangqi.newtips.online.services.Msg3001Service;
import com.jiangqi.newtips.online.services.SendMsgService;
import com.jiangqi.newtips.online.services.Trade1001DbService;
import com.jiangqi.newtips.pojo.trade.Head;
import com.jiangqi.newtips.pojo.trade.Trade1001;
import com.jiangqi.newtips.pojo.trade.Trade9120;
import com.jiangqi.newtips.pojo.trade.TradeKey;
import com.jiangqi.newtips.rules.api.MsgHandleService;
import com.jiangqi.newtips.util.ApplicationContextUtil;

public class TradeFlow1001 extends TradeFlowTemplet {
	private static Logger logger = Logger.getLogger(TradeFlow1001.class);
	
	private MsgHandleService msgHandleService;
	private Trade1001DbService trade1001DbService;
	private Msg3001Service msg3001Service;
	private SendMsgService sendMsgService;
	
	/**
	 * 1001交易的唯一id，用来匹配本交易
	 */
	private String tradeUUid;
	
	@Override
	protected void init() {
		logger.info("init start");
		
		super.init();
		msgHandleService=(MsgHandleService)ApplicationContextUtil.getBean("msgHandleService");
		trade1001DbService=(Trade1001DbService)ApplicationContextUtil.getBean("trade1001DbService");
		msg3001Service=(Msg3001Service)ApplicationContextUtil.getBean("msg3001Service");
		sendMsgService=(SendMsgService)ApplicationContextUtil.getBean("sendMsgService");
	}

	@Override
	protected void saveTradeid() throws OnlieException {
		logger.info("saveTradeid start");
		
		this.tradeKeyService.trade1001SaveKey(this.tradeUUid);
	}

	@Override
	protected void callCustomRemote() throws OnlieException {
		logger.info("callCustomRemote start");
		
		saveConvertMsg();
	}
	
	/**
	 * 预生成3001报文
	 * @throws OnlieException
	 */
	private void saveConvertMsg() throws OnlieException{
		
		logger.info("saveConvertMsg start");
		
		Trade1001 bean = (Trade1001) this.beans.get(OnlineConstant.TRADE_FLOW_BEAN_NAME);
		Head head=createHead(bean);
		this.msgHandleService.advancedMsg3001(this.tradeUUid, (Trade1001)this.beans.get(OnlineConstant.TRADE_FLOW_BEAN_NAME), head);
	}
	

	@Override
	protected void prepare() throws OnlieException {
		logger.info("prepare start");
		
		super.prepare();
	
		String keyTmp = "%s.%s.%d";
		Trade1001 bean = (Trade1001) this.beans.get(OnlineConstant.TRADE_FLOW_BEAN_NAME);
		this.tradeUUid=String.format(keyTmp, bean.getMsg().getRealHead1001().getTaxOrgCode(),bean.getMsg().getRealHead1001().getEntrustDate(),bean.getMsg().getRealHead1001().getTraNo());
		
		bean.setPk(Math.abs(this.tradeUUid.hashCode()));
		
		bean.getMsg().getTurnAccount1001().setPayeeBankNo(this.paramSelectService.getReckonTreCode(bean.getMsg().getTurnAccount1001().getPayeeOrgCode()));
	}

	@Override
	protected void saveTrade() throws OnlieException {

		logger.info("saveTrade start");
		super.saveTrade();

		
		Trade1001 bean = (Trade1001) this.beans.get(OnlineConstant.TRADE_FLOW_BEAN_NAME);
		TradeKey tradeKey=this.trade1001DbService.saveTrade1001(bean,this.tipsWorkDate,this.onlieContext.getRcvMsgContext().getMsg());
		
		try{
			this.sendMsgService.sendVoucher(this.onlieContext.getRcvMsgContext().getMsg());
			this.trade1001DbService.updateVoucherSend(tradeKey);
		}catch(AmqpException e){
			logger.error(e,e.fillInStackTrace());
		}
	}

	@Override
	protected void returnMsg() throws OnlieException {
		logger.info("returnMsg start");
		
		super.returnMsg();
		
		ret3001();
		ret9120();
		
	}
	
	/**
	 * 向返回队列中添加3001报文相关信息
	 * @throws OnlieException
	 */
	private void ret3001()throws OnlieException{
		logger.info("ret3001 start");
		
		Trade1001 bean = (Trade1001) this.beans.get(OnlineConstant.TRADE_FLOW_BEAN_NAME);
		
		Head head=createHead(bean);
		
		try {
			String msg=this.msg3001Service.create3001(this.tradeUUid, bean, head, msgParse);
			
			SendMsgContext sendMsg = new SendMsgContext();
			sendMsg.setSendNode(bean.getHead().getDes());
			sendMsg.setTradeId(OnlineConstant.TIPS_NODE);
			sendMsg.setMsgType(this.onlieContext.getRcvMsgContext().getMsgType());
			sendMsg.setVersion(this.onlieContext.getRcvMsgContext().getVersion());
			sendMsg.setMsg(msg);
			
			this.onlieContext.getSendMsgContext().add(sendMsg);
		} catch (IllegalAccessException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90005","bbbbbb","aaaa");
		} catch (ClassNotFoundException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90005","bbbbbb","aaaa");
		} catch (InvocationTargetException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90005","bbbbbb","aaaa");
		} catch (InstantiationException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90005","bbbbbb","aaaa");
		} catch (IntrospectionException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90005","bbbbbb","aaaa");
		} catch (IOException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90005","bbbbbb","aaaa");
		}
	}
	
	/**
	 * 创建3001报文头
	 * @param bean 1001消息类型
	 * @return	创建的3001报文头
	 * @throws OnlieException
	 */
	private Head createHead(Trade1001 bean )throws OnlieException{
		logger.info("createHead start");
		
		String node=this.paramSelectService.getNode(bean.getMsg().getTurnAccount1001().getPayBkCode(), OrgType.BANK_TYPE);
		
		Head head=new Head();
		head.setVer(head.getVer());
		head.setSrc(OnlineConstant.TIPS_NODE);
		head.setDes(node);
		head.setApp(head.getApp());
		head.setMsgNo("3001");
		head.setMsgID(head.getMsgRef());
		head.setMsgRef(head.getMsgRef());
		head.setWorkDate(this.tipsWorkDate);
		
		return head;
	}
	
	/**
	 * 创建返回成功9120
	 * @throws OnlieException
	 */
	private void ret9120()throws OnlieException{
		logger.info("ret9120 start");
		
		try {
			Trade9120 ret = this.msg9120Service.createSuccess9120(this.beans.get("bean"),this.tipsWorkDate);

			SendMsgContext sendMsg = new SendMsgContext();
			sendMsg.setSendNode(ret.getHead().getDes());
			sendMsg.setTradeId(OnlineConstant.TIPS_NODE);
			sendMsg.setMsgType(this.onlieContext.getRcvMsgContext().getMsgType());
			sendMsg.setVersion(this.onlieContext.getRcvMsgContext().getVersion());
			sendMsg.setMsg(this.msgParse.saveBean(ret,"marshaller9120"));

			this.onlieContext.getSendMsgContext().add(sendMsg);
		} catch (Exception e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException("90005","bbbbbb","aaaa");
		}
	}

}
