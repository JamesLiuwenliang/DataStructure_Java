package tmp;
/**
 *  Map<K,V> : 对应两个数据类型
 */
public interface Map<K,V> {


    void add(K key ,V value);
    V remove(K key);
    boolean contains(K key);
    Node getNode(K key);
    V get(K key);
    void set(K key,V newValue);
    int getSize();
    boolean isEmpty();

}
