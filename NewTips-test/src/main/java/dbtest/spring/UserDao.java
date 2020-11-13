package dbtest.spring;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.DataSourceShardBy;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ShardBy;
import org.jfaster.mango.annotation.TableShardBy;

import dbtest.quickstar.ModFiveTablePartition;
import dbtest.quickstar.MyDataSourceRouter;
import dbtest.quickstar.User;

@DB(
		table = "user", 
		dataSourceRouter = MyDataSourceRouter.class,
		tablePartition = ModFiveTablePartition.class
)
public interface UserDao {

    @SQL("insert into #table(myid, name) values(:1.myid, :1.name)")
    public void addUser(@ShardBy("myid") User user );

   @SQL("select myid, name from #table where myid = :1")
   public User getUser(@ShardBy String uid);

}
