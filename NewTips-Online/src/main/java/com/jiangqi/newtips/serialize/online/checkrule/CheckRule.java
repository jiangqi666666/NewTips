package com.jiangqi.newtips.serialize.online.checkrule;

import java.util.LinkedList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Getter;
import lombok.Setter;
@XStreamAlias("rule")
@Getter  
@Setter
public class CheckRule {
	@XStreamAsAttribute
	private String ruleId;
	private LinkedList<CheckParametes> params=new LinkedList<CheckParametes>();
}
