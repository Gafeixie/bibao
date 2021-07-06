import java.io.*;

/**
 * @author: 谢佳辉
 * @date 2021/6/22 4:46 下午
 */
public class test {
    public static void main(String[] args) {

        FileInputStream fis = null;
        try {
            File file = new File("/Users/mac/Downloads/web-service/logs/lab.log");
            File file1 = new File("/Users/mac/Downloads/web-service/logs/lab1.log");
            fis = new FileInputStream(file);
            int available = fis.available();
            System.out.println(available);
            long n = 500000;
            FileReader fileReader = new FileReader("/Users/mac/Downloads/web-service/logs/lab.log");
            BufferedReader brTest = new BufferedReader(fileReader);
            StringBuffer buf = new StringBuffer();
            while (brTest.readLine()!=null){
                String text = brTest .readLine();
                if(text!=null){
                String[] strs= text.split("\\s+");
                if(strs[6].equals("c.b.w.controller.GoodsController")){
                    text = strs[8]+","+strs[9]+","+strs[10]+","+strs[11];
                    System.out.println(text);
                    buf.append(text);
                }}
            }
            int available2 = fis.available();
            System.out.println("available2 is  " + available2);
            FileOutputStream fos = new FileOutputStream(file1);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }}
