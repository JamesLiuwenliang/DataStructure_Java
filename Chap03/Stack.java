/**
 * 定义接口
 * @param <Element>
 */
public interface Stack<Element>{

    void push(Element e);
    Element pop();
    Element peek();
    int getSize();
    boolean isEmpty();



}