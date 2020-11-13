package com.jiangqi.newtips.dao.database;


import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ShardBy;
import com.jiangqi.newtips.pojo.trade.VoucherSend1001;

@DB(
		table = "VOUCHERSEND1001", 
		dataSourceRouter = MyDataSourceRouter.class,
		tablePartition = ModFiveTablePartition.class
)
public interface VoucherSend1001ExternDao extends VoucherSend1001Dao{
	@SQL("update #table set STATE=:1.state  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public int updateVoucherSendState1001(@ShardBy("id") VoucherSend1001 voucherSend1001);
}
