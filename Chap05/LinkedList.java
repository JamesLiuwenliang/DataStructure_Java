/**
 * dummyHead : 虚拟头节点,不占size
 * 
 * @param <E>
 */
public class LinkedList<E> {

    private Node dummyHead;
    private int size ;

    public LinkedList(){
        Node node = new Node (null,null);
        dummyHead = node;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E e){
        insert(e,0);
    }

    public void addLast(E e){
        insert(e,size);
    }

    //pre 是插入节点的前一个节点
    public void insert(E e ,int pos){

        if(pos < 0 || pos >size){
            throw new IllegalArgumentException("Add failed. Illegal position.");
        }

        Node pre = dummyHead;
        for(int i=0 ;i<pos ;i++){
            pre = pre.next;
        }

        Node node = new Node(e);
        node.next = pre.next;
        pre.next = node;
        //pre.next = new Node(e, current.next);

        size ++;

    }


    public Node delete (int pos){

        Node delNode = null;
        Node pre = dummyHead;
        for(int i=0 ;i<pos ;i++){
            pre = pre.next;
        }
        delNode = pre.next;
        pre = pre.next.next;
        size--;
        return delNode;

    }

    public void change(E e ,int pos){
        Node current = dummyHead.next;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        current.e = e;
    }

    public boolean find(E e ){

        Node current = dummyHead.next;;
        for (int i = 0; i<size ; i++) {

            if(current.e.equals(e)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E get(int pos){
        if(pos < 0 || pos >size){
            throw new IllegalArgumentException("Add failed. Illegal position.");
        }
        Node current = dummyHead.next;
        for(int i=0 ; i<pos ;i++){
            current = current.next;
        }
        return (E) current.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node current = dummyHead.next;
        while(cur. != null){

        }

    }

}
