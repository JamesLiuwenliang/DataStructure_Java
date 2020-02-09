public class LinkedListQueue<E> implements Queue<E> {

    private Node head,tail;
    private int size;


    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void enQueue(E e){
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }
        else{
            tail.next = new Node(e);
            tail = tail.next;
        }

        size++;

    }

    @Override
    public E deQueue(){
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        Node newNode = new Node();
        newNode = head;
        head = head.next;
        newNode.next = null;

        // 如果链表只有一个节点
        if(head == null){
            tail = null;
        }

        size--;
        return (E)newNode.e;

    }

    @Override
    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        return (E)head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue : front ");

        Node cur =head;
        while (cur !=  null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append(" NULL tail");
        return res.toString();
    }


}
