import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class ObjectNode extends Node implements Iterable<String>{
    HashMap<String, Node> map;
    TreeSet<String> set = new TreeSet<>();
    public ObjectNode() {
        this.map = new HashMap<>();
    }

    public Node get(String key) {
        return map.get(key);
    }

    public void set(String key, Node node) {
        this.map.put(key, node);
        this.set.add(key);
    }

    public int size() {
        return map.size();
    }

    @Override
    public Iterator<String> iterator() {
        return set.iterator();
    }
}
