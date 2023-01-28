import java.util.ArrayList;
import java.util.HashSet;

public interface Strategy {
    public ArrayList<Stream> recommendedStreams(HashSet<Stream> streams);
}
