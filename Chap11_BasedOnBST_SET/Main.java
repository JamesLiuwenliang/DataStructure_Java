import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        ArrayList<String> words = new ArrayList<>();
        // 查找File.txt中有多少字
        FileOperation.readFile("File.txt",words);
        System.out.println("Total Size :" + words.size());

        // 利用二分搜索树实现的集合存储FIle.txt的不同的字
        BSTSet<String> set1 = new BSTSet<>();
        for (String word : words) {
            set1.add(word);
        }
        System.out.println("Total different words: "+set1.getSize());

        System.out.println("Hello World.");
    }
}
