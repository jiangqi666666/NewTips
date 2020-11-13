package dbtest.service;

import org.jfaster.mango.transaction.Transaction;
import org.jfaster.mango.transaction.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dbtest.quickstar.User;
import dbtest.spring.UserDao;
import lombok.Getter;
import lombok.Setter;

@Getter  
@Setter
public class TestServiceImpl {
	
	@Autowired
	private UserDao user;
	
	public void addUser( ){
		
		Transaction tx = TransactionFactory.newTransaction("ds1");
		try {
			String userid="123453.123";
			String name="AAAA";
			
			User bean=new User();
			bean.setMyid(userid);
			bean.setName(name);
			user.addUser(bean);
			
			tx.commit();
		}catch (Throwable e) {
			e.printStackTrace();
            tx.rollback();
        }
		
		System.out.println("AAAAA");
		
	}
}
