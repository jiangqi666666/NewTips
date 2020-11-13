package com.jiangqi.newtips.dao.database;

import java.util.List;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ShardBy;
import com.jiangqi.newtips.pojo.trade.VoucherSend1001;

@DB(
		table = "VOUCHERSEND1001", 
		dataSourceRouter = MyDataSourceRouter.class,
		tablePartition = ModFiveTablePartition.class
)
public interface VoucherSend1001Dao {

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, MSG, STATE, UPDATETIME) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.msg , :1.state , :1.updateTime)")
    public void addVoucherSend1001(@ShardBy("id") VoucherSend1001 voucherSend1001 );

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, MSG, STATE, UPDATETIME) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.msg , :1.state , :1.updateTime)")
    public void batchAddVoucherSend1001(@ShardBy("id") List<VoucherSend1001> voucherSend1001 );
    
	@SQL("select ID, TAXORGCODE, ENTRUSTDATE, TRANO, MSG, STATE, UPDATETIME from #table where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public VoucherSend1001 getVoucherSend1001(@ShardBy("id") VoucherSend1001 voucherSend1001);
	
	@SQL("update #table set ID=:1.id, TAXORGCODE=:1.taxOrgCode, ENTRUSTDATE=:1.entrustDate, TRANO=:1.traNo, MSG=:1.msg, STATE=:1.state, UPDATETIME=:1.updateTime  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public int updateVoucherSend1001(@ShardBy("id") VoucherSend1001 voucherSend1001);
	
	@SQL("delete from #table  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
    public int delVoucherSend1001(@ShardBy("id") VoucherSend1001 voucherSend1001);

}
