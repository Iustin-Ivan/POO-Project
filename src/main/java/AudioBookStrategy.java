import java.util.ArrayList;
import java.util.HashSet;

public class AudioBookStrategy implements Strategy{
    @Override
    public ArrayList<Stream> recommendedStreams(HashSet<Stream> streams) {
        ArrayList<Stream> recommendedStreams = new ArrayList<>();
        for (Stream stream : streams) {
            if (stream.getStreamType()==3) {
                recommendedStreams.add(stream);
            }
        }
        return recommendedStreams;
    }
}
