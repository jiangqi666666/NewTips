package com.jiangqi.newtips.pojo.trade;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class VoucherStat1001 {
	private Integer id;
	private String taxOrgCode;
	private String entrustDate;
	private Integer traNo;
	
	private String payeeBankNo;
	private String payeeOrgCode;
	private String payBkCode;
	private String payOpBkCode;
	private String payAcct;
	private String handOrgName;
	private Double traAmt;
	
	private String acceptDate;
	private String endDate;
	
	private Integer state;
	private Integer version;
	
}
