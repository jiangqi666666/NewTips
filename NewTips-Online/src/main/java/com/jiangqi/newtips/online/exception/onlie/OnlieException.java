package com.jiangqi.newtips.online.exception.onlie;

import lombok.Getter;

/**
 * 联机交易异常类
 * @author jiangqi
 *
 */
@Getter  
public class OnlieException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 处理码
	 */
	private String result;
	
	/**
	 * 附言
	 */
	private String addWord;
	
	/**
	 * 补充信息
	 */
	private String information;
	
	/**
	 * 联机交易异常类
	 * @param result 处理码
	 * @param addWord 附言
	 * @param info 补充信息
	 */
	public OnlieException(String result,String addWord,String info){
		super();
		
		this.result=result;
		this.addWord=addWord;
		this.information=info;
	}
}
