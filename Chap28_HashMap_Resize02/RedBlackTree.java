package tmp;

/**
 * 2-3树永远不会添加到空的节点,可以和最后找到的叶子节点做融合或者裂变成一棵新树(但要避免成为一棵不平衡的树)
 * 2-3树是绝对平衡的树
 *
 * 红黑树的性质:
 *      1. 每个节点或者是红色的或者是黑色的
 *      2. 根节点是黑色的
 *      3. 每个叶子节点(最后的空节点)是黑色的
 *      4. 如果一个节点是红色的,那么他的子节点都是黑色的
 *      5. 从任意一个节点到叶子节点,经过的黑色节点个数是一样的
 *      **所有红色的节点向左倾斜**
 *
 *  红黑树最大高度是 : 2*logn
 *  AVL树 : 更适合频繁的查找
 *  红黑树: 更适合增删操作

 */
import java.util.ArrayList;

public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;


        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RedBlackTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向红黑树中添加新的元素,最后要维持根节点为黑色
    public void add(K key, V value){
        root = add(root, key, value);
        // 最终要保持根节点的颜色是黑色
        root.color = BLACK;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    // node 是需要添加的节点(一般是红色的)
    private Node leftRotate(Node node){
        Node x = node.right;
        // 左旋转
        node.right = x.left;
        x.left = node;

        // 维持颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){

        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 添加新的元素
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            // 默认插入红色节点
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

        // 进入红黑树的维护
        if(isRed(node.right) && !(isRed(node.left))){
            // 左旋转
            node = leftRotate(node);
        }

        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }

        // 是否需要颜色翻转
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        return node;

    }

    // 判断节点node的颜色
    private boolean isRed(Node node){
        if(node == null) {
            return BLACK;
        }
        return node.color;
    }

    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

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
        else { // if(key.compareTo(node.key) > 0)
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


}















