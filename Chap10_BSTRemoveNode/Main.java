import java.util.ArrayList;
import java.util.Random;
/**
 *  二分搜索树 : Binary Search Tree
 *              二分搜索树是二叉树
 *              二分搜索树的每个节点的值: 大于其左子树的所有节点的值
 *                                       小于其右子树的所有节点的值
 */
public class Main {


    public static  void main(String[] args){
        BST<Integer> bst = new BST<>();


        int[] nums = {3,6,8,2,4,10};

        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }

        bst.preOrder();
        System.out.println();
        bst.remove(3);
        bst.backOrder();
        System.out.println();

//        bst.levelOrder();
//        bst.preOrderNR();


//        Random random = new Random();
//        int n=1000;
//        for (int i = 0; i < n; i++) {
//            bst.add(random.nextInt(10000));
//        }
//
//        ArrayList<Integer> nums = new ArrayList<>();
//        while(!bst.isEmpty()){
//            nums.add(bst.removeMax());
//        }
//
//        System.out.println("RemoveMax() test.");
//        System.out.println(nums);
//        for (int i = 1; i < nums.size() ; i++) {
//            if(nums.get(i-1) < nums.get(i)) {
//                throw new IllegalArgumentException("Error");
//            }
//        }
//
//        System.out.println("removeMax test completed.");
//        System.out.println();



        System.out.println("Hello World.");
    }
}
