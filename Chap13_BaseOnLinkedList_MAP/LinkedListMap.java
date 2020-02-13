package tmp;
import tmp.LinkedListMap;
import tmp.Node;
public class LinkedListMap<K,V> implements Map<K,V> {
    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    // 增
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            dummyHead.next  = new Node(key,value,dummyHead.next);
            size ++;
        }
        else {
            // 如果传来的键是重复的,直接抛出异常或者选择更新value的值
            node.value = value;
        }
    }

    // 删
    @Override
    public V remove(K key) {
        Node node = getNode(key);
        Node cur = dummyHead.next;
        Node pre = dummyHead;
        if(node == null){
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        else{
            while(cur.key != node.key){
                cur = cur.next;
                pre = pre.next;
            }

            pre.next = node.next;
            node.next = null;
            size --;

        }

//            Node prev = dummyHead;
//            while(prev.next != null){
//                if(prev.next.key != null){
//                    break;
//                }
//                prev = prev.next;
//            }
//
//            if(prev.next != null){
//                Node delNode = prev.next;
//                prev.next = delNode.next;
//                delNode.next = null;
//                size--;
//                return delNode.value;
//            }
//            return null;


        return (V)node.value;
    }

    // 改
    @Override
    public void set(K key, V newValue) {

        Node node = getNode(key);

        if(node == null){
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        else{
            node.value = newValue;
        }
    }

    // 查
    @Override
    public V get(K key){
        Node node = getNode(key);
        return node == null ? null : (V)node.value;

    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public Node getNode(K key) {
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        
        return null;
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
