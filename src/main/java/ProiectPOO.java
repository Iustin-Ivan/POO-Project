import java.util.*;

import static java.lang.Math.min;

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
            } else if (commandSplit[1].equals("DELETE")) {
                this.deleteStream(id, commandSplit);
            } else if (commandSplit[1].equals("LISTEN")) {
                this.listenStream(id, commandSplit);
            } else if (commandSplit[1].equals("RECOMMEND")) {
                String typeOfStream = commandSplit[2];
                this.recommendStream(id, typeOfStream);
            } else if (commandSplit[1].equals("SURPRISE")) {
                String typeOfStream = commandSplit[2];
                this.surpriseStream(id, typeOfStream);
            }
        }
    }

    public void listStreams(Integer id) {
        Human human = getStreamerById(id);
        if (human == null) {
            human = getUserById(id);
        }
        ArrayList<Integer> streams = human.getStreams();
        StringBuilder print = new StringBuilder();
        print.append("[");
        for (int i = 0; i < streams.size(); i++) {
            Stream stream = getStreamById(streams.get(i));
            print.append(stream.toString());
            if (i < streams.size() - 1) {
                print.append(",");
            } else {
                print.append("]");
            }
        }
        System.out.println(print);
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

    public void deleteStream(Integer id, String[] commandSplit) {
        Streamer streamer = getStreamerById(id);
        Integer streamId = Integer.parseInt(commandSplit[2]);
        streamer.getStreams().remove(streamId);
        Stream stream = getStreamById(streamId);
        ProiectPOO.getInstance().getStreams().remove(stream);
    }

    public void listenStream(Integer id, String[] commandSplit) {
        User user = getUserById(id);
        Integer streamId = Integer.parseInt(commandSplit[2]);
        Stream stream = getStreamById(streamId);
        user.getStreams().add(streamId);
        stream.setNoOfStreams(stream.getNoOfStreams() + 1);
    }

    public void recommendStream(Integer id, String typeOfStream) {
        User user = getUserById(id);
        ArrayList<Integer> streams = user.getStreams();
        HashSet<Streamer> streamersListened = new HashSet<>();
        for (Integer streamId : streams) {
            Stream stream = getStreamById(streamId);
            Streamer streamer = getStreamerById(stream.getStreamerId());
            streamersListened.add(streamer);
        }
        HashSet<Stream> streamsNotListened = new HashSet<>();
        for (Streamer streamer : streamersListened) {
            ArrayList<Integer> streamerStreams = streamer.getStreams();
            for (Integer streamId : streamerStreams) {
                Stream stream = getStreamById(streamId);
                if (!streams.contains(streamId)) {
                    streamsNotListened.add(stream);
                }
            }
        }
        Context context = new Context();
        if (typeOfStream.equals("SONG")) {
            context.setStrategy(new SongStrategy());
        } else if (typeOfStream.equals("PODCAST")) {
            context.setStrategy(new PodcastStrategy());
        } else if (typeOfStream.equals("AUDIOBOOK")) {
            context.setStrategy(new AudioBookStrategy());
        }
        ArrayList<Stream> finalStreams = context.executeStrategy(streamsNotListened);
        DynamicComparatorStreamuri d = new DynamicComparatorStreamuri();
        d.setCompareBy(0);
        Collections.sort(finalStreams, d);
        StringBuilder print = new StringBuilder();
        print.append("[");
        int nr = min(5, finalStreams.size());
        for (int i = 0; i < nr; i++) {
            print.append(finalStreams.get(i).toString());
            if (i < nr - 1) {
                print.append(",");
            } else {
                print.append("]");
            }
        }
        System.out.println(print);
    }

    public void surpriseStream(Integer id, String typeOfStream) {
        User user = getUserById(id);
        ArrayList<Integer> streams = user.getStreams();
        HashSet<Streamer> streamersListened = new HashSet<>();
        for (Integer streamId : streams) {
            Stream stream = getStreamById(streamId);
            Streamer streamer = getStreamerById(stream.getStreamerId());
            streamersListened.add(streamer);
        }
        HashSet<Stream> streamsNotListened = new HashSet<>();
        for (Streamer streamer : this.getStreamers()) {
            if (!streamersListened.contains(streamer)) {
                ArrayList<Integer> streamerStreams = streamer.getStreams();
                for (Integer streamId : streamerStreams) {
                    Stream stream = getStreamById(streamId);
                    streamsNotListened.add(stream);
                }
            }
        }
        Context context = new Context();
        if (typeOfStream.equals("SONG")) {
            context.setStrategy(new SongStrategy());
        } else if (typeOfStream.equals("PODCAST")) {
            context.setStrategy(new PodcastStrategy());
        } else if (typeOfStream.equals("AUDIOBOOK")) {
            context.setStrategy(new AudioBookStrategy());
        }
        ArrayList<Stream> finalStreams = context.executeStrategy(streamsNotListened);
        DynamicComparatorStreamuri d = new DynamicComparatorStreamuri();
        d.setCompareBy(1);
        Collections.sort(finalStreams, d);
        StringBuilder print = new StringBuilder();
        print.append("[");
        int nr = min(3, finalStreams.size());
        for (int i = 0; i < nr; i++) {
            print.append(finalStreams.get(i).toString());
            if (i < nr - 1) {
                print.append(",");
            } else {
                print.append("]");
            }
        }
        System.out.println(print);
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
            ProiectPOO.instance = new ProiectPOO();
            ProiectPOO project = ProiectPOO.getInstance();
            StaticStuff.createLists(args);
            project.executeCommands();
        }
    }
}
