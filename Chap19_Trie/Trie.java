package tmp;
import java.util.*;


public class Trie {
    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord ){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private int size;
    private Node root;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return  size;
    }

    /**
     *  增操作 : 向Trie中添加字符串
     *  可尝试利用递归实现
     */
    public void add(String word){

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);

        }
        // 判断是否存在这个char
        if(cur.isWord != true) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查操作:
     */
    public boolean contains(String word){

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.containsKey(c) == true){
                cur = cur.next.get(c);
            }
            else {
                return false;
            }
        }

        return cur.isWord;
    }




}
