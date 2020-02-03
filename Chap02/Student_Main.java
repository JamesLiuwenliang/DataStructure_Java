public class Student_Main{

    private String name;
    private int score;
    public Student_Main(String studentName ,int studentScore){
        name = studentName;
        score = studentScore;
    }

    @Override
    public String toString(){
        return String.format("Student(name : %s , score : %d)",name ,score);
    }

    public static void main(String[] args){

        ArrayList01<Student_Main> array = new ArrayList01<>(20);


        array.insert(0,new Student_Main("zhangsan", 60));
        array.insert(0,new Student_Main("lisi", 30));
        array.insert(0,new Student_Main("wangwu", 20));

        System.out.println(array);
        System.out.println("Hello World...");
        System.out.println("Hello world...");

    }
}