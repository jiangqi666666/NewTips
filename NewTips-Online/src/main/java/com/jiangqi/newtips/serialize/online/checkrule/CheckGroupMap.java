package com.jiangqi.newtips.serialize.online.checkrule;

import java.util.LinkedList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("group")
public class CheckGroupMap {
	@XStreamAsAttribute
	private String groupId;
	private LinkedList<CheckRule> rules=new LinkedList<CheckRule>();
}
