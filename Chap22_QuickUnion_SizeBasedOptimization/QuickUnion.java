package tmp;

/**
 *  并查集 : 基于树实现 , 子节点指向父节点,根节点指向自己,Union操作快
 *   Union : O(logn)
 *   Find  : O(logn)
 *
 */
public class QuickUnion implements UF{

    private int[] parent;
    public QuickUnion(int size){
        parent = new int[size];

        // 每个节点都独立的是一棵树
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 查找过程,查找元素p所对应的的集合编号
    // O(H)复杂度,h为树的高度
    private int find(int p){

        if(p<0 && p>= parent.length){
            throw new IllegalArgumentException("Index is Error.");
        }
        // 因为每个id的parent[id]都是本id的父节点
        // 所以此次循环相当于从子节点开始向树上爬,直到发现parent[id]等于id(即为根节点,根节点是指向自己的节点)
        while(p!=parent[p]){
            p = parent[p];
        }

        return p;
    }

    // 查看元素p和元素q是否所属一个集合
    // O(H)复杂度,h为树的高度
    @Override
    public boolean isConnected(int p ,int q){
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(H)复杂度,H为树的高度
    @Override
    public void unionElements(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot){
            return ;
        }

        parent[pRoot] = qRoot;
    }


}
