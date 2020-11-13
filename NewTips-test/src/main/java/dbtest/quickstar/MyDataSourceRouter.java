package dbtest.quickstar;

import org.jfaster.mango.partition.DataSourceRouter;

public class MyDataSourceRouter implements DataSourceRouter<String> {

    @Override
    public String getDataSourceName(String shardParam, int type) {
    	int id=Math.abs(shardParam.hashCode());
    	String ret=id % 5 < 3 ? "ds1" : "ds2";
        return ret;
    }

}