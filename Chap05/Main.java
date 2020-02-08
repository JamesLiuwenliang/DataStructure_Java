public class Main{

    public static void main(String[] args){
        LinkedList<Integer> myLink= new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            myLink.addFirst(i);
        }
        int length = myLink.getSize();
        for (int i = 0; i < length; i++) {
            System.out.println();
        }

        System.out.println("Hello World...");
    }


}