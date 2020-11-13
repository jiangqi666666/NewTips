package com.jiangqi.newtips.dao.database;

import java.util.List;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ShardBy;
import com.jiangqi.newtips.pojo.trade.VoucherStat1001;

@DB(
		table = "VOUCHERSTAT1001", 
		dataSourceRouter = MyDataSourceRouter.class,
		tablePartition = ModFiveTablePartition.class
)
public interface VoucherStat1001Dao {

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, PAYEEBANKNO, PAYEEORGCODE, PAYBKCODE, PAYOPBKCODE, PAYACCT, HANDORGNAME, TRAAMT, ACCEPTDATE, ENDDATE, STATE, VERSION) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.payeeBankNo , :1.payeeOrgCode , :1.payBkCode , :1.payOpBkCode , :1.payAcct , :1.handOrgName , :1.traAmt , :1.acceptDate , :1.endDate , :1.state , :1.version)")
    public void addVoucherStat1001(@ShardBy("id") VoucherStat1001 voucherStat1001 );

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, PAYEEBANKNO, PAYEEORGCODE, PAYBKCODE, PAYOPBKCODE, PAYACCT, HANDORGNAME, TRAAMT, ACCEPTDATE, ENDDATE, STATE, VERSION) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.payeeBankNo , :1.payeeOrgCode , :1.payBkCode , :1.payOpBkCode , :1.payAcct , :1.handOrgName , :1.traAmt , :1.acceptDate , :1.endDate , :1.state , :1.version)")
    public void batchAddVoucherStat1001(@ShardBy("id") List<VoucherStat1001> voucherStat1001 );
    
	@SQL("select ID, TAXORGCODE, ENTRUSTDATE, TRANO, PAYEEBANKNO, PAYEEORGCODE, PAYBKCODE, PAYOPBKCODE, PAYACCT, HANDORGNAME, TRAAMT, ACCEPTDATE, ENDDATE, STATE, VERSION from #table where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public VoucherStat1001 getVoucherStat1001(@ShardBy("id") VoucherStat1001 voucherStat1001);
	
	@SQL("update #table set ID=:1.id, TAXORGCODE=:1.taxOrgCode, ENTRUSTDATE=:1.entrustDate, TRANO=:1.traNo, PAYEEBANKNO=:1.payeeBankNo, PAYEEORGCODE=:1.payeeOrgCode, PAYBKCODE=:1.payBkCode, PAYOPBKCODE=:1.payOpBkCode, PAYACCT=:1.payAcct, HANDORGNAME=:1.handOrgName, TRAAMT=:1.traAmt, ACCEPTDATE=:1.acceptDate, ENDDATE=:1.endDate, STATE=:1.state, VERSION=:1.version  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public int updateVoucherStat1001(@ShardBy("id") VoucherStat1001 voucherStat1001);
	
	@SQL("delete from #table  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
    public int delVoucherStat1001(@ShardBy("id") VoucherStat1001 voucherStat1001);

}
