package tmp;

import java.util.TreeMap;
public class HashTable<K,V>{


    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    private TreeMap<K,V>[] hashtable;
    private int M;
    private int size ;

    public HashTable(int M){
        this.M = M;
        size =0 ;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable(){
        this(97);
    }

    // 将key转换成哈希索引
    private int hash(K key){
        // 消除符号位影响
        return (key.hashCode() & 0x7fffffff ) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key , V value){
        TreeMap<K,V> map = hashtable[hash(key)];

        // 判断是否包含了key,如果包含就修改该key的value
        if(map.containsKey(key) ) {
            map.put(key,value);
        }
        else {
            // 添加数据
            map.put(key,value);
            size ++;

            if (size >=  upperTol *M){
                resize(M*2);
            }
        }
    }

    public V remove(K key){
        TreeMap<K,V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size --;

            if(size <= lowerTol *M && M/2 > initCapacity ){
                resize(M/2);
            }
        }
        return ret ;
    }

    public void set(K key, V value){
        TreeMap<K,V> map = hashtable[hash(key)];
        if(map.containsKey(key)){
            map.put(key,value);
        }
        else{
            throw new IllegalArgumentException("Key is not contained.");
        }
    }

    public boolean contains(K key){
        TreeMap<K,V> map = hashtable[hash(key)];
        return map.containsKey(key);
    }

    public V get (K key){
        return hashtable[hash(key)].get(key);
    }

    private void resize (int newM){
        TreeMap<K,V>[] newhashtable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newhashtable[i] = new TreeMap<>();
        }

        // 注意保存M,以及新计算Hash值时要用新的M值
        int oldM = this.M;
        this.M = newM;

        // 将原来的HashTable 放入新的HashTable
        for (int i = 0; i < oldM ; i++) {
            TreeMap<K,V> map = hashtable[i];
            for(K key : map.keySet()){
                newhashtable[hash(key)].put(key,map.get(key));
            }
        }

        this.hashtable = newhashtable;
    }








}
