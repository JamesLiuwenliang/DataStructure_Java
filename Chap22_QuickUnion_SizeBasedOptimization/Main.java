


import java.util.*;

/**
 * 并查集 : 并(将两个数据合并起来) & 查(确定是否连接)
 * union(p,q) & isConnected(p,q)
 * 基于size的优化 : 让小的树指向节点多的树(即需要遍历高度更矮的树)
 *
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
        int size = 100000;
        int m = 100000;

        UnionFind1 uf1 = new UnionFind1(size);
        System.out.println("UnionFind1 : "+testUF(uf1,m) + "s");

        // 因为需要合并的操作过多,有可能比uf1要慢
        QuickUnion qu1 = new QuickUnion(size);
        System.out.println("QuickUnion : "+testUF(qu1,m) + "s");

        // 将合并操作基于两个集合的size做了一定的优化后,效率大幅度提升
        QuickUnion_Size qu_s = new QuickUnion_Size(size);
        System.out.println("QuickUnion_Size : "+testUF(qu_s,m) + "s");

        System.out.println("Hello World.");
    }
}
