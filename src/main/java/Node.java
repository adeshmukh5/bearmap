import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by anushkadeshmukh on 4/22/16.
 */
public class Node {
    double lat, lon;
    long id;
    HashSet<Node> connections = new HashSet<>();
    HashMap<Long, Node> connectionSet = new HashMap<>();

    public Node(long id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }
    public void connect(Node other) {
        connections.add(other);
        connectionSet.put(other.id, other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Node node = (Node) o;

        if (Double.compare(node.lat, lat) != 0) {
            return false;
        }
        if (Double.compare(node.lon, lon) != 0) {
            return false;
        }
        if (id != node.id) {
            return false;
        }
        return connectionSet != null
                ? connectionSet.equals(node.connectionSet) : node.connectionSet == null;

    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
