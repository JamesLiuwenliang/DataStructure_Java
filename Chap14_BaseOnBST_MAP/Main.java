/**
 *  有序映射中的键具有顺序性: 基于BST实现
 *  无序映射中的键没有顺序性: 基于链表实现(哈希表)
 *  多重映射: 键可重复
 *  映射在只考虑键的时候可以认为就是集合
 *  
 */
package tmp;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        ArrayList<String> words = new ArrayList<>();
        // 查找File.txt中有多少字
        FileOperation.readFile("pride-and-prejudice.txt",words);
        System.out.println("Total Size :" + words.size());

        // 利用二分搜索树实现的集合存储FIle.txt的不同的字
        BSTMap<String,Integer> map = new BSTMap<>();
        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word) + 1);
            }
            else{
                map.add(word,1);
            }
        }
        System.out.println("Total different words: "+map.getSize());
        System.out.println("Frequently of PRIDE: "+ map.get("pride"));
        System.out.println("Frequently of PRIEJUDICE: "+ map.get("prejudice"));
        map.remove("pride");
        System.out.println("Frequently of PRIDE: "+ map.get("pride"));

        System.out.println("Hello World.");
    }
}
