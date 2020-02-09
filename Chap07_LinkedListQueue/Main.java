/**
 * 链表实现栈: 增删查: O(n)
 */
public class Main{

    public static void main(String[] args){
        LinkedListQueue<Integer> myLinkedQueue = new LinkedListQueue<>();
        for (int i = 0; i < 5; i++) {
            myLinkedQueue.enQueue(i);
            System.out.println(myLinkedQueue);
        }

        myLinkedQueue.deQueue();
        System.out.println(myLinkedQueue);

        System.out.println("Hello World...");
    }


}