package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class TurnAccount3001 {
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
}
