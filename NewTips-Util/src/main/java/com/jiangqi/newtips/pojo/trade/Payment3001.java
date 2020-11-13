package com.jiangqi.newtips.pojo.trade;

import java.util.LinkedList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class Payment3001 {
	@XStreamAlias("PayAcct")
	private String payAcct;
	@XStreamAlias("HandOrgName")
	private String handOrgName;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("TraAmt")
	private Double traAmt;
	
	@XStreamAlias("TaxVouNo")
	private String taxVouNo;
	@XStreamAlias("TaxPayName")
	private String taxPayName;
	@XStreamAlias("ProtocolNo")
	private String protocolNo;
	@XStreamAlias("Remark")
	private String remark;
	@XStreamAlias("Remark1")
	private String remark1;
	@XStreamAlias("Remark2")
	private String remark2;
	@XStreamAlias("TaxTypeNum")
	private Integer taxTypeNum;
	@XStreamImplicit
	private LinkedList<TaxTypeList3001>  taxTypeList3001=new LinkedList<TaxTypeList3001>() ;
}
