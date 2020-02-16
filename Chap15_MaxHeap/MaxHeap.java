
/**
 *      add() 和 extractMax() 都是 O(log(n))
 */
public class MaxHeap<Element extends Comparable<Element>> {

    // 创建动态数组
    private Array<Element> data;
    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中,一个索引所表示的元素的父亲节点的索引
    private int parent(int index){

        //传来的是根节点,没有父亲节点
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index-1)/2;
    }

    // 返回完全二叉树的数组表示中,一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2+ 1;
    }

    private int rightChild (int index){
        return index * 2+ 2;
    }

    // 增
    // SiftUp(上浮) : 向堆中添加元素,添加在完全二叉树的末尾,
    //                但是需要一个上浮的过程,要沿着父亲节点一路找到小于父亲节点的时候为止
    public void add(Element e){
        data.addLast(e);
        // 私有的上浮函数, 需要传入这个节点的索引
        siftUp(data.getSize() -1);
    }

    private void siftUp(int index){

        /**
         * data.get(parent(index)).compareTo(data.get(index)) < 0 :
         *              当index这个节点是大于0,且这个节点的父节点小于这个节点时进行上浮作用
         */
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0){
            data.swap(index,parent(index));
            index = parent(index);
        }
    }

    // 删
    // SiftDown(下沉) :  向堆中删除元素
    // 步骤: 根节点删除(优先级最高), 完全二叉树最末尾元素移动到根节点(比较节点左右孩子哪个最大,与最大的子节点交换位置)
    public Element findMax(){
        if(data.getSize() == 0 ){
            throw new IllegalArgumentException("Cannot find max when heap is empty.");
        }
        return data.get(0);
    }

    public Element extractMax(){
        Element ret = findMax();
        data.swap(0,data.getSize()-1);
        data.deleteLast();

        siftdown(0);

        return ret;
    }

    private void siftdown(int index){
        int maxChild = 0;
        /**
         * leftChild(index)<data.getSize() : 此时没有子节点
         * 关键在于判断会不会没有右孩子或者右孩子比左孩子要小的情况
         */
        while( leftChild(index)<data.getSize() ){
            int j = leftChild(index);
            if(j+1<data.getSize() &&
                    data.get(j+1).compareTo(data.get(j)) > 0){
                j = rightChild(index);
                // data[j] 是leftChild 和 rightChild 中的最大值
            }

            if(data.get(index).compareTo(data.get(j)) >=  0){
                break;
            }
            else{
                data.swap(index,j);
            }

            index = j;

        }
    }




    // 改
    // 查








}
