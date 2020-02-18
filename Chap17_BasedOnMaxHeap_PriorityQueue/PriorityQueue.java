package tmp;

public class PriorityQueue<Element extends Comparable<Element>> implements Queue<Element> {

    private MaxHeap<Element> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {

        return maxHeap.getSize() ;
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(Element e) {
        maxHeap.add(e);
    }

    @Override
    public Element dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public Element getFront() {
        return maxHeap.findMax();
    }
}
