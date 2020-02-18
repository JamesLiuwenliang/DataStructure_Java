package tmp;
import javax.lang.model.element.Element;
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

    // 测试函数 : 判断使用Heapify()和直接将传过来的数组排成堆谁更快
    private static double testHeap(Integer[] testData, boolean isHeapify){

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify) {
            // 使用Heapify()方法
            maxHeap = new MaxHeap<>(testData.length);
            maxHeap.heapify(testData);
        }
        else{
            // 不使用Heapify()方法,直接向堆中添加方法
            maxHeap = new MaxHeap<>(testData.length);
            for(int num: testData) {
                maxHeap.add(num);
            }
        }


        //添加完堆后进行extractMax(),还是相当于排序过程
        int[] arr = new int[testData.length];
        for(int i = 0 ; i < testData.length ; i ++) {
            arr[i] = maxHeap.extractMax();
        }

        for(int i = 1 ; i < testData.length ; i ++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


    public static void main(String[] args) {

        int n = 1000000;

        Random random = new Random();
        Integer[] testData1 = new Integer[n];
        for(int i = 0 ; i < n ; i ++) {
            testData1[i] = random.nextInt(Integer.MAX_VALUE);
        }

//        Integer[] testData2 = Arrays.copyOf(testData1, n);

        double time1 = testHeap(testData1, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData1, true);
        System.out.println("With heapify: " + time2 + " s");
    }
}
