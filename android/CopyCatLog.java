package com.amap.location.demo;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCatLog 
{
    public static final String NAME = "copycat.txt";
    private File file;
    Context context =MyApplication.getContext();
    public CopyCatLog() 
    {
        //生成的文件路径
        ///data/user/0/com.econet.solwallet/files/log/copycat.txt
        File temp = new File(context.getFilesDir().getAbsolutePath(), "log");
        if (!temp.exists())
        {
            temp.mkdirs();
        }
        this.file = new File(temp, NAME);
    }
    public CopyCatLog(String system){
        if("window".equals(system)){
            File temp = new File("C:/workspace/");
            if (!temp.exists()){
                temp.mkdirs();
            }
            this.file = new File("C:/workspace/copycat.txt");
        }else if("linux".equals(system)){
            File temp = new File("/home/workspace/");
            if (!temp.exists())
            {
                temp.mkdirs();
            }
           this.file = new File("/home/workspace/copycat.txt");
        }
    }
    
    public  boolean addLog(String logString)
    {
        try 
        {
            if (!TextUtils.isEmpty(logString)) 
            {
                FileWriter fileWriter = new FileWriter(this.file,true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.append(logString);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
                fileWriter.flush();
                fileWriter.close();
                return true;
            }

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
		return false;
    }

    /**
     * 清空文件内容
     * @return
     */
    public boolean cleanLog()
    {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * 读取整个文件的内容，并返回一个字符串
     * @return
     */
    public String readLog()
    {
        StringBuilder content = new StringBuilder();
        if (this.file.exists()) {
            FileReader fileReader;
			try {
				fileReader = new FileReader(this.file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n"); // 追加每一行到StringBuilder，并加上换行符
                }
		        bufferedReader.close();
		        fileReader.close();
		        return content.toString();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
        }
		return null;
    }
}