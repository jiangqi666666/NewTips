package com.jiangqi.newtips.online.services;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.log4j.Logger;

import com.jiangqi.newtips.enuml.ReturnCodeEnum;
import com.jiangqi.newtips.online.exception.onlie.OnlieException;
import com.jiangqi.newtips.pojo.checkrule.RuleGroup;
import com.jiangqi.newtips.serialize.online.checkrule.CheckGroupMap;
import com.jiangqi.newtips.serialize.online.checkrule.CheckParametes;
import com.jiangqi.newtips.serialize.online.checkrule.CheckRule;

/**
 * 根据用户配置生成业务规则校验组信息服务<p>
 * 远程规则以组为单位创建，可创建多个组<p>
 * 本地规则只能属于同一个组
 * @author jiangqi
 */
public class CheckRuleService {
	private static Logger logger = Logger.getLogger(CheckRuleService.class);

	/**
	 * 将参数放入参数池，如池中已经包含此参数则返回<p>
	 * 系统根据用户配置，通过反射方式获得参数来源实体数据，并放入参数池中
	 * @param params	参数池map
	 * @param name		需要添加到参数池中的参数名称
	 * @param obj		用户配置的参数对应文件中的bean内容，无{}符号则视为直接使用配置文件中文本内容，如有{bean.xx}则视为使用对应bean的xx属性
	 * @param beans		根据报文解析，或用户设置的具体参数来源实体
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 * @throws IntrospectionException
	 */
	private void putParam(HashMap<String, Object> params, String name, String obj, HashMap<String, Object> beans)
			throws IllegalAccessException, InvocationTargetException, NoSuchFieldException, IntrospectionException {
		
		logger.info("putParam start");

		if (params.get(name) != null)
			return;

		if (obj.startsWith("${") == false) {
			params.put(name, obj.trim());
		} else {
			String beanStr = obj.replace("${", "");
			beanStr = beanStr.replace("}", "");
			String[] str = beanStr.split("\\.");
			Object tmp = beans.get(str[0]);

			Method beanMethod;
			Field field;
			PropertyDescriptor pd;
			for (int i = 1; i < str.length; i++) {
				field = tmp.getClass().getDeclaredField(str[i].trim());
				pd = new PropertyDescriptor(field.getName(), tmp.getClass());
				beanMethod = pd.getReadMethod();
				tmp = beanMethod.invoke(tmp);
			}
			params.put(name, tmp);
		}
	}

	/**
	 * 创建规则组用参数池
	 * @param rule
	 * @param params
	 * @param beans
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 * @throws IntrospectionException
	 */
	private void createParamContext(CheckRule rule, HashMap<String, Object> params, HashMap<String, Object> beans)
			throws IllegalAccessException, InvocationTargetException, NoSuchFieldException, IntrospectionException {
		
		logger.info("createParamContext start");

		for (CheckParametes param : rule.getParams()) {
			putParam(params, param.getParamId(), param.getBean(), beans);
		}
	}

	/**
	 * 创建本地业务规则组pojo
	 * @param rules 配置的规则（本地业务规则只有一个业务组）
	 * @param beans 规则组用的参数信息（从报文解析得到数据存在beans.bean里，用户自定义的bena对应名称根据用户定义）
	 * @return
	 * @throws OnlieException
	 */
	public RuleGroup createLocalRule(LinkedList<CheckRule> rules, HashMap<String, Object> beans) throws OnlieException {

		logger.info("createLocalRule");
		
		RuleGroup ruleGroup = new RuleGroup();
		try {
			ruleGroup.setGroupId("localRule");
			for (CheckRule rule : rules) {
				ruleGroup.getRules().add(rule.getRuleId());
				createParamContext(rule, ruleGroup.getParams(), beans);
			}
		} catch (IllegalAccessException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ReturnCodeEnum.SYSTEM_ERR, "", "");
		} catch (InvocationTargetException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ReturnCodeEnum.SYSTEM_ERR, "", "");
		} catch (NoSuchFieldException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ReturnCodeEnum.SYSTEM_ERR, "", "");
		} catch (IntrospectionException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ReturnCodeEnum.SYSTEM_ERR, "", "");
		}

		return ruleGroup;
	}

	/**
	 * 创建远程业务规则组POJO
	 * @param group 配置的规则组名（远程业务规则可包括多个规则组，dubbo调用时以规则组为单位）
	 * @param beans 规则组用的参数信息（从报文解析得到数据存在beans.bean里，用户自定义的bena对应名称根据用户定义）
	 * @return 
	 * @throws OnlieException
	 */
	public RuleGroup createRemoteGroupRule(CheckGroupMap group, HashMap<String, Object> beans)
			throws OnlieException {
		
		logger.info("createRemoteGroupRule");

		RuleGroup ruleGroup = new RuleGroup();
		try {
			
			ruleGroup.setGroupId(group.getGroupId());

			for (CheckRule rule : group.getRules()) {
				ruleGroup.getRules().add(rule.getRuleId());
				createParamContext(rule, ruleGroup.getParams(), beans);
			}
		} catch (IntrospectionException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ReturnCodeEnum.SYSTEM_ERR, "", "");
		} catch (IllegalAccessException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ReturnCodeEnum.SYSTEM_ERR, "", "");
		} catch (InvocationTargetException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ReturnCodeEnum.SYSTEM_ERR, "", "");
		} catch (NoSuchFieldException e) {
			logger.error(e,e.fillInStackTrace());
			throw new OnlieException(ReturnCodeEnum.SYSTEM_ERR, "", "");
		}

		return ruleGroup;
	}
}
