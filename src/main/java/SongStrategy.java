import java.util.ArrayList;
import java.util.HashSet;

public class SongStrategy implements Strategy{
    @Override
    public ArrayList<Stream> recommendedStreams(HashSet<Stream> streams) {
        ArrayList<Stream> recommendedStreams = new ArrayList<>();
        for (Stream stream : streams) {
            if (stream.getStreamType()==1) {
                recommendedStreams.add(stream);
            }
        }
        return recommendedStreams;
    }
}
