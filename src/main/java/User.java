
import java.util.ArrayList;

public class User extends Human{
    private Integer id;
    private String name;

    private ArrayList<Integer> streams = new ArrayList<>();
    public User(Integer id, String name, ArrayList<Integer> streams) {
        super(id, name);
        this.streams = streams;
    }

    public ArrayList<Integer> getStreams() {
        return (ArrayList<Integer>) streams;
    }

    public void setStreams(ArrayList<Integer> streams) {
        this.streams = streams;
    }

}
