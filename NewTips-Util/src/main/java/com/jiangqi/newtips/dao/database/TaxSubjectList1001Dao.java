package com.jiangqi.newtips.dao.database;

import java.util.List;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ShardBy;
import com.jiangqi.newtips.pojo.trade.TaxSubjectList1001;

@DB(
		table = "TAXSUBJECTLIST1001", 
		dataSourceRouter = MyDataSourceRouter.class,
		tablePartition = ModFiveTablePartition.class
)
public interface TaxSubjectList1001Dao {

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, PROJECTID, DETAILNO, TAXSUBJECTCODE, TAXSUBJECTNAME, TAXNUMBER, TAXAMT, TAXRATE, EXPTAXAMT, DISCOUNTTAXAMT, FACTTAXAMT) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.projectId , :1.detailNo , :1.taxSubjectCode , :1.taxSubjectName , :1.taxNumber , :1.taxAmt , :1.taxRate , :1.expTaxAmt , :1.discountTaxAmt , :1.factTaxAmt)")
    public void addTaxSubjectList1001(@ShardBy("id") TaxSubjectList1001 taxSubjectList1001 );

    @SQL("insert into #table(ID, TAXORGCODE, ENTRUSTDATE, TRANO, PROJECTID, DETAILNO, TAXSUBJECTCODE, TAXSUBJECTNAME, TAXNUMBER, TAXAMT, TAXRATE, EXPTAXAMT, DISCOUNTTAXAMT, FACTTAXAMT) values( :1.id , :1.taxOrgCode , :1.entrustDate , :1.traNo , :1.projectId , :1.detailNo , :1.taxSubjectCode , :1.taxSubjectName , :1.taxNumber , :1.taxAmt , :1.taxRate , :1.expTaxAmt , :1.discountTaxAmt , :1.factTaxAmt)")
    public void batchAddTaxSubjectList1001(@ShardBy("id") List<TaxSubjectList1001> taxSubjectList1001 );
    
	@SQL("select ID, TAXORGCODE, ENTRUSTDATE, TRANO, PROJECTID, DETAILNO, TAXSUBJECTCODE, TAXSUBJECTNAME, TAXNUMBER, TAXAMT, TAXRATE, EXPTAXAMT, DISCOUNTTAXAMT, FACTTAXAMT from #table where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo and PROJECTID=:1.projectId and DETAILNO=:1.detailNo")
	public TaxSubjectList1001 getTaxSubjectList1001(@ShardBy("id") TaxSubjectList1001 taxSubjectList1001);
	
	@SQL("update #table set ID=:1.id, TAXORGCODE=:1.taxOrgCode, ENTRUSTDATE=:1.entrustDate, TRANO=:1.traNo, PROJECTID=:1.projectId, DETAILNO=:1.detailNo, TAXSUBJECTCODE=:1.taxSubjectCode, TAXSUBJECTNAME=:1.taxSubjectName, TAXNUMBER=:1.taxNumber, TAXAMT=:1.taxAmt, TAXRATE=:1.taxRate, EXPTAXAMT=:1.expTaxAmt, DISCOUNTTAXAMT=:1.discountTaxAmt, FACTTAXAMT=:1.factTaxAmt  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo and PROJECTID=:1.projectId and DETAILNO=:1.detailNo")
	public int updateTaxSubjectList1001(@ShardBy("id") TaxSubjectList1001 taxSubjectList1001);
	
	@SQL("delete from #table  where ID=:1.id and TAXORGCODE=:1.taxOrgCode and ENTRUSTDATE=:1.entrustDate and TRANO=:1.traNo and PROJECTID=:1.projectId and DETAILNO=:1.detailNo")
    public int delTaxSubjectList1001(@ShardBy("id") TaxSubjectList1001 taxSubjectList1001);

}
