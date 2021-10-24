package com.pair.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 生成器
 */
public class Generator {

    /** 候选运算符数组 */
    public static final String[] OPERATORS = {"+", "-", "×", "÷"};

    /** 获取当前项目路径 */
    private static final String PATH = System.getProperty("user.dir");

    /**
     * 生成题目文件和答案文件
     * @param num 生成题目的个数
     * @param range 题目中数值（自然数、真分数和真分数分母）的范围
     */
    public static void generate(int num, int range) {
        Map<String,String> map = createExp(num, range);
        try {
            //创建文件
            File exFile = FileUtils.getFile(PATH + "/Exercises.txt");
            File ansFile = FileUtils.getFile(PATH + "/Answers.txt");
            if (!exFile.exists()){
                exFile.createNewFile();
            }
            if (!ansFile.exists()){
                ansFile.createNewFile();
            }
            //写入内容
            Set<String> keySet = map.keySet();
            Iterator<String> it = keySet.iterator();
            StringBuilder exSb = new StringBuilder();
            StringBuilder ansSb = new StringBuilder();
            for (int i=0; it.hasNext(); i++){
                String answer = it.next();
                exSb.append((i+1) +". "+ map.get(answer) +"\n");
                ansSb.append((i+1) +". "+ answer +"\n");
            }
            FileUtils.writeStringToFile(exFile, exSb.toString(), "UTF-8");
            FileUtils.writeStringToFile(ansFile, ansSb.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成运算表达式
     * @param num 生成题目的个数
     * @param range 题目中数值（自然数、真分数和真分数分母）的范围
     */
    public static Map<String,String> createExp(int num, int range){
        //答案-表达式map
        Map<String,String> map = new HashMap<>();
        //生成表达式
        Random random = new Random();
        for (int i=0; i<num; ){
            StringBuilder sb = new StringBuilder();
            //随机生成运算符
            int opCount = random.nextInt(3)+1;
            String[] ops = new String[opCount];
            for (int j=0; j<opCount; j++){
                ops[j] = OPERATORS[random.nextInt(4)];
            }
            //随机生成运算数
            int figureCount = opCount + 1;
            String[] figures = new String[figureCount];
            for (int j=0; j<figureCount; j++){
                figures[j] = createFigure(range);
            }
            //随机决定是否生成括号，若是则决定左括号生成的位置
            boolean isBracket = false;
            int index = 0;
            if (opCount > 1){
                isBracket = random.nextBoolean();
            }
            if (isBracket){
                index = random.nextInt(figureCount - 1);
            }
            //组合表达式
            for (int j=0; j<figures.length-1; j++){
                if (isBracket && j==index){
                    sb.append("( ");
                }
                sb.append(figures[j] + " ");
                if (isBracket && j==index+1){
                    sb.append(") ");
                }
                sb.append(ops[j] + " ");
            }
            sb.append(figures[figures.length-1]);
            if (isBracket && figures.length-1==index+1){
                sb.append(" )");
            }
            String exp = sb.toString();
            //计算结果，检验生成的表达式是否合法，合法的才能存入map中
            try {
                String result = Calculator.calculate(exp);
                if (!result.equals("error") && map.get(result)==null){
                    map.put(result, exp);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 随机生成运算数
     * @param range 数值（自然数、真分数和真分数分母）的范围
     * @return 生成的运算数字符串
     */
    private static String createFigure(int range){
        String figure;
        Random random = new Random();
        //随机决定是否生成整数
        boolean flag = random.nextBoolean();
        if (flag || range<3){
            //生成整数（如果range=1或2，不允许生成分数）
            figure = String.valueOf(random.nextInt(range-1)+1);
        }else {
            //生成分数
            figure = createFraction(range);
        }
        return figure;
    }

    /**
     * 随机生成分数
     * @param range 真分数和真分数分母的范围
     * @return 表示分数的字符串
     */
    private static String createFraction(int range){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        //随机决定是否生成带分数
        boolean flag = random.nextBoolean();
        if (flag){
            //生成带分数的整数部分
            sb.append((random.nextInt(range-1)+1)+"'");
        }
        //生成一个最简真分数
        int a = random.nextInt(range-1)+1;
        int b = random.nextInt(range-1)+1;
        while (a==b){
            b = random.nextInt(range-1)+1;
        }
        int gcd = ArithmeticUtils.gcd(a, b);
        a /= gcd;
        b /= gcd;
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        sb.append(min + "/" + max);
        return sb.toString();
    }

}
