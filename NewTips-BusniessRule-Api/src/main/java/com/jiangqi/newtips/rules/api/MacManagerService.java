package com.jiangqi.newtips.rules.api;

/**
 * 加核押服务
 * @author JQ
 *
 */
public interface MacManagerService {
	/**
	 * 核对密押
	 * @param node 密押所属机构
	 * @param msg 加押内容
	 * @param mac 密押
	 * @return true 核对相符 false 核对不符
	 */
	public boolean checkMac(String node, String msg,String mac);
	
	/**
	 * 生成密押
	 * @param node 密押所属机构
	 * @param msg 加押内容
	 * @return 创建的密押，创建失败返回null
	 */
	public String createMac(String node, String msg);
}
