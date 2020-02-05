/**
 *  复用Array
 */
public class ArrayQueue<Element> implements Queue<Element>{
    Array<Element> array;


    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }


    public ArrayQueue() {
        array = new Array<>();

    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public Element getFront(){
        return array.get(0);
    }


    @Override
    public Element deQueue(){
        return array.delete(0);
    }

    @Override
    public void enQueue(Element e) {
        array.addLast(e);
    }

    /**
     * 重写输出队列的方法
     */
    @Override
    public String  toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue:");
        res.append('[');
        for(int i=0;i< array.getSize();i++){
            res.append(array.get(i));
            if(i != array.getSize()-1){
                res.append(", ");
            }
        }
        res.append("] Last");

        return res.toString();

    }

} 