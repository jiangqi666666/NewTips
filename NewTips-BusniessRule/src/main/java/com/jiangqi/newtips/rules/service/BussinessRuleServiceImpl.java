package com.jiangqi.newtips.rules.service;

import java.util.HashMap;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import com.jiangqi.newtips.context.ResultContext;
import com.jiangqi.newtips.rules.api.BussinessRuleService;
import com.jiangqi.newtips.rules.api.RetResult;
import com.jiangqi.newtips.rules.api.RetResultSet;

public class BussinessRuleServiceImpl implements BussinessRuleService {

	private static Logger logger = Logger.getLogger(BussinessRuleServiceImpl.class);
	
	@Override
	public RetResultSet checkRule(LinkedList<String> rules, HashMap<String, Object> paras, boolean isAll) {
		// TODO Auto-generated method stub
		logger.info("checkRule start");
		
		RetResultSet rets=new RetResultSet();
		
		RetResult ret=new RetResult();
		ret.setResult(ResultContext.SUCCESS);
		ret.setAddWord("AAAAAAAAAAAAAAAAAAA");
		rets.getResult().add(ret);
		
		ret=new RetResult();
		ret.setResult(ResultContext.SUCCESS);
		ret.setAddWord("BBBBBBBBB");
		
		rets.getResult().add(ret);
		
		return rets;
	}

}
