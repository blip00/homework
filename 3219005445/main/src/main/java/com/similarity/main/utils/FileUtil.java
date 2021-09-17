package com.similarity.main.utils;

import java.io.*;

public class FileUtil {

    /**
     * 读取文件
     * @param filePath 文件路径
     * @return 文件内容字符串
     */
    public static String readFile(String filePath){
        File file = new File(filePath);
        if (file.isFile() && file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null){
                    sb.append(line);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 写入文件
     * @param filePath 文件路径
     * @param content 文件内容字符串
     */
    public static void writeFile(String filePath, String content){
        FileOutputStream fos;
        File file = new File(filePath);
        try {
            if (file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}