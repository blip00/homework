package com.similarity.main.utils;

public class HammingUtil {

    /**
     * 计算2个simHash值之间的相似度
     * @param simHash1
     * @param simHash2
     * @return 相似度
     */
    public static String hamming(String simHash1, String simHash2){
        //将2个simHash值按位异或计算海明距离
        int distance = 0;
        for (int i=0; i<simHash1.length(); i++){
            if (simHash1.charAt(i) != simHash2.charAt(i)){
                distance++;
            }
        }
        //计算相似度
        return String.format("%.2f", 1-1.0*distance/simHash1.length());
    }

}