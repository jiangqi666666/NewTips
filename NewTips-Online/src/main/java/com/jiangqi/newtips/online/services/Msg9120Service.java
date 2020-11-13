package com.jiangqi.newtips.online.services;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import com.jiangqi.newtips.context.OnlineConstant;
import com.jiangqi.newtips.online.exception.onlie.OnlieException;
import com.jiangqi.newtips.pojo.trade.Head;
import com.jiangqi.newtips.pojo.trade.MsgReturn9120;
import com.jiangqi.newtips.pojo.trade.Trade9120;

/**
 * 9120报文服务
 * @author jiangqi
 *
 */
public class Msg9120Service {
	private static Logger logger = Logger.getLogger(Msg9120Service.class);
	
	/**
	 * 创建9120报文头
	 * @param head	9120报文头
	 * @param tipsWorkDate tips工作日期
	 * @return
	 */
	private Head creatHead(Head head,String tipsWorkDate){
		
		Head bean=new Head();
		
		bean.setVer(head.getVer());
		bean.setSrc(OnlineConstant.TIPS_NODE);
		bean.setDes(head.getSrc());
		bean.setApp(head.getApp());
		bean.setMsgNo("9120");
		bean.setMsgID("AAAAA");
		bean.setMsgRef(head.getMsgRef());
		bean.setWorkDate(tipsWorkDate);
		
		return bean;
	}
	
	/**
	 * 创建9120报文
	 * @param head 9120报文头
	 * @param err 联机交易异常类
	 * @return
	 */
	private MsgReturn9120 createMsg(Head head,OnlieException err){
		MsgReturn9120 bean=new MsgReturn9120();
		bean.setOriMsgNo(head.getMsgNo());
		bean.setResult(err.getResult());
		bean.setAddWord(err.getAddWord());
		bean.setInformation(err.getInformation());
		
		return bean;
	}
	
	/**
	 * 创建错误9120应答
	 * @param bean	9120对应交易信息
	 * @param tipsWorkDate tips工作日期
	 * @param err 联机交易异常类
	 * @return
	 * @throws Exception
	 */
	public Trade9120 createErr9120(Object bean,String tipsWorkDate, OnlieException err) throws Exception {
		logger.info("createErr9120");

		Field field = bean.getClass().getDeclaredField("head");
		PropertyDescriptor pd = new PropertyDescriptor(field.getName(), bean.getClass());
		Method beanMethod = pd.getReadMethod();
		Head head = (Head) beanMethod.invoke(bean);

		Trade9120 ret9120=new Trade9120();
		ret9120.setHead(creatHead(head,tipsWorkDate));
		ret9120.setMsg(createMsg(head,err));
		return ret9120;
	}
	
	/**
	 * 创建成功9120应答
	 * @param bean	9120对应交易信息
	 * @param tipsWorkDate tips工作日期
	 * @return
	 * @throws Exception
	 */
	public Trade9120 createSuccess9120(Object bean,String tipsWorkDate) throws Exception {
		logger.info("createSuccess9120");

		Field field = bean.getClass().getDeclaredField("head");
		PropertyDescriptor pd = new PropertyDescriptor(field.getName(), bean.getClass());
		Method beanMethod = pd.getReadMethod();
		Head head = (Head) beanMethod.invoke(bean);

		Trade9120 ret9120=new Trade9120();
		ret9120.setHead(creatHead(head,tipsWorkDate));
		
		OnlieException msg=new OnlieException("90000","受理成功已转发","受理成功已转发");
		ret9120.setMsg(createMsg(head,msg));
		return ret9120;
	}
}
