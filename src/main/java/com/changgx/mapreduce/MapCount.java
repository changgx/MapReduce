package com.changgx.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by liu on 2016/4/22.
 */
public class MapCount extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        System.out.println("========================setup=========================");
        super.setup(context);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("========================map=========================");
        //��ȡ��һ������
        String line=value.toString();

        //�и����ݣ�ͨ���ո�
        String[] words=line.split(" ");

        //���
        for(String word:words){
            context.write(new Text(word),new IntWritable(1));
        }

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        System.out.println("========================cleanup=========================");
        super.cleanup(context);
    }

    @Override
    public void run(Context context) throws IOException, InterruptedException {
        System.out.println("========================run=========================");
        super.run(context);
    }
}
