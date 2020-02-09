
public class ArrayStack<Element> implements Stack<Element>{
    Array<Element> array;

    /**
     * 构造函数
     * @param capacity
     */
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        array = new Array<>();
    }


    @Override
    public int getSize(){
        return array.getSize();

    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void push(Element e){
        array.addLast(e);
    }

    @Override
    public Element pop(){
        return array.delete(array.getSize()-1);
    }

    /**
     * 看栈顶的元素
     */
    @Override
    public Element peek(){
        return array.get(array.getSize()-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append('[');
        for(int i=0;i< array.getSize();i++){
            res.append(array.get(i));
            if(i != array.getSize()-1){
                res.append(", ");
            }
        }
        res.append("] top");

        return res.toString();
    }



}




