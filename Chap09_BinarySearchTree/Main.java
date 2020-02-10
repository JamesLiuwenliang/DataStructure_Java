
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
        bst.inOrder();
        System.out.println();
        bst.backOrder();
        System.out.println();
//        System.out.println();
//        System.out.println(bst);






        System.out.println("Hello World.");
    }
}
