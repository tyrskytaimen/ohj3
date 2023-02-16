import java.util.ArrayList;
import java.util.Iterator;

public class ArrayNode extends Node implements Iterable<Node> {
    private ArrayList<Node> list;

    public ArrayNode() {
        this.list = new ArrayList<>();
    }
    public void add(Node node) {
        this.list.add(node);
    }

    public int size() {
        return list.size();
    }

    @Override
    public Iterator<Node> iterator() {
        return list.iterator();
    }
}
