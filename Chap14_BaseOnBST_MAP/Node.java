package tmp;
public class Node<K,V>  {
    public K key;
    public V value;
    public Node left,right;

    public Node(K key,V value ) {
        this.key = key;
        this.value = value;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return key.toString() + " : " + value.toString() ;
    }

}