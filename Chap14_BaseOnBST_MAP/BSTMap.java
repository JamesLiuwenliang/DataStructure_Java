package tmp;
import tmp.Node;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    private Node root;
    private int size;

    public BSTMap(){
        size =0;
        root = null;
    }

    // 增
    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node ,K key,V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }

        if(key.compareTo((K) node.key) < 0){
            node.left = add(node.left,key,value);
        }
        else if(key.compareTo((K) node.key) > 0){
            node.right = add(node.right,key,value);
        }
        else{
            // 更新value值
            node.value = value;
        }

        return node;
    }

    // 删
    @Override
    public V remove(K key) {
        Node node = getNode(key);
        if(node != null) {
            root = remove(root, key);
            return (V)node.value;
        }
        return null;
    }

    private Node remove(Node node ,K key){
        if(node == null){
            return null;
        }

        if(key.compareTo((K)node.key) < 0){
            node.left = remove(node.left,key);
            return node;
        }
        else if(key.compareTo((K)node.key) > 0){
            node.right = remove(node.right,key);
            return node ;
        }
        else{
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
                node.left= null;
                size --;
                return leftNode ;
            }

            // 待删除节点左右子树均不为空
            // 找到比待删节点大的最小节点,即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minium(node.right);
            successor.left = node.left;
            successor.right = removeMin(node.right);

            node.left = node.right = null;
            return successor;
        }
    }

    private Node minium(Node node){
        if(node.left == null){
            return node;
        }

        return minium(node.left);
    }

    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node ;
    }

    private Node maxium(Node node){
        if(node.right == null){
            return node;
        }
        return maxium(node.right);
    }

    private Node removeMax(Node node ){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node ;
    }

    // 改
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if(node == null){
            throw  new IllegalArgumentException(key + "doesn't exist.");
        }
        else{
            node.value = newValue;
        }
    }

    // 查
    @Override
    public Node getNode(K key) {
        return getNode(root,key);
    }

    private Node getNode(Node node ,K key){
        // 没有找到Key
        if(node == null){
            return null ;
        }

        if(key.compareTo((K)node.key) == 0){
            return node;
        }
        else if(key.compareTo((K)node.key) < 0){
            return getNode(node.left , key);
        }
        else { // if(key.compareTo((K)node.key) > 0){
            return getNode(node.right ,key);
        }

    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : (V)node.value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
