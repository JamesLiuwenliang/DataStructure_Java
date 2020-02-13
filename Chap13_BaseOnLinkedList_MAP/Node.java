package tmp;
public class Node<K,V>  {
    public K key;
    public V value;
    public Node next;

    public Node(K key,V value ,Node next) {
        this.key = key;
        this.value = value;
        this.next = next;

    }

    public Node(K key) {
        this(key , null , null);
    }

    public Node() {
        this(null ,null, null);
    }

    @Override
    public String toString() {
        return key.toString() + " : " + value.toString() ;
    }

}