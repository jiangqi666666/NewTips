package com.jiangqi.newtips.online.control;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.jfaster.mango.datasource.MultipleDataSourceFactory;
import org.jfaster.mango.operator.Mango;
import com.jiangqi.newtips.dao.database.VoucherSend1001ExternDao;
import com.jiangqi.newtips.dao.database.VoucherStat1001Dao;
import com.jiangqi.newtips.util.ApplicationContextUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * mango对象池，每个线程以id为key创建一个mango对象
 * @author jiangqi
 *
 */
@Getter
@Setter
public class MangoPool {
	private static Logger logger = Logger.getLogger(MangoPool.class);
	
	private static HashMap<Long,MangoBean> pools=new  HashMap<Long,MangoBean>();
	
	/**
	 * 获得本线程对应的mango对象<p>
	 * 如mango对象不存在则系统会创建一个并存放到池中
	 * @param threadId
	 * @return
	 */
	public static synchronized MangoBean getMangoBean(long threadId){
		logger.info("getMangoBean start");
		
		MangoBean tmp=pools.get(threadId);
		if(tmp==null){
			tmp=new MangoBean();
			MultipleDataSourceFactory multipleDataSourceFactory=(MultipleDataSourceFactory)ApplicationContextUtil.getBean("dsf",MultipleDataSourceFactory.class);
			tmp.setMango(Mango.newInstance(multipleDataSourceFactory));
			tmp.setVoucherSend1001ExternDao(tmp.getMango().create(VoucherSend1001ExternDao.class));
			tmp.setVoucherStat1001Dao(tmp.getMango().create(VoucherStat1001Dao.class));
			
			pools.put(threadId, tmp);
		}
		
		return tmp;
	}
}
