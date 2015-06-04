package com.airsupply.hbase.test;

import java.io.IOException;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.conf.Configuration;

public class HelloHBase {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.202.129");
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor tableDescriptor = admin.getTableDescriptor(Bytes.toBytes("kylin_metadata"));
        byte[] name = tableDescriptor.getName();
        System.out.println(new String(name));
        HColumnDescriptor[] columnFamilies = tableDescriptor.getColumnFamilies();
        for (HColumnDescriptor d : columnFamilies) {
            System.out.println(d.getNameAsString());
        }
        
        Scan scan = new Scan();
//      scan.setStartRow(Bytes.toBytes(start_rowkey));
//      scan.setStopRow(Bytes.toBytes(stop_rowkey));
      ResultScanner rs = null;
      HTable table = new HTable(conf, Bytes.toBytes("kylin_metadata"));
      try {
          rs = table.getScanner(scan);
          for (Result r : rs) {
              for (Cell cell : r.listCells()) {
                 System.out.println(cell.getValue());
                  System.out.println("-------------------------------------------");
              }
          }
      } finally {
          rs.close();
      }
    }
}