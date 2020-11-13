package com.jiangqi.newtips.serialize.online.onlieinfo;

import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class OnlieInfoResource {
	private Resource test;
	private Resource checkRule;
	private Resource tradeFlow;
	private Resource queueNode;
}
