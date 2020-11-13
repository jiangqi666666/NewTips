package com.jiangqi.newtips.online.control;

import org.jfaster.mango.operator.Mango;

import com.jiangqi.newtips.dao.database.VoucherSend1001ExternDao;
import com.jiangqi.newtips.dao.database.VoucherStat1001Dao;

import lombok.Getter;
import lombok.Setter;

/**
 * 每个线程独立使用的mango
 * @author jiangqi
 *
 */
@Getter
@Setter
public class MangoBean {
	/**
	 * mango对象
	 */
	private Mango mango;
	
	/**
	 * mango对象会使用的dao
	 */
	private VoucherSend1001ExternDao voucherSend1001ExternDao;
	private VoucherStat1001Dao voucherStat1001Dao;
	
}
