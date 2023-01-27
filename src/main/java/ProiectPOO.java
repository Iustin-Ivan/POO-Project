import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProiectPOO {
    private static ProiectPOO instance;
    private List<Streamer> streamers = new ArrayList<>();

    private List<Stream> streams = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private List<String> commands = new ArrayList<>();

    public List<Streamer> getStreamers() {
        return streamers;
    }

    public void setStreamers(List<Streamer> streamers) {
        this.streamers = streamers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    private ProiectPOO() {
    }

    public static ProiectPOO getInstance() {
        if(instance == null) {
            instance = new ProiectPOO();
        }
        return instance;
    }

    public void executeCommands() {
        for (String command : commands) {
            String[] commandSplit = command.split(" ");
            if (commandSplit[1].equals("LIST")) {
                Integer id = Integer.parseInt(commandSplit[0]);
                    Human human = getStreamerById(id);
                    if (human == null) {
                        human = getUserById(id);
                    }
                    ArrayList<Integer> streams = human.getStreams();
                    int nr = 0;
                    String print = "";
                    for (Integer streamId : streams) {
                        Stream stream = getStreamById(streamId);
                        print = "["+stream.toString();
                        if(nr < streams.size() - 1) {
                            print += ",";
                        } else {
                            print += "]";
                        }
                        nr ++;
                        }
                System.out.println(print);
                    }
                }
            }

    public Streamer getStreamerById(Integer id) {
        for (Streamer streamer : this.getStreamers()) {
            if (streamer.getId().equals(id)) {
                return streamer;
            }
        }
        return null;
    }

    public User getUserById(Integer id) {
        for (User user : this.getUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public Stream getStreamById(Integer id) {
        for (Stream stream : this.getStreams()) {
            if (stream.getId().equals(id)) {
                return stream;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Nothing to read here");
        } else {
            ProiectPOO project = getInstance();
            StaticStuff.createLists(args);
            project.executeCommands();
        }
    }
}
