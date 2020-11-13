package com.jiangqi.newtips.pojo.trade;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class TaxTypeList3001 {
	@XStreamAlias("ProjectId")
	private Integer projectId;
	@XStreamAlias("TaxTypeName")
	private String taxTypeName;
	@XStreamAlias("TaxStartDate")
	private String taxStartDate;
	@XStreamAlias("TaxEndDate")
	private String taxEndDate;
	
	@XStreamConverter( value=com.jiangqi.newtips.serialize.TipsDoubleConverter.class )
	@XStreamAlias("TaxTypeAmt")
	private Double taxTypeAmt;
}
