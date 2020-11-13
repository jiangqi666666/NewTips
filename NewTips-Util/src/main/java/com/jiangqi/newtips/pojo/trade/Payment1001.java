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
public class Payment1001 {
	@XStreamOmitField 
	private Integer id;
	@XStreamOmitField 
	private String taxOrgCode;
	@XStreamOmitField
	private String entrustDate;
	@XStreamOmitField
	private Integer traNo;
	
	@XStreamAlias("PayAcct")
	private String payAcct;
	@XStreamAlias("HandOrgName")
	private String handOrgName;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("TraAmt")
	private Double traAmt;
	
	@XStreamAlias("TaxVouNo")
	private String taxVouNo;
	@XStreamAlias("BillDate")
	private String billDate;
	@XStreamAlias("TaxPayCode")
	private String taxPayCode;
	@XStreamAlias("TaxPayName")
	private String taxPayName;
	@XStreamAlias("CorpCode")
	private String corpCode;
	@XStreamAlias("ProtocolNo")
	private String protocolNo;
	@XStreamAlias("BudgetType")
	private String budgetType;
	@XStreamAlias("TrimSign")
	private String trimSign;
	@XStreamAlias("CorpType")
	private String corpType;
	@XStreamAlias("PrintVouSign")
	private String printVouSign;
	@XStreamAlias("Remark")
	private String remark;
	@XStreamAlias("Remark1")
	private String remark1;
	@XStreamAlias("Remark2")
	private String remark2;
	@XStreamAlias("TaxTypeNum")
	private Integer taxTypeNum;
	@XStreamImplicit
	private LinkedList<TaxTypeList1001>  taxTypeList1001=new LinkedList<TaxTypeList1001>() ;
	
	public void setPk(Integer pk,RealHead1001 realHead1001){
		this.id=pk;
		this.taxOrgCode=realHead1001.getTaxOrgCode();
		this.entrustDate=realHead1001.getEntrustDate();
		this.traNo=realHead1001.getTraNo();
		
		for(TaxTypeList1001 obj:taxTypeList1001)
			obj.setPk(pk,realHead1001);
		
	}
}
