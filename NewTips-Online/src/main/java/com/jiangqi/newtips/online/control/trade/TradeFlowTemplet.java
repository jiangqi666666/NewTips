package com.jiangqi.newtips.online.control.trade;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;
import com.alibaba.dubbo.rpc.RpcContext;
import com.jiangqi.newtips.context.OnlineConstant;
import com.jiangqi.newtips.context.ResultContext;
import com.jiangqi.newtips.online.context.OnlieContext;
import com.jiangqi.newtips.online.context.OnlieInfoContext;
import com.jiangqi.newtips.online.context.SendMsgContext;
import com.jiangqi.newtips.online.exception.onlie.OnlieException;
import com.jiangqi.newtips.online.services.CheckRuleService;
import com.jiangqi.newtips.online.services.LocalBussinessRuleService;
import com.jiangqi.newtips.online.services.Msg9120Service;
import com.jiangqi.newtips.online.services.ParamSelectService;
import com.jiangqi.newtips.online.services.RemoteBussinessRuleService;
import com.jiangqi.newtips.online.services.TradeKeyService;
import com.jiangqi.newtips.pojo.checkrule.RuleGroup;
import com.jiangqi.newtips.pojo.trade.Trade9120;
import com.jiangqi.newtips.rules.api.RetResult;
import com.jiangqi.newtips.rules.api.RetResultSet;
import com.jiangqi.newtips.rules.api.SchemaVerifyService;
import com.jiangqi.newtips.serialize.MessageParse;
import com.jiangqi.newtips.serialize.online.checkrule.CheckGroupMap;
import com.jiangqi.newtips.serialize.online.checkrule.TradeRuleMap;
import com.jiangqi.newtips.util.ApplicationContextUtil;

/**
 * 校验流程控制模板类
 * 
 * @author jiangqi
 *
 */
public abstract class TradeFlowTemplet implements TradeFlow {
	private static Logger logger = Logger.getLogger(TradeFlowTemplet.class);

	/**
	 * 远程校验规则组，系统根据checkrule.xml配置自动生成
	 */
	protected HashMap<String, RuleGroup> remoteGroupContext = new HashMap<String, RuleGroup>();
	
	/**
	 * 远程业务规则校验异步返回结果
	 */
	protected LinkedList<Future<RetResultSet>> remoteRet = new LinkedList<Future<RetResultSet>>();
	
	/**
	 * 存放规则校验所需参数bean，报文解析后的并默认存放key=bean
	 */
	protected HashMap<String, Object> beans = new HashMap<String, Object>();

	/**
	 * 本地校验规则组，系统根据checkrule.xml配置自动生成
	 */
	protected RuleGroup localGroupContext;
	protected OnlieContext onlieContext;
	
	private CheckRuleService checkRuleService;
	protected TradeKeyService tradeKeyService;
	private RemoteBussinessRuleService remoteBussinessRuleService;
	private LocalBussinessRuleService localBussinessRuleService;
	protected MessageParse msgParse;
	protected Msg9120Service msg9120Service;
	protected ParamSelectService paramSelectService;
	private SchemaVerifyService schemaVerifyService;
	
	/**
	 * tips系统工作日期
	 */
	protected String tipsWorkDate;
	
	protected void init(){
		checkRuleService=(CheckRuleService)ApplicationContextUtil.getBean("checkRuleService");
		tradeKeyService=(TradeKeyService)ApplicationContextUtil.getBean("tradeKeyService");
		remoteBussinessRuleService=(RemoteBussinessRuleService)ApplicationContextUtil.getBean("remoteBussinessRuleService");
		localBussinessRuleService=(LocalBussinessRuleService)ApplicationContextUtil.getBean("localBussinessRuleService");
		msgParse=(MessageParse)ApplicationContextUtil.getBean("msgParse");
		msg9120Service=(Msg9120Service)ApplicationContextUtil.getBean("msg9120Service");
		paramSelectService=(ParamSelectService)ApplicationContextUtil.getBean("paramSelectService");
		schemaVerifyService=(SchemaVerifyService)ApplicationContextUtil.getBean("schemaVerifyService");
	}
	
	@Override
	public boolean runFlow(OnlieContext onlieContext) {
		// TODO Auto-generated method stub
		logger.info("runFlow start");

		this.onlieContext=onlieContext;
		
		try {
			init();
			parse();
			prepare();
			saveTradeid();
			createRuleContext();
			createCustomRuleContext();
			callRemote();
			callCustomRemote();
			callLocal();
			callCustomLocal();
			checkRet();
			checkCustomRet();
			saveTrade();
			returnMsg();
		} catch (OnlieException e) {
			return returnErr(e);
		} catch (IOException e) {
			// 报文解析错误，直接丢弃
			logger.error(e,e.fillInStackTrace());
			return false;
		}

		return true;
	}
	
	/**
	 * 创建自定义规则环境<p>
	 * 用户可实现此函数，在其中准备好远程或本地调用的一些环境
	 * 
	 * @throws OnlieException
	 */
	protected void createCustomRuleContext()throws OnlieException{
		logger.info("createCustomRuleContext start");
	}

	/**
	 * 保存凭证状态
	 * <p>
	 * 
	 * @throws OnlieException
	 */
	protected void saveTrade() throws OnlieException {
		logger.info("saveTrade start");
	}

