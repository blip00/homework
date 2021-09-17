package com.similarity.main;

import com.similarity.main.utils.FileUtil;
import com.similarity.main.utils.HammingUtil;
import com.similarity.main.utils.SimHashUtil;


public class Main {

    /**
     * 主方法
     * @param args 命令行参数数组
     *             args[0]: 原文文件路径
     *             args[1]: 抄袭版论文文件路径
     *             args[2]: 答案文件路径
     */
    public static void main(String[] args) {
        String origin = FileUtil.readFile(args[0]);
        String originAdd = FileUtil.readFile(args[1]);
        if ((origin != null) && (originAdd != null)){
            String simHash1 = SimHashUtil.simHash(origin);
            String simHash2 = SimHashUtil.simHash(originAdd);
            String  hamming = HammingUtil.hamming(simHash1, simHash2);
            FileUtil.writeFile(args[2], hamming);
        }
    }

}