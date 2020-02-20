
/**
 * 在一定区间内实现查询一般采用线段树(segment tree,恒是平衡二叉树)
 *  使用数组实现更新,查询 : O(n)
 *  使用线段树实现更新,查询: O(logn)
 *
 *   线段树最重要的两个操作: 创建 & 查询区间
 *
 */

 /***********************************************
  *      两个关键函数 buildSegmentTree 和query     *
  **********************************************/

 /**
 * buildSegmentTree
 * [-2,0,3,-5,2,-1]
 
                                     [-2 ... -1]{和为-3}
                        [-2 0 3]{和为1}                 [-5 2 -1]{和为-4}
                [-2 0]{和为-2}    [3]               [-5 2]{和为-3}  [-1]
            [-2]    [0]      null  null         [-5]   [2]      null  null

驻存的是线段树的每个节点中的线段元素的和
{-3 , 1 , -4 , -2 , 3 , -3 , -1 ........}

 */
/**
 * query



 
 */

public class Main {

    public static void main(String[] args){
        Integer[] nums  = {-2,0,3};
//        SegmentTree<Integer> segment = new SegmentTree<>(nums, new Merger<Integer>() {
//            @Override
//            public Integer merger(Integer a, Integer b) {
//                return a + b;
//            }
//
//        });

        // 用Lamda表达式也可以实现
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);

        // -2 + 0 + 3
        // System.out.println(segTree.query(0,2));

        System.out.println(segTree);

        System.out.println("Hello World.");
    }
}