	/**
	 * 返回应答报文
	 * <p>
	 * 子类如需返回应答报文，可在此实现该方法
	 * 
	 * @throws OnlieException
	 */
	protected void returnMsg() throws OnlieException {
		logger.info("returnMsg start");
	}

	/**
	 * 调用用户自定义远程调用返回结果
	 * <p>
	 * 子类可实现该方法，校验自定义的远程异步调用返回结果
	 * 
	 * @throws OnlieException
	 */
	protected void checkCustomRet() throws OnlieException {
		logger.info("checkCustomRet start");
	}

	/**
	 * 调用用户自定义本地调用
	 * @throws OnlieException
	 */
	protected void callCustomLocal() throws OnlieException {
		logger.info("callCustomLocal start");
	}

	/**
	 * 调用用户自定义远程调用
	 * @throws OnlieException
	 */
	protected void callCustomRemote() throws OnlieException {
		logger.info("callCustomRemote start");
	}

	/**
	 * 创建返回系统错误9120相关信息，存放到onlieContext中
	 * 
	 * @param e
	 * @return true 创建成功，false 创建失败，
	 */
	protected boolean returnErr(OnlieException e) {
		logger.info("returnErr start");

		try {
			Trade9120 ret = this.msg9120Service.createErr9120(this.beans.get("bean"),this.tipsWorkDate, e);

			SendMsgContext sendMsg = new SendMsgContext();
			sendMsg.setSendNode(ret.getHead().getDes());
			sendMsg.setTradeId(OnlineConstant.TIPS_NODE);
			sendMsg.setMsgType(this.onlieContext.getRcvMsgContext().getMsgType());
			sendMsg.setVersion(this.onlieContext.getRcvMsgContext().getVersion());
			sendMsg.setMsg(this.msgParse.saveBean(ret,"marshaller9120"));

			this.onlieContext.getSendMsgContext().add(sendMsg);
		} catch (Exception e1) {
			logger.error(e1,e1.fillInStackTrace());
			return false;
		}
		return true;
	}

	/**
	 * 子类调用这个函数向beans中添加参数对象
	 */
	protected void prepare() throws OnlieException {
		logger.info("prepare start");
		
		this.tipsWorkDate=this.paramSelectService.getTipsDate();
	}

	/**
	 * 远程业务规则校验返回结果处理
	 */
	protected void checkRet() throws OnlieException {

		logger.info("checkRet start");

		try {
			RetResultSet ret;
			for (Future<RetResultSet> futrue : this.remoteRet) {
				ret = (RetResultSet) futrue.get();
				for (RetResult result : ret.getResult()) 
					if (result.getResult().equals(ResultContext.SUCCESS) != true)
						throw new OnlieException(result.getResult(), result.getAddWord(), "");
			}
		} catch (InterruptedException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ResultContext.SYS_ERR, "系统错误！", "");
		} catch (ExecutionException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ResultContext.SYS_ERR, "系统错误！", "");
		}
	}

	/**
	 * 调用本地业务规则校验
	 */
	protected void callLocal() throws OnlieException {
		logger.info("callLocal start");

		this.localBussinessRuleService.checkRule(this.localGroupContext.getRules(), this.localGroupContext.getParams());
	}

	/**
	 * 调用远程业务规则校验
	 */
	@SuppressWarnings("unchecked")
	protected void callRemote() throws OnlieException {
		logger.info("callRemote start");
		
		schemaVerifyService.verify(this.onlieContext.getRcvMsgContext().getTradeId(), this.onlieContext.getRcvMsgContext().getMsg());
		Future<?> ret = RpcContext.getContext().getFuture();
		this.remoteRet.add((Future<RetResultSet>) ret);

		RuleGroup tmp;

		for (Entry<String, RuleGroup> entry : this.remoteGroupContext.entrySet()) {
			tmp = entry.getValue();
			logger.info("callRemote.remoteBussinessRuleService.checkRule start");
			this.remoteBussinessRuleService.checkRule(tmp.getRules(), tmp.getParams(), false);
			logger.info("callRemote.remoteBussinessRuleService.checkRule end");
			
			ret = RpcContext.getContext().getFuture();
			this.remoteRet.add((Future<RetResultSet>) ret);
		}
	}

	/**
	 * 报文解析，解析后bean存放在beans中，key=“bean”
	 */
	private void parse() throws IOException {

		logger.info("parse start");

		String tmp = "marshaller" + this.onlieContext.getRcvMsgContext().getTradeId();

		this.beans.put(OnlineConstant.TRADE_FLOW_BEAN_NAME,
				this.msgParse.loadBean(this.onlieContext.getRcvMsgContext().getMsg(),tmp));
	}

	/**
	 * 创建业务规则校验上下文
	 */
	private void createRuleContext() throws OnlieException {
		logger.info("createRuleContext start");

		TradeRuleMap trade = OnlieInfoContext.allCheckRuleMap.getTrades()
				.get(onlieContext.getRcvMsgContext().getTradeId());

		for (CheckGroupMap group : trade.getRemotes()) {
			this.remoteGroupContext.put(group.getGroupId(),
					this.checkRuleService.createRemoteGroupRule(group, this.beans));
		}

		this.localGroupContext = this.checkRuleService.createLocalRule(trade.getLocal(), this.beans);
	}

	/**
	 * 保存交易主键
	 */
	protected void saveTradeid() throws OnlieException {
		logger.info("saveTradeid start");
	}

}
