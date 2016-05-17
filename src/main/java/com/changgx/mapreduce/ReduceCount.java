package com.changgx.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by liu on 2016/4/22.
 */
public class ReduceCount extends Reducer<Text,IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        Integer count=0;
        for(IntWritable value:values){
            count+=value.get();
        }
        context.write(key,new IntWritable(count));
    }
}
