/**
 * 循环队列
 */
public class LoopQueue<E> implements Queue<E>{
    private E[] data;
    private int front ;
    private int tail ;
    private int size;


    public LoopQueue(int capacity){
        // new的空间大小是传进来的容积加一,有意识的浪费一个空间
        data = (E[])new Object[capacity +1];
        front =0;
        tail  =0;
        size  =0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length -1;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }

        return data[front];

    }

    /**
     * 入队函数
     * @param e
     */
    @Override
    public void enQueue(E e){
        /**
         * 查看队列是否是满的,否则扩容
         */
        if( (tail+1)%data.length == front){
            resize(getCapacity() * 2);
        }

        //tail 的位置是空的
        data[tail] = e;
        tail = (tail + 1)% data.length;
        size ++ ;

    }

    /**
     *
     * @return
     */
    @Override
    public E deQueue(){
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E e = data[front];
        data[front] = null;
        front = (front+1)%data.length;

        size--;
        if(size == getCapacity()/4 && getCapacity() /2 != 0){
            resize(getCapacity() /2);
        }
        return e;
    }

    /**
     * 扩容 | 缩容
     */
    private void resize(int newCapacity){
        // 要记得富裕出一个位置
        E[] newData = (E[])new Object[newCapacity + 1];
        for (int i=0; i < size ; i++){
            // 因为是循环队列
            newData[i] = data[(i+ front) % data.length];
        }

        data = newData;
        front =0;
        tail = size;
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d \t",size ,getCapacity()));
        res.append("Front[");
        //这种遍历方式与resize函数的遍历其实相同
        for(int i=front ;i != tail ; i = (i+1)% data.length){
            res.append(data[i]);

            if((i+1)%data.length != tail){
                res.append(", ");
            }
        }
        res.append("] back");
        return res.toString();

    }


    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>(5);
        for(int i = 0 ; i < 10 ; i ++){
            queue.enQueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.deQueue();
                System.out.println(queue);
            }
        }
    }








}
