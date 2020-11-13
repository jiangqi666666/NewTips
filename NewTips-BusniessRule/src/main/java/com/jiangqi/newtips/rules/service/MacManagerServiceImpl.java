package com.jiangqi.newtips.rules.service;

import org.apache.log4j.Logger;

import com.jiangqi.newtips.rules.api.MacManagerService;

public class MacManagerServiceImpl implements MacManagerService {
	private static Logger logger = Logger.getLogger(MacManagerServiceImpl.class);

	@Override
	public boolean checkMac(String node, String msg, String mac) {
		// TODO Auto-generated method stub
		logger.info("checkMac start");
		return true;
	}

	@Override
	public String createMac(String node, String msg) {
		// TODO Auto-generated method stub
		logger.info("createMac start");
		return "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	}
}
