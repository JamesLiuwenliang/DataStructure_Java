package tmp;

import java.util.TreeMap;
public class HashTable<K,V>{

    // 原有的M直接扩容至2倍,必不满足素数条件,改进的可以用查表法记录素数
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    // 素数表的索引
    private int capacityIndex = 0;

    private TreeMap<K,V>[] hashtable;
    private int M;
    private int size ;

    public HashTable(){
        this.M = capacity[capacityIndex];
        size =0 ;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
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

            if (size >=  upperTol *M && capacityIndex +1 < capacity.length){
                capacityIndex ++;
                resize(capacity[capacityIndex]);
            }

        }
    }

    public V remove(K key){
        TreeMap<K,V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size --;

            if(size <= lowerTol*M &&  capacityIndex -1 > 0  ){
                capacityIndex--;
                resize(capacity[capacityIndex]);
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
