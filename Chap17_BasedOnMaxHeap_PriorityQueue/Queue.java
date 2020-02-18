package tmp;


public interface Queue<Element> {

    int getSize();
    boolean isEmpty();
    void enqueue(Element e);
    Element dequeue();
    Element getFront();
}
