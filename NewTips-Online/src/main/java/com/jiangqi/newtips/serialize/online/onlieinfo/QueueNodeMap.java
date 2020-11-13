package com.jiangqi.newtips.serialize.online.onlieinfo;

import java.util.HashMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
@XStreamAlias("QueueNodeMap")
public class QueueNodeMap {
	@XStreamImplicit
	private HashMap<String,String> queueNodeMap= new HashMap<String,String>();
}
