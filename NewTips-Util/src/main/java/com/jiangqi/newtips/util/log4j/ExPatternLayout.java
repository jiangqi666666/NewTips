package com.jiangqi.newtips.util.log4j;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

public class ExPatternLayout extends PatternLayout {

	public ExPatternLayout(String pattern) {
		super(pattern);
	}

	public ExPatternLayout() {
		super();
	}

	@Override
	protected PatternParser createPatternParser(String pattern) {
		// TODO Auto-generated method stub
		return new ExPatternParser(pattern);
	}
}
