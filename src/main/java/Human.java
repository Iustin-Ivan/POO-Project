import java.util.ArrayList;
import java.util.List;

public abstract class Human {
    protected Integer id;
    protected String name;

    protected ArrayList<Integer> streams = new ArrayList<>();

    public Human(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<Integer> streams) {
        this.streams = streams;
    }
}
