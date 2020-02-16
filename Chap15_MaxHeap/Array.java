/**
 *  复用Array
 */
public class Array<Element>{
    private Element[] data;
    private int size;

    /**
     * 构造函数
     * @param capacity
     */
    public Array(int capacity){
        data = (Element[])new Object[capacity]; // new一个Object类型数组,然后强制转换成特定类型
        size =0;
    }


    public Array() {
        this(10); //直接传10进入

    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size==0;
    } 

    /**
     * 重写输出数组的方法
     */
    @Override
    public String  toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n",size,data.length));
        res.append('[');
        for(int i=0;i<size;i++){
            res.append(data[i]);
            if(i != size-1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();

    }


    /*
     *  自定义数组内增删改查
     */

     /**
      * 在最末位添加元素
      * @param e
      */
    public void addLast(Element e){
        // if(size == data.length){
        //     throw new IllegalArgumentException("AddLast failed. Array is full.");
        // }
        // data[size] = e;
        // size ++;

        insert(size,e);
    }

    /**
     * 在数组的指定位置上插入元素
     * @param e
     * 时间复杂度 : O(n)
     */
    public void insert(int index ,Element e){
        if(index <0 || index > size){
            throw new IllegalArgumentException("AddLast failed. Required index is incorrect.");
        }
        if(size==data.length ) {
            resize(2 * data.length);
        }


        
        for(int i= size-1 ;i >= index;i--){
            data[i+1] = data[i];
        }
        data[index] = e;


        size++;
    }

    /**
     * 自定义数组中查找某个元素
     * @param index
     * @return
     */
    public Element get (int index){
        if(index <0 || index >=size){
            throw new IllegalArgumentException("Get failed. Required index is incorrect.");
        }
        return data[index];

    }

    /**
     * 自定义数组中更改元素
     * @param index
     * @param e
     * 时间复杂度 : O(1)
     */
    public void set (int index , Element e){
        if(index <0 || index >=size){
            throw new IllegalArgumentException("Get failed. Required index is incorrect.");
        }
        
        data[index] = e;
    }

    /**
     * 查找是否包含元素e
     * @param e
     * @return
     * 时间复杂度 : O(n)
     */
    public boolean contains(Element e){
        for(int i=0;i<size ;i++){
            if(data[i].equals(e)){
                return true;
            }
        }

        return false;
    }

    /**
     * 删除指定元素
     * @param index
     * @return
     * 时间复杂度 : O(n)
     */
    public Element delete(int index){
        if(index <0 || index >=size){
            throw new IllegalArgumentException("Get failed. Required index is incorrect.");
        }
        Element temp = data[index];
        for(int i = index+1 ;i<size ; i++){
            data[i-1] = data[i]; 
        }
        /*
        for(int i = index ;i<size ; i++){
            data[i] = data[i+1];  //在缩小数组容量时失败了  ???
        }
        */
        size--;
        data[size] = null; //这句话不是必要,但因为size一直指向这个空间,所以Java的回收机制不能回收这个空间

        if((size == data.length/4) && (data.length/2 != 0)){
            resize(data.length/2);
        }

        return temp;
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public Element deleteLast(){
        return delete(size - 1);
    }

    /**
     * 扩容,一次2倍;也可以缩小容量,一次缩小一半
     * @param newCapacity
     * 时间复杂度 : O(n)
     * 均摊复杂度: 如果resize()被放在了addLast()里面,只有当每次容量恰好满的时候才会触发resize(),
     * 所以实际上计算单次均摊,是O[(n-1+n)/(n+1)],也就是O(1),所以addLast()依然算是O(1)的操作.
     */
    private void resize (int newCapacity){
        Element[] newData = (Element[])new Object[newCapacity];
        for(int i=0;i <size ;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     *  实现完全二叉树交换函数
     */
    public void swap(int i,int j){
        if(i<0 || i>= size || j<0 || j>size){
            throw new IllegalArgumentException("Index is illegal.");
        }
        Element tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }





} 