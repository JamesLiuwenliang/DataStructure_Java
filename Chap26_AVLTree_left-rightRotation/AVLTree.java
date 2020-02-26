

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        // 高度
        public int height ;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 判断二叉树是否是一棵平衡二叉树
    public boolean isBalancedTree(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node ){
        if(node == null){
            return true;
        }

        int balancedFactor = getFactor(node) ;
        if(Math.abs(balancedFactor) > 1){
            return false;
        }
        else if(node.left != null){
            return isBalanced(node.left);
        }
        else{
            return isBalanced(node.right);
        }

        // return isBalanced(node.left)  && isBalanced(node.right);

    }

    // 判断该二叉树是否是一课二分搜索树(BST)
    public boolean isBST(){
        ArrayList<K> list = new ArrayList<>();

        // BST二分搜索树中序遍历得到的结果是从小到大的顺序
        inOrder(root ,list);
        for (int i = 0; i < list.size()-1; i++) {
            if(list.get(i+1).compareTo(list.get(i)) < 0){
                return false;
            }
        }

        return true;
    }

    private void inOrder(Node node , ArrayList<K> keys){
        if(node == null){
            return;
        }


        inOrder(node.left , keys);
        keys.add(node.key);
        inOrder(node.right ,keys);
    }

    /**
     *   对节点y进行向右旋转操作，返回旋转后新的根节点x
     *          y                              x
     *         / \                           /   \
     *        x   T4     向右旋转 (y)        z     y
     *       / \       - - - - - - - ->    / \   / \
     *      z   T3                       T1  T2 T3 T4
     *     / \
     *   T1   T2
     *   右旋转:
     *   y: 原树的根节点, x: 原树根节点的左子树 , z: x的左子树.
     *   右旋转步骤 : x.right = y ; y.left = T3;
     */

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = T3;

        // 更新height (只需更新x,y的高度值)
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     *  左旋转:
     *   对节点y进行向左旋转操作，返回旋转后新的根节点x
     *      y                             x
     *    /  \                          /   \
     *   T1   x      向左旋转 (y)       y     z
     *       / \   - - - - - - - ->   / \   / \
     *     T2  z                     T1 T2 T3 T4
     *        / \
     *       T3 T4
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        y.right = T2;
        x.left = y;

        // 维护高度(只有x,y需要更新)
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;

    }

    public int getHeight(Node node){
        if(node ==  null){
            return  0;
        }
        return node.height;
    }

    // 获得节点的平衡因子 , 左子树的高度减去右子树的高度
    private int getFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        }
        else if(key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }
        else {// key.compareTo(node.key) == 0
            node.value = value;
        }

        // 更新高度值
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getFactor(node);
//        if(Math.abs(balanceFactor) > 1){
//            System.out.println("Unbalanced : " + balanceFactor);
//        }

        // 当增加一个节点而破坏平衡性时,只会影响其祖辈节点
        // 平衡维护
        // 当不平衡发生且左子树的高度大于右子树高度(左子树的平衡因子大于等于0),即此时既没有平衡且是向左倾斜的,做右旋转
        // 右旋转之前不平衡但满足BST,右旋转之后平衡了且仍然满足BST
        //  y: 原树的根节点, x: 原树根节点的左子树 , z: x的右子树.
        // 右旋转步骤 : x.right = y ; y.left = z;

        /**********************************************************************
         *                                  平衡维护                           *
         **********************************************************************/

        // LL
        if(balanceFactor > 1 && getFactor(node.left) >= 0 ){
            // 做完右旋操作后就满足了以当前节点为根节点的二叉树既是平衡二叉树也是BST
            return rightRotate(node);
        }

        // RR
        if(balanceFactor < -1 && getFactor(node.right)<=0){
            return leftRotate(node);
        }

        // LR
        if(balanceFactor > 1 && getFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if(balanceFactor < -1 && getFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }


        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null) {
            return null;
        }

        if(key.equals(node.key)) {
            return node;
        }
        else if(key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        }
        else {// if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        }
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null ) {
            return null;
        }

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                }
                else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : "+ map.isBST());
            System.out.println("is Balanced : "+map.isBalancedTree());
        }

        System.out.println();
    }
}