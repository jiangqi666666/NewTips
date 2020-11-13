package com.jiangqi.newtips.online.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.jiangqi.newtips.online.context.OnlieInfoContext;
import com.jiangqi.newtips.serialize.MessageParse;
import com.jiangqi.newtips.serialize.online.checkrule.AllCheckRuleMap;
import com.jiangqi.newtips.serialize.online.onlieinfo.OnlieInfoResource;
import com.jiangqi.newtips.serialize.online.onlieinfo.QueueNodeMap;
import com.jiangqi.newtips.serialize.online.traderoute.TradeFlowMap;

/**
 * 主控线程
 * @author jiangqi
 *
 */
public class MainOnlie {
	//private ClassPathXmlApplicationContext context;
	
	private void initAllCheckRuleMap(ClassPathXmlApplicationContext context, MessageParse parse,
			OnlieInfoResource onlie) throws IOException {

		File file = onlie.getCheckRule().getFile();
		FileInputStream is = new FileInputStream(file);
		OnlieInfoContext.allCheckRuleMap = (AllCheckRuleMap) parse.loadBean(is,"checkRuleMarshaller");

		is.close();
	}

	private void initTradeFlowMap(ClassPathXmlApplicationContext context, MessageParse parse,
			OnlieInfoResource onlie) throws IOException {

		File file = onlie.getTradeFlow().getFile();
		FileInputStream is = new FileInputStream(file);
		OnlieInfoContext.tradeFlowMap = (TradeFlowMap) parse.loadBean(is,"tradeFlowMarshaller");
		
		is.close();
	}

	private void initQueueNodeMap(ClassPathXmlApplicationContext context, MessageParse parse,
			OnlieInfoResource onlie) throws IOException {

		File file = onlie.getQueueNode().getFile();
		FileInputStream is = new FileInputStream(file);
		OnlieInfoContext.queueNodeMap = (QueueNodeMap) parse.loadBean(is,"queueNodeMarshaller");
		
		is.close();
	}

	/**
	 * 初始化联机交易
	 * @param context
	 * @throws IOException
	 */
	private void init(ClassPathXmlApplicationContext context) throws IOException {
		
		OnlieInfoResource onlie = (OnlieInfoResource) context.getBean("onlieInfoResource");
		MessageParse parse = (MessageParse) context.getBean("msgParse");

		initAllCheckRuleMap(context, parse, onlie);
		initTradeFlowMap(context, parse, onlie);
		initQueueNodeMap(context, parse, onlie);
		
	}

	/**
	 * 启动后台联机交易
	 */
	public void go(){
		
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("./spring/app.xml");
			init(context);
			context.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//InternalLoggerFactory.setDefaultFactory(new Log4JLoggerFactory());
		
		MainOnlie main=new MainOnlie();
		main.go();
	}
}
