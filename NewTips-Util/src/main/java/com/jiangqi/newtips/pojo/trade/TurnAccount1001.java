package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class TurnAccount1001 {
	@XStreamOmitField 
	private Integer id;
	@XStreamOmitField 
	private String taxOrgCode;
	@XStreamOmitField
	private String entrustDate;
	@XStreamOmitField
	private Integer traNo;
	
	@XStreamAlias("HandleType")
	private String handleType;
	@XStreamAlias("PayeeBankNo")
	private String payeeBankNo;
	@XStreamAlias("PayeeOrgCode")
	private String payeeOrgCode;
	@XStreamAlias("PayeeAcct")
	private String payeeAcct;
	@XStreamAlias("PayeeName")
	private String payeeName;
	@XStreamAlias("PayBkCode")
	private String payBkCode;
	@XStreamAlias("PayOpBkCode")
	private String payOpBkCode;
	
	public void setPk(Integer pk,RealHead1001 realHead1001){
		this.id=pk;
		this.taxOrgCode=realHead1001.getTaxOrgCode();
		this.entrustDate=realHead1001.getEntrustDate();
		this.traNo=realHead1001.getTraNo();
	}
}
