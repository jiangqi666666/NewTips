package tipstest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.jiangqi.newtips.online.context.SendMsgContext;
import com.jiangqi.newtips.pojo.trade.Trade1001;
import com.jiangqi.newtips.serialize.MessageParse;
import com.jiangqi.newtips.serialize.online.onlieinfo.OnlieInfoResource;

public class TestMain {
	private static Logger logger = Logger.getLogger(TestMain.class);
	
	private static ClassPathXmlApplicationContext context;
	private static OnlieInfoResource onlie;
	private static MessageWork messageWork;
	private static int i_count=3000;
	private static int i_start=100;

	public static Object xml2bean(MessageParse parse) throws IOException {

		File file = null;
		FileInputStream is = null;
		try {
			file = onlie.getTest().getFile();
			is = new FileInputStream(file);
			Trade1001 tmp = (Trade1001) parse.loadBean(is,"marshaller1001");
			return tmp;
		} finally {
			if (is != null)
				is.close();
		}
	}
	
	public static String bean2xml(MessageParse parse,Object obj) throws IOException {

		return parse.saveBean(obj,"marshaller1001");

	}

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("./spring/app.xml");
		context.start();

		onlie = (OnlieInfoResource) context.getBean("onlieInfoResource");
		messageWork = (MessageWork) context.getBean("messageWork");
		MessageParse parse = (MessageParse) context.getBean("msgParse");
		//XStreamMarshaller marshaller = (XStreamMarshaller) context.getBean("marshaller1001");
		//parse.setMarshaller(marshaller);
		//parse.setUnmarshaller(marshaller);

		Trade1001 trade1001;
		try {
			 trade1001 = (Trade1001)xml2bean(parse);
			 trade1001.setPk(0);
			 String msg;
			 for(int i=i_start;i<i_count+i_start;i++){
					trade1001.getMsg().getRealHead1001().setTraNo(i);
					
					msg=bean2xml(parse,trade1001);
					
					SendMsgContext send = new SendMsgContext();
					send.setTradeId("1001");
					send.setSendNode("202100000010");
					send.setMsgType(1);
					send.setVersion("AA");
					send.setMsg(msg);
					
					logger.info("send msg");
					messageWork.send("queueTest", send);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
}
