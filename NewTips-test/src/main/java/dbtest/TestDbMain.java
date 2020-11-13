package dbtest;

import org.jfaster.mango.operator.Mango;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dbtest.quickstar.User;
import dbtest.service.TestServiceImpl;
import dbtest.spring.UserDao;

public class TestDbMain {

	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("./spring/db.xml");
		context.start();

		TestServiceImpl tt=(TestServiceImpl)context.getBean("testServiceImpl");
		tt.addUser();
        
        context.close();
	}
}
