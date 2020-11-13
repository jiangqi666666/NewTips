package com.jiangqi.newtips.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.apache.log4j.Logger;
import com.jiangqi.newtips.pojo.trade.Msg3001;
import com.jiangqi.newtips.pojo.trade.Payment3001;
import com.jiangqi.newtips.pojo.trade.RealHead3001;
import com.jiangqi.newtips.pojo.trade.TaxTypeList1001;
import com.jiangqi.newtips.pojo.trade.TaxTypeList3001;
import com.jiangqi.newtips.pojo.trade.Trade1001;
import com.jiangqi.newtips.pojo.trade.Trade3001;
import com.jiangqi.newtips.pojo.trade.TurnAccount3001;

/**
 * 创建转换报文api
 * 
 * @author jiangqi
 *
 */
public class CreateMsg {
	private static Logger logger = Logger.getLogger(CreateMsg.class);

	public final String listName = "java.util.LinkedList";

	/**
	 * 用反射方式，做bean之间转换<p>
	 * 只做简单转换，碰到listName = "java.util.LinkedList"的属性自动跳过
	 * @param bean	原bean
	 * @param convert 需转换的bean对应类名称
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Object convert(Object bean, String convert) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IntrospectionException, IllegalArgumentException, InvocationTargetException {

		logger.info("convert start");

		Object obj = null;
		Object ret = Class.forName(convert).newInstance();

		PropertyDescriptor pd;
		Field[] field = ret.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			if (field[i].getModifiers() == Modifier.PRIVATE) {

				if (field[i].getType() != Class.forName(this.listName)) {
					obj = getSimpleField(field[i], bean);
					pd = new PropertyDescriptor(field[i].getName(), ret.getClass());
					pd.getWriteMethod().invoke(ret, obj);
				}
			}
		}
		return ret;
	}

	/**
	 * 获得简单属性内容
	 * @param field	
	 * @param bean 原bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Object getSimpleField(Field field, Object bean)
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		PropertyDescriptor pd = new PropertyDescriptor(field.getName(), bean.getClass());

		return pd.getReadMethod().invoke(bean);
	}

	/**
	 * 根据1001创建3001
	 * 
	 * @param trade1001
	 * @return
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IntrospectionException
	 */
	public Trade3001 create3001(Trade1001 trade1001) throws IllegalAccessException, ClassNotFoundException,
			InvocationTargetException, InstantiationException, IntrospectionException {

		Trade3001 bean = new Trade3001();

		Msg3001 msg = new Msg3001();

		bean.setMsg(msg);
		RealHead3001 real = (RealHead3001) convert(trade1001.getMsg().getRealHead1001(),
				"com.jiangqi.newtips.pojo.trade.RealHead3001");

		TurnAccount3001 turn = (TurnAccount3001) convert(trade1001.getMsg().getTurnAccount1001(),
				"com.jiangqi.newtips.pojo.trade.TurnAccount3001");

		Payment3001 pay = (Payment3001) convert(trade1001.getMsg().getPayment1001(),
				"com.jiangqi.newtips.pojo.trade.Payment3001");

		bean.getMsg().setPayment3001(pay);
		bean.getMsg().setRealHead3001(real);
		bean.getMsg().setTurnAccount3001(turn);

		for (TaxTypeList1001 tmp : trade1001.getMsg().getPayment1001().getTaxTypeList1001()) {
			TaxTypeList3001 tax = (TaxTypeList3001) convert(tmp, "com.jiangqi.newtips.pojo.trade.TaxTypeList3001");
			bean.getMsg().getPayment3001().getTaxTypeList3001().add(tax);
		}

		return bean;
	}
}
