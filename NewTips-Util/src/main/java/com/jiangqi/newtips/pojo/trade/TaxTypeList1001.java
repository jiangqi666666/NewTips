package com.jiangqi.newtips.pojo.trade;

import java.util.LinkedList;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class TaxTypeList1001 {
	@XStreamOmitField 
	private Integer id;
	@XStreamOmitField 
	private String taxOrgCode;
	@XStreamOmitField
	private String entrustDate;
	@XStreamOmitField
	private Integer traNo;
	
	@XStreamAlias("ProjectId")
	private Integer projectId;
	@XStreamAlias("BudgetSubjectCode")
	private String budgetSubjectCode;
	@XStreamAlias("LimitDate")
	private String limitDate;
	@XStreamAlias("TaxTypeName")
	private String taxTypeName;
	@XStreamAlias("BudgetLevelCode")
	private String budgetLevelCode;
	@XStreamAlias("BudgetLevelName")
	private String budgetLevelName;
	@XStreamAlias("TaxStartDate")
	private String taxStartDate;
	//@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDateConverter.class , strings={"yyyyMMdd"} )
	@XStreamAlias("TaxEndDate")
	private String taxEndDate;
	@XStreamAlias("ViceSign")
	private String viceSign;
	@XStreamAlias("TaxType")
	private String taxType;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("TaxTypeAmt")
	private Double taxTypeAmt;
	
	@XStreamAlias("DetailNum")
	private Integer detailNum;
	@XStreamImplicit
	private LinkedList<TaxSubjectList1001> taxSubjectList1001=new LinkedList<TaxSubjectList1001>();
	
	public void setPk(Integer pk,RealHead1001 realHead1001){
		this.id=pk;
		this.taxOrgCode=realHead1001.getTaxOrgCode();
		this.entrustDate=realHead1001.getEntrustDate();
		this.traNo=realHead1001.getTraNo();
		
		for(TaxSubjectList1001 obj:taxSubjectList1001)
			obj.setPk(pk,this.projectId,realHead1001);
	}
}
