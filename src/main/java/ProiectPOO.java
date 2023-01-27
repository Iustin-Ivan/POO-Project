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
            Integer id = Integer.parseInt(commandSplit[0]);
            if (commandSplit[1].equals("LIST")) {
                this.listStreams(id);
            } else if (commandSplit[1].equals("ADD")) {
                this.addStreamToStreamer(id, commandSplit);
            }
        }
    }

    public void listStreams(Integer id) {
        Human human = getStreamerById(id);
        if (human == null) {
            human = getUserById(id);
        }
        ArrayList<Integer> streams = human.getStreams();
        String print = "";
        for (int i = 0; i < streams.size(); i++) {
            Stream stream = getStreamById(streams.get(i));
            print += "[";
            print += stream.toString();
            if (i < streams.size() - 1) {
                print += ",";
            } else {
                print += "]";
            }
        }
        System.out.println(print);
        try {
            FileWriter fw = new FileWriter("output.txt", true);
            fw.write(print+"\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStreamToStreamer(Integer id, String[] commandSplit) {
        Streamer streamer = getStreamerById(id);
        Integer streamType = Integer.parseInt(commandSplit[2]);
        Integer streamId = Integer.parseInt(commandSplit[3]);
        Integer streamGenre = Integer.parseInt(commandSplit[4]);
        Long streamDuration = Long.parseLong(commandSplit[5]);
        String name = commandSplit[6];
        for (int i = 7; i < commandSplit.length; i++) {
            name += " " + commandSplit[i];
        }
        streamer.getStreams().add(streamId);
        Stream stream = new Stream.Builder(streamType, streamId, streamGenre, streamDuration, name, id).build();
        ProiectPOO.getInstance().getStreams().add(stream);
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
