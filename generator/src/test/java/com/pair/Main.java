package com.pair;

import com.pair.utils.Corrector;
import com.pair.utils.Generator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if (args.length == 4){
            //生成题目
            if (args[0].equals("-n") && args[2].equals("-r")){
                if (isNum(args[1]) && isNum(args[3])){
                    Generator.generate(Integer.parseInt(args[1]), Integer.parseInt(args[3]));
                }
            }else if (args[0].equals("-r") && args[2].equals("-n")){
                if (isNum(args[1]) && isNum(args[3])){
                    Generator.generate(Integer.parseInt(args[3]), Integer.parseInt(args[1]));
                }
            //批改答案
            }else if (args[0].equals("-e") && args[2].equals("-a")){
                File exFile = FileUtils.getFile(args[1]);
                File ansFile = FileUtils.getFile(args[3]);
                if (!exFile.exists()){
                    System.out.println("作答文件路径不存在");
                }else {
                    Corrector.correctEx(exFile, ansFile);
                }
            }else if (args[0].equals("-a") && args[2].equals("-e")){
                File exFile = FileUtils.getFile(args[3]);
                File ansFile = FileUtils.getFile(args[1]);
                if (!exFile.exists()){
                    System.out.println("作答文件路径不存在");
                }else {
                    Corrector.correctEx(exFile, ansFile);
                }
            }else {
                System.out.println("参数不合法");
                System.exit(0);
            }
        }else {
            System.out.println("参数输入有误");
            System.exit(0);
        }
    }

    /**
     * 正则判断纯数字
     * @param str 待判断字符串
     * @return 判断结果
     */
    public static boolean isNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

}