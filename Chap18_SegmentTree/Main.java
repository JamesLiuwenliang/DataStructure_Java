package tmp;

/**
 * 在一定区间内实现查询一般采用线段树(segment tree,恒是平衡二叉树)
 *  使用数组实现更新,查询 : O(n)
 *  使用线段树实现更新,查询: O(logn)
 *
 */
public class Main {

    public static void main(String[] args){
        Integer[] nums  = {-2,0,3,-5,2,-1};
//        SegmentTree<Integer> segment = new SegmentTree<>(nums, new Merger<Integer>() {
//            @Override
//            public Integer merger(Integer a, Integer b) {
//                return a + b;
//            }
//
//        });

        // 用Lamda表达式也可以实现
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);


        System.out.println(segTree);

        System.out.println("Hello World.");
    }
}
