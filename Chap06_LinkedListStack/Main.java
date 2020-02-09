/**
 * 链表实现栈: 增删查: O(n)
 */
public class Main{

    public static void main(String[] args){
        LinkedListStack<Integer> myLinkStack = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            myLinkStack.push(i);
            System.out.println(myLinkStack);
        }

        myLinkStack.push(666);
        System.out.println(myLinkStack);

        myLinkStack.pop();
        System.out.println(myLinkStack);





        System.out.println("Hello World...");
    }


}