package com.changgx.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 读取HDFS上某一目录下的所有文件
 */
public class Test {

    public static void main(String[] args) {
        new Test().getHDFSFile();
    }

    /***
     * 加载配置文件
     **/
    static Configuration conf = new Configuration();

    public void getHDFSFile() {

        //流读入和写入
        InputStream in = null;
        //获取HDFS的conf
        //读取HDFS上的文件系统
        FileSystem hdfs = null;
        try {
            hdfs = FileSystem.get(conf);
            //使用缓冲流，进行按行读取的功能
            BufferedReader buff = null;
            //获取日志文件的根目录
            Path listf = new Path("///liujian/changgx");
            //获取根目录下的所有2级子文件目录
            FileStatus stats[] = hdfs.listStatus(listf);
            //自定义j，方便查看插入信息
            int j = 0;
            for (int i = 0; i < stats.length; i++) {
                //获取子目录下的文件路径
                FileStatus temp[] = hdfs.listStatus(new Path(stats[i].getPath().toString()));
                for (int k = 0; k < temp.length; k++) {
                    System.out.println("文件路径名:" + temp[k].getPath().toString());
                    //获取Path
                    Path p = new Path(temp[k].getPath().toString());
                    //打开文件流
                    in = hdfs.open(p);
                    //BufferedReader包装一个流
                    buff = new BufferedReader(new InputStreamReader(in));
                    String str = null;
                    while ((str = buff.readLine()) != null) {
                        System.out.println(str);
                    }
                    buff.close();
                    in.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                hdfs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
