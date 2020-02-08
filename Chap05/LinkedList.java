public class LinkedList<E> {

    private Node head;
    private int size ;

    public LinkedList(){
        head = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst (E e){
        Node node = new Node<>(e);
        node.next = head;
        head = node;
        //head = new Node(e,head);

        size++;

    }

    public void insert(E e ,int pos){
        if(pos == 0){
            addFirst(e);
            return ;
        }
        Node node = new Node<>(e);
        Node current = head;
        for(int i=0 ;i<pos ;i++){
            current = current.next;
        }

        node.next = current.next;
        current = node;
        size ++;

    }
//
//    public Node delete (int pos){
//        Node delNode = null;
//        Node current = head;
//        for(int i=0 ;i<pos ;i++){
//            current = current.next;
//        }
//        delNode = current.next;
//        current = current.next.next;
//        size--;
//        return delNode;
//
//    }
//
//    public void change(Node e ,int pos){
//        Node current = head;
//        for (int i = 0; i < pos; i++) {
//            current = current.next;
//        }
//        current.next = e;
//        e.next = current.next;
//
//    }
//
//    public int find(Node e ){
//        int pos = -1 ;
//        Node current = head;
//        for (int i = 0; i<size ; i++) {
//
//            if(current == e){
//                pos = i;
//            }
//            current = current.next;
//        }
//        return pos;
//    }
}
