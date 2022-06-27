package com.ruoyi.sidebarTree.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static List<String[]> read(String path){
        CsvReader csvReader=null;
        List<String> list=new ArrayList<>();
        try {
            csvReader = new CsvReader(path, ',', Charset.forName("GBK"));

        /*    //读表头
            csvReader.readHeaders();*/
            while (csvReader.readRecord()) {
                //读取一整行
                String rawRecord = new String(csvReader.getRawRecord());
                list.add(rawRecord);
                //读取这行的某列，序号从0开始，csvReader.get(0)读取的是这行的第一列
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            csvReader.close();
        }
        List<String[]> result =new ArrayList<>();
        for(String sb:list){
            String[] arr =sb.split(",");
            result.add(arr);
        }
        return result;
    }

    public static  List<String> getHeight(String path) {
        List<String[]> read = read(path);
        //提取height_m
        String[] strings = read.get(0);
        int flag=0;
        for(int i=0;i<strings.length;i++)
            if(strings[i].equals("height_m"))
                flag=i;
        List<String> ls =new ArrayList<>();
        for(int i=1;i<read.size();i++)
        {
            if(read.get(i).length>=flag)
            ls.add(read.get(i)[flag]);
        }
        return ls;
    }

    /**
     *
     * @param arr 各文件路径
     * @param date 各日期
     * @param savePath 保存路径
     */
    public static void mknewCsv(String[] arr,String[] date,String savePath) {
        List<List<String>> a=new ArrayList<>();
        for(int i=0;i<date.length;i++)
        {
            List<String> height = getHeight(arr[i]);
            a.add(height);
        }

        File newCsv=new File(savePath);
        CsvWriter csvWriter =null;

        try {
            if(!newCsv.exists())
                newCsv.createNewFile();
            csvWriter=new CsvWriter(newCsv.getAbsolutePath(),',', Charset.forName("GBK"));
            csvWriter.write("幼苗序号");
            for(String datestr:date){
                csvWriter.write(datestr);
            }
            csvWriter.endRecord();
            for(int i=0;i<a.get(0).size();i++)//行
            {
                csvWriter.write("第"+(i+1)+"株");
                for(int j=0;j<a.size();j++){
                    csvWriter.write(a.get(j).get(i));
                }
                csvWriter.endRecord();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            csvWriter.close();
        }
    }
}
