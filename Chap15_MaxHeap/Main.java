import java.util.*;
/**
 *      O(logn) : 一般都和树有关
 *      二叉堆   :  完全二叉树(把元素顺序排列成树的形状,堆中某个节点的值总是不大于其父亲节点的值)
 *
 *      利用数组可以实现二叉堆 : 如何表示子节点和父节点(数组[0]被空出的情况下):  parent(i) = i/2 ;
 *                                                                           left(child) = 2*i ;
 *                                                                           right(i) = i*2+1.
 *                              数组[0]没有被空出的情况下(相当于加了一个偏移) : parent(i) = (i-1) /2 ;
 *                                                                           left(child) = 2*i + 1;
 *                                                                           right(child) = 2*i +2 .
 *
 *
 *   优先队列:
 *
 *                      入队          出队(拿出最大元素)
 *  普通线性结构         O(1)          O(n)
 *  顺序线性结构         O(n)          O(1)
 *           堆         O(logn)       O(logn)
 */


public class Main {

    public static void main(String[] args){
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];

        /**
         *  相当于进行了一次排序
         */
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 0; i < n-1; i++) {
            if(arr[i+1] > arr[i]){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap is successful.");
        System.out.println("Hello World.");
    }
}
