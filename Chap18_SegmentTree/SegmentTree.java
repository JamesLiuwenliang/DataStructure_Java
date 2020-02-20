

public class SegmentTree<E> {



    // 线段树基于数组实现
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    private static int x = 0;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        // 利用数组去实现线段树是需要开4倍的空间的
        tree =(E[])new Object[arr.length * 4];

        // 创建线段树的时候需要传入三个参数 : 要创建的线段树的根节点的索引;
        //                                  对于treeIndex所代表的线段的左右端点的索引
        buildSegmentTree( 0 , 0 , data.length-1);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >=data.length ){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    private int leftChild(int index){
        return  index *2 + 1;
    }

    private int rightChild(int index){
        return index*2+2;
    }

    // 在treeIndex的位置创建表示区间[l ... r]的线段树
    private void buildSegmentTree(int treeIndex , int l ,int r){
        if( l == r){
            // 该线段左右 l=r时 ,意味着这个线段只有一个值,所以这个线段和是自己本身
            tree[treeIndex] = data[l]; 
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l +(r-l) /2;
        buildSegmentTree(leftTreeIndex ,l,mid);
        buildSegmentTree(rightTreeIndex , mid+1 ,r);

        // 这个要根据业务需求,可以通过重写merger接口实现
        tree[treeIndex] = merger.merger(tree[leftTreeIndex], tree[rightTreeIndex]);

    }

    /**
     *  返回区间[queryL,queryR]的值
     */
    public E query(int queryL ,int queryR){
        if(queryL<0 || queryL > queryR || queryR > data.length || queryR < 0 || queryL >data.length){
            throw  new IllegalArgumentException("Index is illegal.");
        }

        return query(0 , 0 ,data.length -1 , queryL , queryR);
    }

    // 在treeID为根的线段中[l,r]的范围里, 搜索区间[queryL,queryR]的值
    private E query(int treeIndex ,int l ,int r ,int queryL ,int queryR){
        if( l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int mid = l+(r - l) /2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(queryL >= mid+1 ){
            return query(rightTreeIndex,mid +1 , r ,queryL,queryR);
        }
        else if(queryR <= mid){
            return query(leftTreeIndex , l ,mid ,queryL ,queryR);
        }
        else {
            E leftResult = query(leftTreeIndex , l ,mid ,queryL ,mid);
            E rightResult = query(rightTreeIndex,mid +1 , r ,mid+1 ,queryR);
            // 业务代码
            return merger.merger(leftResult,rightResult);
        }

    }



    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length ; i++) {
            if(tree[i] != null){
                res.append(tree[i]);
            }
            else{
                res.append("NULL");
            }
            if(i != tree.length -1){
                res.append(',');
            }
        }

        return res.toString();
    }



}
