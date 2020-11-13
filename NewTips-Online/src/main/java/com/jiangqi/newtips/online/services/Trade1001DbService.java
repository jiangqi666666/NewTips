package com.jiangqi.newtips.online.services;

import java.util.Date;
import org.apache.log4j.Logger;
import org.jfaster.mango.transaction.Transaction;
import org.jfaster.mango.transaction.TransactionFactory;
import com.jiangqi.newtips.dao.database.MyDataSourceRouter;
import com.jiangqi.newtips.enuml.VoucherSendState;
import com.jiangqi.newtips.enuml.VoucherState;
import com.jiangqi.newtips.online.control.MangoBean;
import com.jiangqi.newtips.online.control.MangoPool;
import com.jiangqi.newtips.online.exception.onlie.OnlieException;
import com.jiangqi.newtips.pojo.trade.Trade1001;
import com.jiangqi.newtips.pojo.trade.TradeKey;
import com.jiangqi.newtips.pojo.trade.VoucherSend1001;
import com.jiangqi.newtips.pojo.trade.VoucherStat1001;

import lombok.Getter;
import lombok.Setter;

/**
 * 1001交易数据库操作服务
 * @author jiangqi
 *
 */
@Getter  
@Setter
public class Trade1001DbService {
	private static Logger logger = Logger.getLogger(Trade1001DbService.class);
		
	/**
	 * 保存1001凭证状态，并将1001报文放入转发表
	 * @param trade	1001交易信息
	 * @param tipsWorkDate	tips工作日期
	 * @param msg	收到的1001报文信息
	 * @throws OnlieException
	 */
	public  TradeKey saveTrade1001(Trade1001 trade,String tipsWorkDate,String msg)throws OnlieException{
		logger.info("saveTrade1001 start");
		
		String dbs=MyDataSourceRouter.getName(trade.getMsg().getPayment1001().getId(),0);	
		Transaction tx =null;
		try {
			MangoBean mango=MangoPool.getMangoBean(Thread.currentThread().getId());
			
			tx = TransactionFactory.newTransaction(mango.getMango(),dbs);
			//tx = TransactionFactory.newTransaction(mango,dbs);
			//tx = TransactionFactory.newTransaction( mango,dbs,TransactionIsolationLevel.READ_COMMITTED);
			
			VoucherStat1001 bean=new VoucherStat1001();
			bean.setId(trade.getMsg().getTurnAccount1001().getId());
			bean.setTaxOrgCode(trade.getMsg().getTurnAccount1001().getTaxOrgCode());
			bean.setEntrustDate(trade.getMsg().getTurnAccount1001().getEntrustDate());
			bean.setTraNo(trade.getMsg().getTurnAccount1001().getTraNo());
			bean.setPayeeBankNo(trade.getMsg().getTurnAccount1001().getPayeeBankNo());
			bean.setPayeeOrgCode(trade.getMsg().getTurnAccount1001().getPayeeOrgCode());
			bean.setPayBkCode(trade.getMsg().getTurnAccount1001().getPayBkCode());
			bean.setPayOpBkCode(trade.getMsg().getTurnAccount1001().getPayOpBkCode());
			bean.setPayAcct(trade.getMsg().getTurnAccount1001().getPayeeAcct());
			bean.setHandOrgName(trade.getMsg().getPayment1001().getHandOrgName());
			bean.setTraAmt(trade.getMsg().getPayment1001().getTraAmt());
			
			bean.setAcceptDate(tipsWorkDate);
			bean.setEndDate(null);
			bean.setState(VoucherState.ACCEPT.ordinal());
			bean.setVersion(0);
			
			VoucherSend1001 sendmsg=new VoucherSend1001();
			sendmsg.setId(trade.getMsg().getTurnAccount1001().getId());
			sendmsg.setTaxOrgCode(trade.getMsg().getTurnAccount1001().getTaxOrgCode());
			sendmsg.setEntrustDate(trade.getMsg().getTurnAccount1001().getEntrustDate());
			sendmsg.setTraNo(trade.getMsg().getTurnAccount1001().getTraNo());
			sendmsg.setMsg(msg);
			sendmsg.setState(VoucherSendState.SENT.ordinal());
			sendmsg.setUpdateTime(new Date());
			
			TradeKey tradeKey=new TradeKey();
			tradeKey.setId(sendmsg.getId());
			tradeKey.setEntrustDate(sendmsg.getEntrustDate());
			tradeKey.setTaxOrgCode(sendmsg.getTaxOrgCode());
			tradeKey.setTraNo(sendmsg.getTraNo());
			
			mango.getVoucherStat1001Dao().addVoucherStat1001(bean);
			mango.getVoucherSend1001ExternDao().addVoucherSend1001(sendmsg);
			
			tx.commit();
			
			return tradeKey;
		}catch (Throwable e) {
            logger.error(e,e.fillInStackTrace());
            tx.rollback();
            throw new OnlieException("9002","数据库操作错误","数据库操作错误");
        }
	}
	
	/**
	 * 更改凭证发送状态为发送成功<p>
	 * 状态更改失败自动忽略
	 * @param tradeKey	凭证发送信息
	 */
	public void updateVoucherSend(TradeKey tradeKey){
		logger.info("updateVoucherSend start");
		
		String dbs=MyDataSourceRouter.getName(tradeKey.getId(),0);	
		Transaction tx =null;
		try {
			MangoBean mango=MangoPool.getMangoBean(Thread.currentThread().getId());
			
			tx = TransactionFactory.newTransaction(mango.getMango(),dbs);
			
			VoucherSend1001 bean=new VoucherSend1001();
			bean.setId(tradeKey.getId());
			bean.setEntrustDate(tradeKey.getEntrustDate());
			bean.setTaxOrgCode(tradeKey.getTaxOrgCode());
			bean.setTraNo(tradeKey.getTraNo());
			bean.setState(VoucherSendState.SUCCESS.ordinal());
			
			mango.getVoucherSend1001ExternDao().updateVoucherSendState1001(bean);
			tx.commit();
		}catch (Throwable e) {
            logger.error(e,e.fillInStackTrace());
            tx.rollback();
        }
	}
}
