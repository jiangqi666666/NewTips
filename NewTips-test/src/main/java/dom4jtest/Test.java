package dom4jtest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jiangqi.newtips.serialize.online.onlieinfo.OnlieInfoResource;

public class Test {
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
	context = new ClassPathXmlApplicationContext("./spring/dom4j.xml");
		context.start();
		
		TTT ttt = (TTT) context.getBean("ttt");
		
		
		
		ttt.verify();
		
	
	}
}
