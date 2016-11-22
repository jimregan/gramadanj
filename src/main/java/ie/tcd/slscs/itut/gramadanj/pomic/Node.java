
public class Node<T> {
    private List<Node<T>> children;
    private Node<T> parent = null;
    private String label;
    private T content = null;

    private Node<T>() {
        children = new ArrayList<Node<T>>();
    }
    public Node(String label, T content) {
        this();
        this.label = label;
        this.content = content;
    }
    public String getLabel() {
        return this.label;
    }
    public T getData() {
        return this.content;
    }
    public boolean isRootNode() {
        return (this.parent == null);
    }
    public boolean isTerminal() {
        return (this.children.size() != 0);
    }
}
