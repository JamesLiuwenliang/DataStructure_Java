/**
 *  自定义数组实现增删改查
 */
public class Main {
    public static void main(String[] args){

        int[] scores = new int[]{100,99,59};
        for (int score : scores){
            System.out.println(score);
        }
        System.out.println("Just Test");
        System.out.println("--------------------------");

        /**
         *  数组实现
         */
        ArrayList01<Integer> array = new ArrayList01<>(20); //泛型内不能存放基本数据类型,只能放包装类
        for(int i=1;i<=10;i++){
            array.addLast(i);
        }
        int length = array.getSize();
        int capacity = array.getCapacity();
        

        System.out.println(array);

        System.out.println("--------------------------");

        array.insert(6, 70000);
        System.out.println(array);
        System.out.println("--------------------------");

        array.delete(1);
        System.out.println(array);

        System.out.println("Hello World...");

    }
}
