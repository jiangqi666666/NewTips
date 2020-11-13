package dbtest.quickstar;

import java.util.HashMap;

import javax.sql.DataSource;

import org.jfaster.mango.datasource.DataSourceFactory;
import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.datasource.MultipleDataSourceFactory;
import org.jfaster.mango.datasource.SimpleDataSourceFactory;
import org.jfaster.mango.operator.Mango;
import org.jfaster.mango.transaction.Transaction;
import org.jfaster.mango.transaction.TransactionFactory;

import dbtest.spring.UserDao;

public class HelloWorld {

	public static void main(String[] args) {
		String driverClassName = "com.mysql.jdbc.Driver";
		String url1 = "jdbc:mysql://192.168.3.101:3306/mango_1";
		String url2 = "jdbc:mysql://192.168.3.101:3306/mango_2";
		String username = "root"; // 这里请使用您自己的用户名
		String password = "111111"; // 这里请使用您自己的密码

		DataSource ds1 = new DriverManagerDataSource(driverClassName, url1, username, password);
		DataSource ds2 = new DriverManagerDataSource(driverClassName, url2, username, password);

		HashMap<String, DataSourceFactory> factories = new HashMap<String, DataSourceFactory>();
		factories.put("ds1", new SimpleDataSourceFactory(ds1));
		factories.put("ds2", new SimpleDataSourceFactory(ds2));
		DataSourceFactory dsf = new MultipleDataSourceFactory(factories);
		
		//dsf.setFactories(factories);

		Mango mango = Mango.newInstance(dsf);

		// Mango mango = Mango.newInstance(ds); // 使用数据源初始化mango

		UserDao user = mango.create(UserDao.class);
		

		Transaction tx1 = TransactionFactory.newTransaction(mango, "ds1");
		Transaction tx2 = TransactionFactory.newTransaction(mango, "ds2");

		try {

			String userid="8225678.20160628.12345670";
			String name="AAAA";
			User bean=new User();
			bean.setMyid(userid);
			bean.setName(name);
			user.addUser(bean);
			
			

			tx1.commit();
			tx2.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx1.rollback();
			tx2.rollback();
			
			System.out.println("SSSSSSSSSSSS");
		}

		User aa = user.getUser("123466.123");

		System.out.println(aa.getName());
	}

}