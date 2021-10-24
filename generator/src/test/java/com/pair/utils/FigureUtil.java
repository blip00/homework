package com.pair.utils;

import com.pair.entity.Fraction;

/**
 * 数字处理工具类
 */
public class FigureUtil {

    /**
     * 将数字统一解析为分数形式（fraction实体）
     * @param figure 数字字符串
     * @return 解析后的fraction实体
     */
    public static Fraction parseFigure(String figure){
        Fraction fraction = new Fraction();
        //判断数字的类型
        if (figure.contains("/")){ //是分数
            if (figure.contains("'")){ //是带分数
                String[] split = figure.split("'");
                fraction.setInteger(Integer.valueOf(split[0]));
                split = split[1].split("/");
                fraction.setDenominator(Integer.valueOf(split[1]));
                fraction.setNumerator(Integer.valueOf(split[0]));
            }else { //是一般真分数
                String[] split = figure.split("/");
                fraction.setInteger(0);
                fraction.setDenominator(Integer.valueOf(split[1]));
                fraction.setNumerator(Integer.valueOf(split[0]));
            }
        }else { //是整数
            fraction.setInteger(0);
            fraction.setDenominator(1);
            fraction.setNumerator(Integer.valueOf(figure));
        }
        return fraction;
    }

    /**
     * 解析fraction实体拼接成数字字符串
     * @param fraction fraction实体
     * @return 解析后的数字字符串
     */
    public static String parseFraction(Fraction fraction){
        String figure;
        Integer integer = fraction.getInteger();
        Integer denominator = fraction.getDenominator();
        Integer numerator = fraction.getNumerator();
        //判断数字的类型
        if (integer == 0){
            if (denominator == 1){ //是整数
                figure = String.valueOf(numerator);
            }else { //是一般真分数
                figure = numerator +"/"+ denominator;
            }
        }else { //是带分数
            figure = integer +"'"+ numerator +"/"+ denominator;
        }
        return figure;
    }

}
