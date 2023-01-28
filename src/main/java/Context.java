import java.util.ArrayList;
import java.util.HashSet;

public class Context {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public ArrayList<Stream> executeStrategy(HashSet<Stream> streams) {
        return strategy.recommendedStreams(streams);
    }

}
