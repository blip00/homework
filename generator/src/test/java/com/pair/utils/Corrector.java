package com.pair.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 批改器
 */
public class Corrector {

    /** 获取当前项目路径 */
    private static final String PATH = System.getProperty("user.dir");

    /**
     * 批改答案，输出结果文件
     * @param exFile 已作答题目文件
     * @param ansFile 答案文件
     */
    public static void correctEx(File exFile, File ansFile){
        try {
            List<String> originList;
            List<String> exList = new ArrayList<>();
            List<String> ansList = new ArrayList<>();
            //获取作答答案list
            originList = FileUtils.readLines(exFile, "UTF-8");
            for (String str : originList) {
                String[] split = str.split(" ");
                exList.add(split[split.length-1]);
            }
            //获取答案list
            originList = FileUtils.readLines(ansFile, "UTF-8");
            for (String str : originList) {
                String[] split = str.split(" ");
                ansList.add(split[split.length-1]);
            }
            //比对答案
            List<Integer> correctNum = new ArrayList<>();
            List<Integer> wrongNum = new ArrayList<>();
            for (int i=0; i<exList.size(); i++){
                if (exList.get(i).equals(ansList.get(i))){ //作答正确
                    correctNum.add(i+1);
                }else { //作答错误
                    wrongNum.add(i+1);
                }
            }
            //输出结果文件
            StringBuilder sb = new StringBuilder();
            String substring = "";
            sb.append("Correct: " + correctNum.size() + " (");
            for (Integer num : correctNum) {
                sb.append(num + ", ");
            }
            if (sb.toString().charAt(sb.length()-2) == ','){
                substring = sb.substring(0, sb.length()-2);
                sb = new StringBuilder(substring);
            }
            sb.append(")"+"\n"+"Wrong: " + wrongNum.size() + " (");
            for (Integer num : wrongNum) {
                sb.append(num + ", ");
            }
            if (sb.toString().charAt(sb.length()-2) == ','){
                substring = sb.substring(0, sb.length()-2);
                sb = new StringBuilder(substring);
            }
            sb.append(")");
            File gradeFile = FileUtils.getFile(PATH + "/Grade.txt");
            if (!gradeFile.exists()){
                exFile.createNewFile();
            }
            FileUtils.writeStringToFile(gradeFile, sb.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
