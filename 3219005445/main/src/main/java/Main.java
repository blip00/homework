import com.similarity.main.utils.FileUtil;

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
    }

}
