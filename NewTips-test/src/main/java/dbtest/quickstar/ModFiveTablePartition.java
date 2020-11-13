package dbtest.quickstar;

import org.jfaster.mango.partition.TablePartition;

public class ModFiveTablePartition implements TablePartition<String> {

    @Override
    public String getPartitionedTable(String table, String shardParam, int type) {
    	int aa=Math.abs(shardParam.hashCode());
        return table + "_" + (aa % 5);
    }

}