package tmp;
/**
 * 哈希函数的设计: 尽可能减少哈希冲突
 * 哈希函数的设计 : 一个简单的解决办法 : 模一个素数
 * 哈希冲突的处理 : 链地址法
 * Java8 之前,每一个位置对应一个链表
 * Java8 之后,当哈希冲突达到一定程度,每一个位置从链表转成红黑树
 */
import java.util.*;

public class Main {

    public static class Student{
        String FirstName ;
        String LastName;
        int  grade;

        Student(String firstName ,String lastName ,int grade){
            this.grade = grade;
            this.FirstName = firstName;
            this.LastName = lastName;
        }

        @Override
        public boolean equals(Object o){
            if(this == o){
                return true;
            }

            if(o == null){
                return false;
            }

            if(this.getClass() != o.getClass()){
                return  false;
            }

            Student another = (Student) o;
            return this.LastName.toLowerCase().equals(another.LastName.toLowerCase()) &&
                    this.FirstName.toLowerCase().equals(another.FirstName.toLowerCase()) &&
                    another.grade == this.grade;

        }
    }
    public static void main(String[] args) {

        int a = 42;
        System.out.println(((Integer)a).hashCode());

        Student stu = new Student("fuck" , "java", 13);

        // 基于Hash表的集合
        HashSet<Student> set = new HashSet<>();
        set.add(stu);

        // 基于Hash表的映射
        HashMap<Student,Integer> map = new HashMap<Student, Integer>();
        map.put(stu,100);


        System.out.println("Hello World.");
    }
}