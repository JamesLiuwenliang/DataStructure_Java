package tmp;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
/**
 * Trie字典树
 * 字典:基于二叉树结构,复杂度是O(logn);
 * Trie : 复杂度和条目无关,时间复杂度为O(w),w为查询的字符串长度
 *      每个节点有26个指向下一个节点的指针(只放英文且不考虑大小时);
 *      每个节点有若干指向下一个节点的指针
 *      class Node {
 *          boolean isWord ; // 确定是否是尾节点,即是否到此构成了一个单词
 *          Map<char,Node> node;
 *      }
 *
 */
public class Main {

    public static void main(String[] args){
        ArrayList<String> words = new ArrayList<>();

        if(FileOperation.readFile("pride-and-prejudice.txt",words)){
            long startTime = System.nanoTime();
            Trie trie = new Trie();
            for(String word : words){
                trie.add(word);
            }
            for(String word : words){
                trie.contains(word);
            }

            long endtime = System.nanoTime();
            double time = (endtime -startTime) / 1000000000.0;
            System.out.println("Total words : "+trie.getSize());
            System.out.println("Total time : "+time);
        }




        System.out.println("Hello World.");
    }
}
