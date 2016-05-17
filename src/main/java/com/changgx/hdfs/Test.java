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
 * ��ȡHDFS��ĳһĿ¼�µ������ļ�
 */
public class Test {

    public static void main(String[] args) {
        new Test().getHDFSFile();
    }

    /***
     * ���������ļ�
     **/
    static Configuration conf = new Configuration();

    public void getHDFSFile() {

        //�������д��
        InputStream in = null;
        //��ȡHDFS��conf
        //��ȡHDFS�ϵ��ļ�ϵͳ
        FileSystem hdfs = null;
        try {
            hdfs = FileSystem.get(conf);
            //ʹ�û����������а��ж�ȡ�Ĺ���
            BufferedReader buff = null;
            //��ȡ��־�ļ��ĸ�Ŀ¼
            Path listf = new Path("///liujian/changgx");
            //��ȡ��Ŀ¼�µ�����2�����ļ�Ŀ¼
            FileStatus stats[] = hdfs.listStatus(listf);
            //�Զ���j������鿴������Ϣ
            int j = 0;
            for (int i = 0; i < stats.length; i++) {
                //��ȡ��Ŀ¼�µ��ļ�·��
                FileStatus temp[] = hdfs.listStatus(new Path(stats[i].getPath().toString()));
                for (int k = 0; k < temp.length; k++) {
                    System.out.println("�ļ�·����:" + temp[k].getPath().toString());
                    //��ȡPath
                    Path p = new Path(temp[k].getPath().toString());
                    //���ļ���
                    in = hdfs.open(p);
                    //BufferedReader��װһ����
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
