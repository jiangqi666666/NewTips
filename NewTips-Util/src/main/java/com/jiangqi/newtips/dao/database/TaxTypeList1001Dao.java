package com.jiangqi.newtips.dao.database;

import java.util.List;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ShardBy;
import com.jiangqi.newtips.pojo.trade.TaxTypeList1001;

@DB(
		table = "TAXTYPELIST1001", 
		dataSourceRouter = MyDataSourceRouter.class,
		tablePartition = ModFiveTablePartition.class
)
public interface TaxTypeList1001Dao {

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, PROJECTID, BUDGETSUBJECTCODE, LIMITDATE, TAXTYPENAME, BUDGETLEVELCODE, BUDGETLEVELNAME, TAXSTARTDATE, TAXENDDATE, VICESIGN, TAXTYPE, TAXTYPEAMT, DETAILNUM) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.projectId , :1.budgetSubjectCode , :1.limitDate , :1.taxTypeName , :1.budgetLevelCode , :1.budgetLevelName , :1.taxStartDate , :1.taxEndDate , :1.viceSign , :1.taxType , :1.taxTypeAmt , :1.detailNum)")
    public void addTaxTypeList1001(@ShardBy("id") TaxTypeList1001 taxTypeList1001 );

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, PROJECTID, BUDGETSUBJECTCODE, LIMITDATE, TAXTYPENAME, BUDGETLEVELCODE, BUDGETLEVELNAME, TAXSTARTDATE, TAXENDDATE, VICESIGN, TAXTYPE, TAXTYPEAMT, DETAILNUM) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.projectId , :1.budgetSubjectCode , :1.limitDate , :1.taxTypeName , :1.budgetLevelCode , :1.budgetLevelName , :1.taxStartDate , :1.taxEndDate , :1.viceSign , :1.taxType , :1.taxTypeAmt , :1.detailNum)")
    public void batchAddTaxTypeList1001(@ShardBy("id") List<TaxTypeList1001> taxTypeList1001 );
    
	@SQL("select ID, TAXORGCODE, ENTRUSTDATE, TRANO, PROJECTID, BUDGETSUBJECTCODE, LIMITDATE, TAXTYPENAME, BUDGETLEVELCODE, BUDGETLEVELNAME, TAXSTARTDATE, TAXENDDATE, VICESIGN, TAXTYPE, TAXTYPEAMT, DETAILNUM from #table where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo and PROJECTID=:1.projectId")
	public TaxTypeList1001 getTaxTypeList1001(@ShardBy("id") TaxTypeList1001 taxTypeList1001);
	
	@SQL("update #table set ID=:1.id, TAXORGCODE=:1.taxOrgCode, ENTRUSTDATE=:1.entrustDate, TRANO=:1.traNo, PROJECTID=:1.projectId, BUDGETSUBJECTCODE=:1.budgetSubjectCode, LIMITDATE=:1.limitDate, TAXTYPENAME=:1.taxTypeName, BUDGETLEVELCODE=:1.budgetLevelCode, BUDGETLEVELNAME=:1.budgetLevelName, TAXSTARTDATE=:1.taxStartDate, TAXENDDATE=:1.taxEndDate, VICESIGN=:1.viceSign, TAXTYPE=:1.taxType, TAXTYPEAMT=:1.taxTypeAmt, DETAILNUM=:1.detailNum  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo and PROJECTID=:1.projectId")
	public int updateTaxTypeList1001(@ShardBy("id") TaxTypeList1001 taxTypeList1001);
	
	@SQL("delete from #table  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo and PROJECTID=:1.projectId")
    public int delTaxTypeList1001(@ShardBy("id") TaxTypeList1001 taxTypeList1001);

}
