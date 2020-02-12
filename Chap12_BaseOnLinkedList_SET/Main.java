/**
 *  链表的增,删,查的操作复杂度是O(N)
 *  二分搜索树的增,删,查的操作复杂度是O(h) [h是二分搜索树的高度]
 *  2^h-1 = n -->  h = log(n+1)  -->  h = O(logn)
 *  即满的二分搜索树的复杂度是 O(logn)
 *  但是当二分搜索树中的数据是连续的时候,二分搜索树可能会退化成链表,时间复杂度变为O(n)(h=n)
 */


import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        ArrayList<String> words = new ArrayList<>();
        // 查找File.txt中有多少字
        FileOperation.readFile("File.txt",words);
        System.out.println("Total Size :" + words.size());

        // 利用二分搜索树实现的集合存储FIle.txt的不同的字
        LinkedListSET<String> set1 = new LinkedListSET<>();
        for (String word : words) {
            set1.add(word);
        }
        System.out.println("Total different words: "+set1.getSize());

        System.out.println("Hello World.");
    }
}
