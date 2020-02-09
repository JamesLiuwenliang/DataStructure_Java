/**
 * 定义接口
 * @param <Element>
 */
public interface Queue<Element>{

    void enQueue(Element e);
    Element deQueue();
    Element getFront();
    int getSize();
    boolean isEmpty();



}