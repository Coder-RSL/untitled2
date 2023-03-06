package com.ruoyi.sidebarTree.pythonCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Author jia wei
 * Date 2022/8/8 9:46
 */
public class PythonUse {

    private String pythonEvn="C:\\Users\\Administrator\\AppData\\Local\\Programs\\Python\\Python310\\python.exe";
    private String pythonCodeDict="C:\\SeedlinManagement\\pythonCode";
//    private String pythonEvn="D:\\pythonEnvironment\\python.exe";
//    private String pythonCodeDict="D:\\java笔记\\种苗项目及笔记\\RuoYi-Vue22\\sidebar-tree\\src\\main\\resources\\pythonCode";
    /**
     * 苗盘超绿检测，使用的是checkGreen.py文件
     *
     * @param fileUrl 检测图片的url
     */
    public void checkGreen(String fileUrl) {
        File file = new File(fileUrl);
        String filePath = file.getParent();

        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        try {
            String[] args1 = new String[]{pythonEvn, pythonCodeDict+"\\checkGreen.py", filePath, fileName};
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 苗盘穴孔检测
     *
     * @param fileUrl 检测图片的url
     */
    public void checkHole(String fileUrl) {
        File file = new File(fileUrl);
        String filePath = file.getParent();
        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        System.out.println("fileName"+fileName);
        try {
            String[] args1 = new String[]{pythonEvn, pythonCodeDict+"\\CheckHole.py", filePath, fileName};
            for(String str : args1) System.out.print(str+" ");
            System.out.println();
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cleanFile(File file){
        File[] list = file.listFiles();
        if(list.length==0){//如果长度为零则删除
            file.delete();
        }
        else{
            for (File childrenfile:list) {
                if(childrenfile.isDirectory())
                    cleanFile(childrenfile);//递归
                else
                    childrenfile.delete();//删除其他类型文件
                }
            }
        file.delete();
    }


    /**
     * 生长点预测
     * @param fileUrl
     * @return 图片链接
     */
    public String predictGrowPoint(String fileUrl,long treeId) {
        //这方法相较比较特殊，生成的图片到特定的文件夹中，因此处理前，先把文件夹清空
        File parentFile =new File("C:\\Users\\Administrator\\Desktop\\YOLOX-growpoint\\YOLOX_outputs\\yolox_voc_s\\vis_res");
        cleanFile(parentFile);
        parentFile.mkdirs();
        File file = new File(fileUrl);

        try {
            String[] args1 = new String[]{pythonEvn,
                    "demo.py",
                    "--path",
                    fileUrl
                    };
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            System.out.println("begin");
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        StringBuffer reslutUrl=new StringBuffer("");
        File path=new File("C:\\Users\\Administrator\\Desktop\\YOLOX-growpoint\\YOLOX_outputs\\yolox_voc_s\\vis_res");
        //列出该目录下所有文件和文件夹
        reslutUrl.append(path.getAbsoluteFile());
        File[] files = path.listFiles();
        //按照目录中文件最后修改日期实现倒序排序
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                return (int)(file2.lastModified()-file1.lastModified());
            }
        });
        //取最新修改的文件，get文件名
        parentFile = files[0];
        parentFile.renameTo(new File("C:\\Users\\Administrator\\Desktop\\YOLOX-growpoint\\YOLOX_outputs\\yolox_voc_s\\vis_res\\"+treeId));
        reslutUrl.append("\\"+treeId);
        File newFile =new File(reslutUrl.toString());
        File[] files1 = newFile.listFiles();
        for(File f:files1){
            if(f.getName().contains(".jpg")||f.getName().contains(".png"))
                reslutUrl.append("\\"+f.getName());
        }
        return reslutUrl.toString();
    }

    /**
     * 叶片检测
     *
     * @param fileUrl 需要检测的图片文件地址
     */
    public int detectLeaf(String fileUrl) {
        File file = new File(fileUrl);
        String filePath = file.getParent();
        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        File file1 = new File(filePath);
        if(!file1.exists()) return 0;
        try {
            String[] args1 = new String[]{pythonEvn,  pythonCodeDict+ "\\DetectLeaf.py", filePath, fileName, filePath};
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e){

            return 0;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return 1;
    }

//    /**
//     * 生长预测，用来对csv文件进行预测
//     *
//     * @param fileUrl csv文件所在的文件地址
//     * @return
//     */
    public void predictGrow(long fileName) {

        try {
            String fileDictory ="C:\\ruoyi\\uploadPath\\predictGrowPoint\\result";
            String predictFile="C:\\ruoyi\\uploadPath\\predictGrowPoint\\predict\\"+fileName+"\\predict.csv";
            String[] args1 = new String[]{pythonEvn,  "C:\\SeedlinManagement\\pythonCode\\LSTM_predict.py",
                    fileDictory, fileName+"", predictFile};
            Process proc = null;// 执行py文件
            proc = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
//            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
