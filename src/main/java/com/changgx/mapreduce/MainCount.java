package com.changgx.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by liu on 2016/4/22.
 */
public class MainCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration configuration=new Configuration();


        Job job=Job.getInstance(configuration,"changgx");


        job.setJarByClass(MainCount.class);


        job.setMapperClass(MapCount.class);


        job.setReducerClass(ReduceCount.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);


        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        FileInputFormat.setInputPaths(job,new Path("hdfs://172.16.12.71:9000/liujian/changgx/1.txt"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://172.16.12.71:9000/liujian/out1"));


        boolean flag=job.waitForCompletion(true);
        if(!flag){
            System.out.println("job is failed");
        }
    }
}
