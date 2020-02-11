import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 继承可比较性
 * BST : Binary Search Tree
 * 本例实现的二分搜索树不包含重复元素
 * @author Administrator
 */
public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size ==0;
    }

    public void add(E e){
        root = add(root ,e );
    }

    // 向以node为根的二分搜索树中插入元素E,递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node ,E e){
        if (node == null){
            size ++;
            return new Node(e);
        }
//        if(e.equals(node.e)){
//            return;
//        }else if(e.compareTo(node.e) < 0 && node.left == null){
//            node.left = new Node(e);
//            size ++ ;
//            return ;
//        }else if(e.compareTo(node.e) > 0 && node.right == null){
//            node.right = new Node(e);
//            size ++ ;
//            return ;
//        }

        // 最基本的一步操作
        if(e.compareTo(node.e) < 0  ){
            node.left = add(node.left ,e );
        }
        else if(e.compareTo(node.e) > 0){
            node.right = add(node.right,e );
        }

        return node ;
    }

    public boolean contains(E e){
        return contains(root ,e);
    }

    /**
     *  查询操作
     */
    private boolean contains(Node node , E e ){
        if(node == null){
            return false;
        }

        if(e.compareTo(node.e) == 0){
            return true;
        }
        else if(e.compareTo(node.e) < 0){
            contains(node.left,e);
        }
        else if(e.compareTo(node.e) > 0){
            contains(node.right, e);
        }

        return false;

    }

    /**
     *  前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){

        // 递归终止条件
        if(node == null){
            return;
        }

        System.out.print(node.e+ " ");
        preOrder(node.left);
        preOrder(node.right);

    }

    /**
     * 非递归前序遍历(本质上是深度优先遍历),借助栈实现
     */
    public void preOrderNR(){

        System.out.println("深度优先遍历,基于栈实现");

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.print(cur.e+" ");

            if(cur.right != null) {
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }

        }

        System.out.println();
    }

    /**
     *  二分搜索树的层序遍历,借助队列实现
     */
    public void levelOrder(){

        System.out.println("层序遍历,基于队列实现");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while( ! queue.isEmpty()){
            Node cur = queue.remove();
            System.out.print(cur.e+" ");

            if(cur.left !=  null) {
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
        System.out.println();

    }

    /**
     *  中序遍历: 对二分搜索树恰好是排序的结果
     */
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){

        // 递归终止条件
        if(node == null){
            return;
        }

        inOrder(node.left);
        System.out.print(node.e+ " ");
        inOrder(node.right);
    }

    /**
     *  后序遍历 : 先处理完子节点, 为二分搜素树释放内存
     */
    public void backOrder(){
        backOrder(root);
    }

    private void backOrder(Node node){

        // 递归终止条件
        if(node == null){
            return;
        }


        backOrder(node.left);
        backOrder(node.right);
        System.out.print(node.e+ " ");

    }

    /**
     * 找到最小的元素节点
     */
    public E minimum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null){
            return node ;
        }
        return minimum(node.left);
    }

    /**
     * 找到最大的元素节点
     */
    public E maximum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right == null){
            return node ;
        }
        return maximum(node.right);
    }

    /**
     * 删除最小值所在节点,返回最小值
     * Video : 6-11
     */
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * Video : 6-11
     */
    private Node removeMin(Node node){
        if(node.left == null){
            // 当前节点可能有右子节点
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);

        return node ;
    }

    /**
     * 删除最小值所在节点,返回最小值
     * Video : 6-11
     */
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * Video : 6-11
     */
    private Node removeMax(Node node ){
        if(node.right == null){
            Node leftnode = node.left;
            node.left = null;
            size--;
            return leftnode;
        }

        node.right = removeMax(node.right);
        return node ;
    }

    /**
     * 删除任意节点 (Hibbard Deletion)
     *     找到要删除节点的右子树的最小节点(后继)
     *  Video : 6-12
     */
    public void remove(E e){
        root = remove(root,e);
    }

    private Node remove (Node node, E e){

        if(node == null){
            return null;
        }

        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left,e );
            return node;
        }
        else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e );
            return node;
        }
        else {

            /**
             * 删除该节点,分情况讨论
             */
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            /**
             *  待删除节点的左右子树均不为空的情况
             *  找到比待删节点大的最小节点,即待删节点右子树的最小节点
             *  用这个节点顶替待删节点的位置
             *  小细节: removeMin() 里含有 size-- 操作
             */
            Node Min_Right_Node = minimum(node.right);
            // remove() 返回的是子树的根节点
            Min_Right_Node.right = removeMin(node.right);
            Min_Right_Node.left = node.left;

            node.left = node.right = null;
            return Min_Right_Node;

        }


    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    private  void generateBSTString(Node node , int depth ,StringBuilder res){
        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        // 访问左子树
        generateBSTString(node.left ,depth + 1, res);
        // 访问右子树
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }




}
