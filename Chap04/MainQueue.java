
class MainQueue{


    public static void main(String[] args){
        ArrayQueue<Integer> queue = new ArrayQueue<>();

         for(int i=0 ;i <5; i++){
             queue.enQueue(i);
             System.out.println(queue);
         }

         queue.deQueue();
         System.out.println(queue);

         queue.deQueue();
         System.out.println(queue);

         queue.enQueue(1000);
         System.out.println(queue);

        System.out.println("Hello World");
    }


}