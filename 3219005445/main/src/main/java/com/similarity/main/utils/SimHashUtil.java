package com.similarity.main.utils;

import com.hankcs.hanlp.HanLP;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

public class SimHashUtil {

    /**
     * SimHash算法
     * @param text 文本字符串
     * @return 计算得出的SimHash码
     */
    public static String simHash(String text){
        //排除文本过短无法进行检测的情况
        if (text.length() < 200){
            System.out.println("文本过短，无法提取词语。");
            return null;
        }
        //分词
        List<String> keywordList = HanLP.extractKeyword(text, text.length());
        //以数组表示向量
        int[] vector = new int[128];
        //简单加权后合并
        for (int i=0; i<keywordList.size(); i++){
            String keywordHash = hash(keywordList.get(i));
            int weight = (int) (10-(10.0*i)/keywordList.size());
            for (int j=0; j<vector.length; j++){
                if (keywordHash.charAt(j) == '1'){
                    vector[j] += weight;
                }else {
                    vector[j] -= weight;
                }
            }
        }
        //降维
        StringBuilder simHash = new StringBuilder();
        for (int v : vector) {
            if (v <= 0){
                simHash.append("0");
            }else {
                simHash.append("1");
            }
        }
        return simHash.toString();
    }

    /**
     * MD5加密算法计算hash值
     * @param originString 待加密字符串
     * @return 加密后128位的二进制串
     */
    private static String hash(String originString){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder(new BigInteger(1, md.digest(originString.getBytes("UTF-8"))).toString(2));
            if (sb.length() < 128) {
                //若hash值少于128位，则在低位以0补齐
                int val = 128 - sb.length();
                for (int j=0; j<(128-val); j++) {
                    sb.append("0");
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
