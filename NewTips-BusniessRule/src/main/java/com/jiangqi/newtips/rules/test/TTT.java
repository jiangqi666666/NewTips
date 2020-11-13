package com.jiangqi.newtips.rules.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TTT {
	private int count=0;
	
	public int test(){
		this.count++;
		return this.count;
	}
}
