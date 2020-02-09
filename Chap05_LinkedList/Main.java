/**
 * 链表: 增删查: O(n)
 */
public class Main{

    public static void main(String[] args){
        LinkedList<Integer> myLink= new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            myLink.addFirst(i);
            System.out.println(myLink);
        }

        myLink.insert(666,2);
        System.out.println(myLink);

        Node<Integer> delNode = new Node<>();
        delNode.e = myLink.delete(2);
        System.out.println(myLink);
        System.out.println(delNode.e);

        myLink.deleteFirst();
        System.out.println(myLink);

        myLink.deleteLast();
        System.out.println(myLink);

        System.out.println("Hello World...");
    }


}