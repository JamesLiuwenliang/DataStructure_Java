

/**
 * 基于深度(rank)合并
 * 当根节点下很多子孩子(size大)但是另一个棵树每个节点都只有一个孩子但是很高的时候,
 * 合理的合并方式应该是矮的树(即使size大)向高的树依附
 */
public class QuickUnion_Rank implements  UF {

    private int[] parent;
    private int[] rank;
    public QuickUnion_Rank(int size){
        parent = new int[size];
        rank = new int[size];
        // 每个节点都独立的是一棵树
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] =1 ;
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

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        // rank高度不需要维护,因为矮树成为高树的子树后,合并出的树的高度还是高的树的高度
        // 当两个树高度相等的时候,需要成为根节点的rank+1(因为另一个树成为了他的子树,而多出1个高度)
        if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }
        else if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }
        else { // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] ++;
        }
    }

}
