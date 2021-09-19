package com.similarity.main;

import org.junit.Test;

public class UnitTest {

    //获取当前项目路径
    public static final String PATH = System.getProperty("user.dir")+"/src/test/resources/";

    /** 传入参数个数不正确 */
    @Test
    public void test0_1(){
        String[] args = {
//                PATH + "test/orig.txt",
                PATH + "test/orig_0.8_add.txt",
                PATH + "out/ans.txt"
        };
        Main.main(args);
    }

    /** 传入的文件路径不存在 */
    @Test
    public void test0_2(){
        String[] args = new String[3];
        args[0] = PATH + "test/abc.txt";
        args[1] = PATH + "test/orig_0.8_add.txt";
        args[2] = PATH + "out/ans.txt";
        Main.main(args);
    }

    /** test包内文件检测 */
    @Test
    public void test1_1(){
        String[] args = new String[3];
        args[0] = PATH + "test/orig.txt";
        args[1] = PATH + "test/orig_0.8_add.txt";
        args[2] = PATH + "out/ans1_1.txt";
        Main.main(args);
    }

    @Test
    public void test1_2(){
        String[] args = new String[3];
        args[0] = PATH + "test/orig.txt";
        args[1] = PATH + "test/orig_0.8_del.txt";
        args[2] = PATH + "out/ans1_2.txt";
        Main.main(args);
    }

    @Test
    public void test1_3(){
        String[] args = new String[3];
        args[0] = PATH + "test/orig.txt";
        args[1] = PATH + "test/orig_0.8_dis_1.txt";
        args[2] = PATH + "out/ans1_3.txt";
        Main.main(args);
    }

    @Test
    public void test1_4(){
        String[] args = new String[3];
        args[0] = PATH + "test/orig.txt";
        args[1] = PATH + "test/orig_0.8_dis_10.txt";
        args[2] = PATH + "out/ans1_4.txt";
        Main.main(args);
    }

    @Test
    public void test1_5(){
        String[] args = new String[3];
        args[0] = PATH + "test/orig.txt";
        args[1] = PATH + "test/orig_0.8_dis_15.txt";
        args[2] = PATH + "out/ans1_5.txt";
        Main.main(args);
    }

    /** test2包内文件检测 */
    @Test
    public void test2_1(){
        String[] args = new String[3];
        args[0] = PATH + "test2/orig.txt";
        args[1] = PATH + "test2/orig_0.8_add.txt";
        args[2] = PATH + "out/ans2_1.txt";
        Main.main(args);
    }

    @Test
    public void test2_2(){
        String[] args = new String[3];
        args[0] = PATH + "test2/orig.txt";
        args[1] = PATH + "test2/orig_0.8_del.txt";
        args[2] = PATH + "out/ans2_2.txt";
        Main.main(args);
    }

    @Test
    public void test2_3(){
        String[] args = new String[3];
        args[0] = PATH + "test2/orig.txt";
        args[1] = PATH + "test2/orig_0.8_dis_1.txt";
        args[2] = PATH + "out/ans2_3.txt";
        Main.main(args);
    }

    @Test
    public void test2_4(){
        String[] args = new String[3];
        args[0] = PATH + "test2/orig.txt";
        args[1] = PATH + "test2/orig_0.8_dis_10.txt";
        args[2] = PATH + "out/ans2_4.txt";
        Main.main(args);
    }

    @Test
    public void test2_5(){
        String[] args = new String[3];
        args[0] = PATH + "test2/orig.txt";
        args[1] = PATH + "test2/orig_0.8_dis_15.txt";
        args[2] = PATH + "out/ans2_5.txt";
        Main.main(args);
    }

}
