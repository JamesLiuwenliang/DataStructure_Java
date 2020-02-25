

import java.util.*;

/**
 * 并查集 : 并(将两个数据合并起来) & 查(确定是否连接)
 * union(p,q) & isConnected(p,q)
 * 基于size的优化 : 让小的树指向节点多的树(即需要遍历高度更矮的树)
 * 基于Rank的优化 : 让矮的树依附于高德树
 *
 * 路径压缩: 高树变矮  --> parent[p] = parent[parent[p]]
 * 路径压缩(递归实现)  --> parent[p] = find(parent[p]);
 *
 * 并查集时间复杂度 : O(log*n) -> iterated logarithm
 *
 * log*n ={ 0               if(n<=1)
 *          1+log*(logn)    if(n>1) }
 *  近乎可以认为是O(1)
 *
 */
public class Main {

    private static double testUF(UF uf,int m){
        int size = uf.getSize();
        Random random =new Random();
        long startTime = System.nanoTime();

        // 执行m次合并操作
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }

        // 执行m次查询操作
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.00;
    }

    public static void main(String[] args){
        int size = 10000000;
        int m = 10000000;

        // 基于size的优化和基于rank的优化效率提升差不多,但是基于rank的优化要更加合理,所以一般选择基于rank的优化
        // 将合并操作基于两个集合的size做了一定的优化后,效率大幅度提升
        QuickUnion_Size qu_s = new QuickUnion_Size(size);
        System.out.println("QuickUnion_Size : "+testUF(qu_s,m) + "s");

        // 将合并操作基于两个集合的rank做了一定的优化
        QuickUnion_Rank qu_r = new QuickUnion_Rank(size);
        System.out.println("QuickUnion_Rank : "+testUF(qu_r,m) + "s");

        // 路径压缩
        QuickUnion_PathCompression qu_pc = new QuickUnion_PathCompression(size);
        System.out.println("QuickUnion_PathCompression :" + testUF(qu_pc,m)+"s");

        QuickUnion_PathCompression_02 qu_pc2 = new QuickUnion_PathCompression_02(size);
        System.out.println("QuickUnion_PathCompression_02 : "+testUF(qu_pc2,m)+"s");

        System.out.println("Hello World.");
    }
}
