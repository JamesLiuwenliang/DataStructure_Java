package tmp;

/**
 * 总共有M个地址
 * 如果放入哈希表的元素为N
 * 如果每个地址是链表 : O(N/M)
 * 如果每个地址是平衡树 : O(log(N/M))
 *
 */

import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                if (bst.contains(word)) {
                    bst.set(word, bst.get(word) + 1);
                }
                else {
                    bst.add(word, 1);
                }
            }

            for(String word: words) {
                bst.contains(word);
            }

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL
            startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word)) {
                    avl.set(word, avl.get(word) + 1);
                }
                else {
                    avl.add(word, 1);
                }
            }

            for(String word: words) {
                avl.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");


            // Test RBTree
            startTime = System.nanoTime();

            RedBlackTree<String, Integer> rbt = new RedBlackTree<>();
            for (String word : words) {
                if (rbt.contains(word)) {
                    rbt.set(word, rbt.get(word) + 1);
                }
                else {
                    rbt.add(word, 1);
                }
            }

            for(String word: words) {
                rbt.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");


            // Test HashMap
            startTime = System.nanoTime();

            HashTable<String, Integer> Ht = new HashTable<>();
            for (String word : words) {
                if (Ht.contains(word)) {
                    Ht.set(word, Ht.get(word) + 1);
                }
                else {
                    Ht.add(word, 1);
                }
            }

            for(String word: words) {
                Ht.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable: " + time + " s");

        }

        System.out.println();
    }
}