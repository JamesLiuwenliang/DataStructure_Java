package tmp;
/**
 * 对比AVL ,BST, 红黑树
 * 只有添加操作,添加的数值是顺序的
 * (BST退化成链表)
 */
import java.util.ArrayList;
import java.util.Random;

public class Main3 {

    public static void main(String[] args) {

        int n = 20000000;

        ArrayList<Integer> testData = new ArrayList<>(n);
        for(int i = 0 ; i < n ; i ++) {
            testData.add(i);
        }

        // Test AVL
        long startTime = System.nanoTime();

        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer x: testData) {
            avl.add(x, null);
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");


        // Test RBTree
        startTime = System.nanoTime();

        RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
        for (Integer x: testData) {
            rbt.add(x, null);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time + " s");
    }
}