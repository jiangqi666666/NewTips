package com.jiangqi.newtips.rules.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;
import com.jiangqi.newtips.context.ResultContext;
import com.jiangqi.newtips.rules.api.RetResult;
import com.jiangqi.newtips.rules.api.RetResultSet;
import com.jiangqi.newtips.rules.api.SchemaVerifyService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchemaVerifyServiceImpl implements SchemaVerifyService {
	private static Logger logger = Logger.getLogger(SchemaVerifyServiceImpl.class);

	//private HashMap<String, Resource> schemaFile;
	private Resource xsdFileName; 

	@Override
	public RetResultSet verify(String tradeId, String msg) {
		// TODO Auto-generated method stub
		logger.info("verify start");
		
		RetResultSet rets = new RetResultSet();
		boolean isException = true;
		try {
			// 创建默认的XML错误处理器
			XMLErrorHandler errorHandler = new XMLErrorHandler();

			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();
			SAXReader xmlReader = new SAXReader();

			Document xmlDocument = (Document) xmlReader.read(new ByteArrayInputStream(msg.getBytes()));
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource",
					this.xsdFileName.getFile());
			

			SAXValidator validator = new SAXValidator(parser.getXMLReader());
			validator.setErrorHandler(errorHandler);
			validator.validate(xmlDocument);

			// 如果错误信息不为空，说明校验失败，打印错误信息
			if (errorHandler.getErrors().hasContent()) {
				XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
				writer.write(errorHandler.getErrors());
			//	writer.close();

				RetResult ret = new RetResult();
				ret.setResult(ResultContext.SYS_ERR);
				ret.setAddWord("报文格式校验失败");
				rets.getResult().add(ret);
				isException = false;
			} else {
				RetResult ret = new RetResult();
				ret.setResult(ResultContext.SUCCESS);
				ret.setAddWord("报文格式校验通过");
				rets.getResult().add(ret);
				isException = false;
			}
		} catch (SAXException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (DocumentException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (ParserConfigurationException e) {
			logger.error(e,e.fillInStackTrace());
		} catch (IOException e) {
			logger.error(e,e.fillInStackTrace());
		}

		if (isException == true) {
			RetResult ret = new RetResult();
			ret.setResult(ResultContext.SYS_ERR);
			ret.setAddWord("系统错误！");
			rets.getResult().add(ret);
		}

		return rets;
	}
}
