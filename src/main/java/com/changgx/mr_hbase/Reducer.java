package com.changgx.mr_hbase;


import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * Created by liu on 2016/4/25.
 */
public class Reducer extends org.apache.hadoop.mapreduce.Reducer<Text,Text,ImmutableBytesWritable,Put>{
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        super.reduce(key, values, context);
    }
}
