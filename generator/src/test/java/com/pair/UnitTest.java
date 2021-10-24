package com.pair;

import org.junit.Test;

/**
 * 单元测试
 */
public class UnitTest {

    /** 获取当前项目路径 */
    public static final String PATH = System.getProperty("user.dir");

    /** 传入参数个数不正确 */
    @Test
    public void test0_1(){
        String[] args = {"-n","10"};
        Main.main(args);
    }

    /** 输入非法参数 */
    @Test
    public void test0_2(){
        String[] args = {"-n","10","?","?"};
        Main.main(args);
    }

    /** 传入的作答文件路径不存在 */
    @Test
    public void test0_3(){
        String[] args = new String[4];
        args[0] = "-e";
        args[1] = PATH + "/a.txt";
        args[2] = "-a";
        args[3] = PATH + "/b.txt";
        Main.main(args);
    }

    /** 测试生成题目功能 */
    @Test
    public void test1_1(){
        String[] args = {"-n","10","-r","20"};
        Main.main(args);
    }

    @Test
    public void test1_2(){
        String[] args = {"-n","100","-r","20"};
        Main.main(args);
    }

    @Test
    public void test1_3(){
        String[] args = {"-n","1000","-r","10"};
        Main.main(args);
    }

    @Test
    public void test1_4(){
        String[] args = {"-n","10000","-r","15"};
        Main.main(args);
    }

    /** 测试批改答案功能 */
    @Test
    public void test2_1(){
        String[] args = new String[4];
        args[0] = "-e";
        args[1] = PATH + "/src/test/resources/Test1.txt";
        args[2] = "-a";
        args[3] = PATH + "/src/test/resources/Answers1.txt";
        Main.main(args);
    }

    @Test
    public void test2_2(){
        String[] args = new String[4];
        args[0] = "-e";
        args[1] = PATH + "/src/test/resources/Test2.txt";
        args[2] = "-a";
        args[3] = PATH + "/src/test/resources/Answers2.txt";
        Main.main(args);
    }

    @Test
    public void test2_3(){
        String[] args = new String[4];
        args[0] = "-e";
        args[1] = PATH + "/src/test/resources/Test3.txt";
        args[2] = "-a";
        args[3] = PATH + "/src/test/resources/Answers3.txt";
        Main.main(args);
    }

    @Test
    public void test2_4(){
        String[] args = new String[4];
        args[0] = "-e";
        args[1] = PATH + "/src/test/resources/Test4.txt";
        args[2] = "-a";
        args[3] = PATH + "/src/test/resources/Answers4.txt";
        Main.main(args);
    }

}
