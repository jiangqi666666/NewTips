package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class TaxSubjectList1001 {
	@XStreamOmitField 
	private Integer id;
	@XStreamOmitField 
	private String taxOrgCode;
	@XStreamOmitField
	private String entrustDate;
	@XStreamOmitField
	private Integer traNo;
	
	@XStreamOmitField 
	private Integer projectId;
	
	@XStreamAlias("DetailNo")
	private Integer detailNo;
	@XStreamAlias("TaxSubjectCode")
	private String taxSubjectCode;
	@XStreamAlias("TaxSubjectName")
	private String taxSubjectName;
	@XStreamAlias("TaxNumber")
	private String taxNumber;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("TaxAmt")
	private Double taxAmt;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("TaxRate")
	private Double taxRate;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("ExpTaxAmt")
	private Double expTaxAmt;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("DiscountTaxAmt")
	private Double discountTaxAmt;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("FactTaxAmt")
	private Double factTaxAmt;
	
	public void setPk(Integer pk, Integer projectId,RealHead1001 realHead1001){
		this.id=pk;
		this.taxOrgCode=realHead1001.getTaxOrgCode();
		this.entrustDate=realHead1001.getEntrustDate();
		this.traNo=realHead1001.getTraNo();
		this.projectId=projectId;
	}
}
