import java.util.ArrayList;

public class Streamer extends Human{
    private Integer id;
    private String name;
    private Integer streamerType;

    private ArrayList<Integer> streams = new ArrayList<>();

    public Streamer(Integer id, String name, Integer streamerType) {
        super(id, name);
        this.streamerType = streamerType;
    }

    public ArrayList<Integer> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<Integer> streams) {
        this.streams = streams;
    }

}
