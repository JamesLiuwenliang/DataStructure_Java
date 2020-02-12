
public class LinkedListSET<E> implements Set<E> {
    private LinkedList<E> list ;
    public LinkedListSET(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public boolean contains(E e){
        return list.find(e);
    }
//    void add(E e);
//    void remove(E e);
    @Override
    public void remove(E e){
        list.removeElement(e);
    }

    @Override
    public void add(E e){
        if(list.find(e)){
            return ;
        }
        else{
            list.addFirst(e);
        }

    }


}
