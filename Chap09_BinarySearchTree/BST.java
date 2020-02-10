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
