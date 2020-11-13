package com.jiangqi.newtips.dao.database;

import java.util.List;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ShardBy;
import com.jiangqi.newtips.pojo.trade.TurnAccount1001;

@DB(
		table = "TURNACCOUNT1001", 
		dataSourceRouter = MyDataSourceRouter.class,
		tablePartition = ModFiveTablePartition.class
)
public interface TurnAccount1001Dao {

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, HANDLETYPE, PAYEEBANKNO, PAYEEORGCODE, PAYEEACCT, PAYEENAME, PAYBKCODE, PAYOPBKCODE) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.handleType , :1.payeeBankNo , :1.payeeOrgCode , :1.payeeAcct , :1.payeeName , :1.payBkCode , :1.payOpBkCode)")
    public void addTurnAccount1001(@ShardBy("id") TurnAccount1001 turnAccount1001 );

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, HANDLETYPE, PAYEEBANKNO, PAYEEORGCODE, PAYEEACCT, PAYEENAME, PAYBKCODE, PAYOPBKCODE) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.handleType , :1.payeeBankNo , :1.payeeOrgCode , :1.payeeAcct , :1.payeeName , :1.payBkCode , :1.payOpBkCode)")
    public void batchAddTurnAccount1001(@ShardBy("id") List<TurnAccount1001> turnAccount1001 );
    
	@SQL("select ID, TAXORGCODE, ENTRUSTDATE, TRANO, HANDLETYPE, PAYEEBANKNO, PAYEEORGCODE, PAYEEACCT, PAYEENAME, PAYBKCODE, PAYOPBKCODE from #table where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public TurnAccount1001 getTurnAccount1001(@ShardBy("id") TurnAccount1001 turnAccount1001);
	
	@SQL("update #table set ID=:1.id, TAXORGCODE=:1.taxOrgCode, ENTRUSTDATE=:1.entrustDate, TRANO=:1.traNo, HANDLETYPE=:1.handleType, PAYEEBANKNO=:1.payeeBankNo, PAYEEORGCODE=:1.payeeOrgCode, PAYEEACCT=:1.payeeAcct, PAYEENAME=:1.payeeName, PAYBKCODE=:1.payBkCode, PAYOPBKCODE=:1.payOpBkCode  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
	public int updateTurnAccount1001(@ShardBy("id") TurnAccount1001 turnAccount1001);
	
	@SQL("delete from #table  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo")
    public int delTurnAccount1001(@ShardBy("id") TurnAccount1001 turnAccount1001);

}
