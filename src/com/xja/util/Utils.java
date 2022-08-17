package com.xja.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
Utils工具类：
将不同的功能封装为方法，就是可以直接通过调用方法使用它的功能，而无需考虑具体的功能实现细节。
*/
/**
 * @author GodLamp
 * @date 2022/8/13 10:25
 */
public class Utils {

    /**
     * 工具类中的方法都是静态方式访问的因此将构造器私有不允许创建对象
     */
    private Utils() {
        throw new AssertionError();
    }

    private static char[] chars = {
            '0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };

    private static char[] numbers = {
        '0','1','2','3','4','5','6','7','8','9'
    };

    /**
     * 传入字符数组、位数，生成 amount位 随机数（打乱字符数组内字符顺序）
     * @param arr
     * @param amount
     * @return
     */
    private static String randomSet(char[] arr,int amount){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i] + "");
        }
        Collections.shuffle(list);
        Random r = new Random();
        String str = "";
        int num = 0;
        for(int i = 0; i < amount; i ++){
            num = r.nextInt(list.size());
            str += list.get(num);
        }
        return str;
    }

    /**
     * 生成 amount位 随机值（包含大、小写字母，数字）
     * @param amount
     * @return
     */
    public static String randomNums(int amount){
        return randomSet(chars,amount);
    }

    /**
     * 生成 amount位 随机值（包含数字）
     * @param amount
     * @return
     */
    public static String randomNum(int amount){
        return randomSet(numbers,amount);
    }

    /**
     * 返回指定位数的随机数--截取系统时间（System.currentTimeMillis()）的位数
     * @param num
     * @return
     */
    public static String timeRand(int num){
        long time = System.currentTimeMillis();
        int all = String.valueOf(time).length();
        if(num < 0 || num > all) {
            return null;
        }
        String timeStr = (time + "").substring((all - num),all);
        if(timeStr != null && timeStr.length() > 0){
            return timeStr;
        }
        return null;
    }

    /**
     * 基于序列化和反序列化实现的克隆
     *      不仅仅是深度克隆，更重要的是通过泛型限定，可以检查出要克隆的对象是否支持序列化，
     *      这项检查是编译器完成的，不是在运行时抛出异常，这种是方案明显优于使用Object类的clone方法克隆对象。
     * @param obj 参数
     * @param <T> 泛型
     * @return 返回T对象
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T cloneBySerialize(T obj) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        ObjectInputStream ois;
        ByteArrayInputStream bin;

        try {
            oos = new ObjectOutputStream(bout);
            oos.writeObject(obj);
            bin = new ByteArrayInputStream(bout.toByteArray());
            ois = new ObjectInputStream(bin);
            return (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

    /**
     * 文件拷贝
     * @param source
     * @param target
     * @throws IOException
     */
    public static void fileCopy(String source, String target) throws IOException {
        try (InputStream in = new FileInputStream(source)) {
            try (OutputStream out = new FileOutputStream(target)) {
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }
        }
    }

    /**
     * 文件拷贝
     * @param source
     * @param target
     * @throws IOException
     */
    public static void fileCopyNio(String source, String target) throws IOException {
        try (FileInputStream in = new FileInputStream(source)) {
            try (FileOutputStream out = new FileOutputStream(target)) {
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                while(inChannel.read(buffer) != -1) {
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
            }
        }
    }

    /**
     * 统计给定文件中给定字符串的出现次数
     *
     * @param filename  文件名
     * @param word 字符串
     * @return 字符串在文件中出现的次数
     */
    public static int countWordInFile(String filename, String word) {
        int counter = 0;
        try (FileReader fr = new FileReader(filename)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int index = -1;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return counter;
    }



}
