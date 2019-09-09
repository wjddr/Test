package com.dou;

import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
//      testTransient();
        //print("Holis", "公众号:Hollis", "博客：www.hollischuang.com", "QQ：907607222");
        try (FileInputStream inputStream = new FileInputStream(new File("test"))) {
            System.out.println(inputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void print(String... strs)
    {
        for (int i = 0; i < strs.length; i++)
        {
            System.out.println(strs[i]);
        }
    }

    public static class TransientTest implements Serializable {
        private static final long serialVersionUID = 233858934995755239L;
        private String name1;
        private transient String name2;

        public TransientTest(String name1,String name2){
            this.name1 = name1;
            this.name2 = name2;
        }
        public String toString(){
            return String.format("TransientTest.toString(): name1=%s,name2=%s", name1,name2);
        }
    }

    public static class TransientTest2 implements Serializable{
        private static final long serialVersionUID = 233858934995755239L;
        private String name1;
        private transient String name2;

        public TransientTest2(String name1,String name2){
            this.name1 = name1;
            this.name2 = name2;
        }
        public String toString(){
            return String.format("TransientTest.toString(): name1=%s,name2=%s", name1,name2);
        }

        private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
            s.defaultWriteObject();
            s.writeObject(name2);
        }
        private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
            s.defaultReadObject();
            name2=String.valueOf(s.readObject());
        }
    }

    public static void testTransient(){
        String name1="常规属性",name2="transient修饰的属性";
        TransientTest2 test = new TransientTest2(name1, name2);
        System.out.println("序列化前："+test.toString());
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        String filePath = "C:\\Users\\wjdell\\IdeaProjects\\Test\\out\\production\\Test\\com\\dou\\TransientTest.obj";
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(test);

            inStream = new ObjectInputStream(new FileInputStream(filePath));
            TransientTest2 readObject = (TransientTest2)inStream.readObject();
            System.out.println("序列化后："+readObject.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
