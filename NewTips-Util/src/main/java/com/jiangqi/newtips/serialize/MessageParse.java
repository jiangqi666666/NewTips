package com.jiangqi.newtips.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import org.springframework.oxm.xstream.XStreamMarshaller;

import lombok.Getter;
import lombok.Setter;

/**
 * xml报文解析转换类
 * @author jiangqi
 *
 */
@Getter
@Setter
public class MessageParse {
	private static Logger logger = Logger.getLogger(MessageParse.class);
	
	/**
	 * 存放解析报文的所需的XStreamMarshaller
	 */
	private HashMap<String,XStreamMarshaller> hander;

	/**
	 * 将xml转换成bean
	 * @param msg	xml报文
	 * @param marshaller	所需XStreamMarshaller名称
	 * @return
	 * @throws IOException
	 */
	public Object loadBean(String msg,String marshaller) throws IOException {
		logger.info("loadBean start");
		
		try {
			InputStream in = new ByteArrayInputStream(msg.getBytes());
			return hander.get(marshaller).unmarshal(new StreamSource(in));
		} catch ( IOException e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 将xml文件转换成bean
	 * @param in	xml文件
	 * @param marshaller	所需XStreamMarshaller名称
	 * @return
	 * @throws IOException
	 */
	public Object loadBean(FileInputStream in,String marshaller) throws IOException {
		logger.info("loadBean start");
		return hander.get(marshaller).unmarshal(new StreamSource(in));
	}

	/**
	 * 将bean转换成xml
	 * @param bean	
	 * @param marshaller	所需XStreamMarshaller名称
	 * @return
	 * @throws IOException
	 */
	public String saveBean(Object bean,String marshaller) throws IOException {
		logger.info("saveBean start");
		
		OutputStream out = null;
		try {
			
			out = new ByteArrayOutputStream();
			hander.get(marshaller).marshal(bean, new StreamResult(out));
			return out.toString();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
