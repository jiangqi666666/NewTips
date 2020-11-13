package com.jiangqi.newtips.dao.database;

import java.util.List;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ShardBy;
import com.jiangqi.newtips.pojo.trade.Payment1001;

@DB(
		table = "PAYMENT1001", 
		dataSourceRouter = MyDataSourceRouter.class,
		tablePartition = ModFiveTablePartition.class
)
public interface Payment1001Dao {

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, PAYACCT, HANDORGNAME, TRAAMT, TAXVOUNO, BILLDATE, TAXPAYCODE, TAXPAYNAME, CORPCODE, PROTOCOLNO, BUDGETTYPE, TRIMSIGN, CORPTYOE, PRINTVOUSIGN, REMARK, REMARK1, REMARK2, TAXTYPENUM) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.payAcct , :1.handOrgName , :1.traAmt , :1.taxVouNo , :1.billDate , :1.taxPayCode , :1.taxPayName , :1.corpCode , :1.protocolNo , :1.budgetType , :1.trimSign , :1.corpType , :1.printVouSign , :1.remark , :1.remark1 , :1.remark2 , :1.taxTypeNum)")
    public void addPayment1001(@ShardBy("id") Payment1001 payment1001 );

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, PAYACCT, HANDORGNAME, TRAAMT, TAXVOUNO, BILLDATE, TAXPAYCODE, TAXPAYNAME, CORPCODE, PROTOCOLNO, BUDGETTYPE, TRIMSIGN, CORPTYOE, PRINTVOUSIGN, REMARK, REMARK1, REMARK2, TAXTYPENUM) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.payAcct , :1.handOrgName , :1.traAmt , :1.taxVouNo , :1.billDate , :1.taxPayCode , :1.taxPayName , :1.corpCode , :1.protocolNo , :1.budgetType , :1.trimSign , :1.corpType , :1.printVouSign , :1.remark , :1.remark1 , :1.remark2 , :1.taxTypeNum)")
    public void batchAddPayment1001(@ShardBy("id") List<Payment1001> payment1001 );
    
	@SQL("select ID, TAXORGCODE, ENTRUSTDATE, TRANO, PAYACCT, HANDORGNAME, TRAAMT, TAXVOUNO, BILLDATE, TAXPAYCODE, TAXPAYNAME, CORPCODE, PROTOCOLNO, BUDGETTYPE, TRIMSIGN, CORPTYOE, PRINTVOUSIGN, REMARK, REMARK1, REMARK2, TAXTYPENUM from #table where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public Payment1001 getPayment1001(@ShardBy("id") Payment1001 payment1001);
	
	@SQL("update #table set ID=:1.id, TAXORGCODE=:1.taxOrgCode, ENTRUSTDATE=:1.entrustDate, TRANO=:1.traNo, PAYACCT=:1.payAcct, HANDORGNAME=:1.handOrgName, TRAAMT=:1.traAmt, TAXVOUNO=:1.taxVouNo, BILLDATE=:1.billDate, TAXPAYCODE=:1.taxPayCode, TAXPAYNAME=:1.taxPayName, CORPCODE=:1.corpCode, PROTOCOLNO=:1.protocolNo, BUDGETTYPE=:1.budgetType, TRIMSIGN=:1.trimSign, CORPTYOE=:1.corpType, PRINTVOUSIGN=:1.printVouSign, REMARK=:1.remark, REMARK1=:1.remark1, REMARK2=:1.remark2, TAXTYPENUM=:1.taxTypeNum  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public int updatePayment1001(@ShardBy("id") Payment1001 payment1001);
	
	@SQL("delete from #table  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
    public int delPayment1001(@ShardBy("id") Payment1001 payment1001);

}
