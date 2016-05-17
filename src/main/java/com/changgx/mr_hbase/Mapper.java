package com.changgx.mr_hbase;

import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * Created by liu on 2016/4/25.
 */
public class Mapper extends org.apache.hadoop.mapreduce.Mapper<Text, Text, Text, Text> {
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String msg = value.toString();
        String[] str = msg.split(":");
        context.write(new Text(str[0]),new Text(str[1]));
    }
}
