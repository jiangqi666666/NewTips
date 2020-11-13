package com.jiangqi.newtips.serialize.online.checkrule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Getter;
import lombok.Setter;
@XStreamAlias("param")
@Getter  
@Setter
public class CheckParametes {
	@XStreamAsAttribute
	private String paramId;
	@XStreamAsAttribute
	private String bean;
}
