package com.pair.utils;

import com.pair.entity.Fraction;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 计算器
 */
public class Calculator {

    /**
     * 运算表达式求值
     * @param infixExp 运算表达式字符串
     * @return 计算结果字符串
     * @throws Exception
     */
    public static String calculate(String infixExp) throws Exception {
        String res = "";
        Stack<String> numStack = new Stack<>();
        //先将中序表示的算式转为后序表达式（以list存放各个运算符和运算数）
        List<String> suffixExp = infixToSuffix(infixExp);
        //计算
        for (String str : suffixExp) {
            if (str.matches("[+\\-×÷]")) { //遇到符号则取出前两个数进行计算
                String next = numStack.pop();
                String pre = numStack.pop();
                res = calTwo(pre, next, str);
                //当运算过程中出现负数时，中断计算
                if (res.equals("error")){
                    return res;
                }
                numStack.push(res);
            }else { //遇到数字则入栈
                numStack.push(str);
            }
        }
        //将计算结果化至最简
        Fraction fraction = FigureUtil.parseFigure(res);
        int gcd = ArithmeticUtils.gcd(fraction.getNumerator(), fraction.getDenominator());
        fraction.setNumerator(fraction.getNumerator() / gcd);
        fraction.setDenominator(fraction.getDenominator() / gcd);
        //整理结果：假分数则化为真分数或整数
        if (fraction.getNumerator() >= fraction.getDenominator()){
            fraction.setInteger(fraction.getNumerator() / fraction.getDenominator());
            fraction.setNumerator(fraction.getNumerator() % fraction.getDenominator());
        }
        if (fraction.getNumerator() == 0){
            res = fraction.getInteger().toString();
        } else {
            res = FigureUtil.parseFraction(fraction);
        }
        return res;
    }

    /**
     * 中序表达式转后序表达式
     * @param infix 中序表达式字符串
     * @return 1条list形式的后序表达式字符串
     * @throws Exception
     */
    private static List<String> infixToSuffix(String infix) throws Exception {
        if (StringUtils.isEmpty(infix.trim())) {
            return null;
        }
        ArrayList<String> suffix = new ArrayList<>();  // 保存后缀表达式
        Stack<String> opStack = new Stack<>();
        String[] split = infix.split(" ");
        for (String str : split) {
            if (str.matches("[+\\-×÷\\(\\)]")) {
                if (str.equals("(")) { //遇到"("入栈
                    opStack.push(str);
                }
                else if (str.equals(")")) { //遇到")"，出栈直到"("
                    String topOperator;
                    while (!(topOperator = opStack.pop()).equals("(")) {
                        suffix.add(topOperator);
                    }
                } else { //遇到运算符
                    //出栈，直到栈顶操作符的优先级大于当前操作符的优先级
                    while (!opStack.isEmpty() && opPriority(str) <= opPriority(opStack.peek())) {
                        suffix.add(opStack.pop());
                    }
                    //当前操作符入栈
                    opStack.push(str);
                }
            }else { //遇到数字直接输出
                suffix.add(str);
            }
        }
        if (!opStack.isEmpty()) {
            while (!opStack.isEmpty()) {
                suffix.add(opStack.pop());
            }
        }
        return suffix;
    }

    /**
     * 获取操作符的优先级
     * @param op 运算符字符串
     * @return 优先级
     * @throws Exception 非法运算符异常
     */
    private static int opPriority(String op) throws Exception {
        if (op == null) return 0;
        switch (op) {
            case "(": return 1;
            case "+":
            case "-": return 2;
            case "×":
            case "÷": return 3;
            default:break;
        }
        throw new Exception("Exception: 非法运算符");
    }

    /**
     * 两个数求值
     * @param preFigure 前一个运算数
     * @param nextFigure 后一个运算数
     * @param op 运算符
     * @return 计算结果字符串
     * @throws Exception 非法运算符异常
     */
    private static String calTwo(String preFigure, String nextFigure, String op) throws Exception {
        //解析运算数（带分数需化为假分数）
        Fraction pre = FigureUtil.parseFigure(preFigure);
        Integer x = pre.getDenominator();
        Integer a = x * pre.getInteger() + pre.getNumerator();
        Fraction next= FigureUtil.parseFigure(nextFigure);
        Integer y = next.getDenominator();
        Integer b = y * next.getInteger() + next.getNumerator();
        //结果统一表示为一般分数
        Fraction result = new Fraction();
        result.setInteger(0);
        //运算
        switch (op) {
            case "+":
                result.setNumerator(a*y+b*x);
                result.setDenominator(x*y);
                return FigureUtil.parseFraction(result);
            case "-":
                if ((a*y-b*x) < 0){
                    //计算结果出现非正数时，直接置标志位
                    return "error";
                }else {
                    result.setNumerator(a*y-b*x);
                    result.setDenominator(x*y);
                }
                return FigureUtil.parseFraction(result);
            case "×":
                result.setNumerator(a*b);
                result.setDenominator(x*y);
                return FigureUtil.parseFraction(result);
            case "÷":
                result.setNumerator(a*y);
                result.setDenominator(b*x);
                if (result.getDenominator() == 0){
                    //计算结果中分母为0时，直接置标志位
                    return "error";
                }else {
                    return FigureUtil.parseFraction(result);
                }
            default: break;
        }
        throw new Exception("Exception: 非法运算符");
    }

}
