import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StaticStuff {
    public static void createLists(String[] args) {
        File streamersFile = new File("src/main/resources/"+ args[0]);
        File streamsFile = new File("src/main/resources/"+args[1]);
        File usersFile = new File("src/main/resources/"+args[2]);
        File commandsFile = new File("src/main/resources/"+args[3]);
        readStreamersFromFile(streamersFile);
        readStreamsFromFile(streamsFile);
        readUsersFromFile(usersFile);
        readCommandsFromFile(commandsFile);
    }

    public static void readStreamersFromFile(File streamersFile) {
        try {
            Scanner myReader = new Scanner(streamersFile);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplit = data.split(",");
                Integer streamerType = Integer.parseInt(dataSplit[0].substring(1, dataSplit[0].length() - 1));
                Integer id = Integer.parseInt(dataSplit[1].substring(1, dataSplit[1].length() - 1));
                String name = dataSplit[2].substring(1, dataSplit[2].length() - 1);
                HumanFactory humanFactory = new HumanFactory();
                ProiectPOO.getInstance().getStreamers().add(
                        (Streamer) humanFactory.getHuman("Streamer", id, name, streamerType, new ArrayList<>()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readUsersFromFile(File usersFile) {
        try {
            Scanner myReader = new Scanner(usersFile);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplit = data.split(",");
                Integer id = Integer.parseInt(dataSplit[0].substring(1, dataSplit[0].length() - 1));
                String name = dataSplit[1].substring(1, dataSplit[1].length() - 1);
                String streams = dataSplit[2].substring(1, dataSplit[2].length() - 1);
                String[] streamsSplit = streams.split(" ");
                ArrayList<Integer> streamsList = new ArrayList<>();
                for (String stream : streamsSplit) {
                    streamsList.add(Integer.parseInt(stream));
                }
                HumanFactory humanFactory = new HumanFactory();
                ProiectPOO.getInstance().getUsers().add(
                        (User) humanFactory.getHuman("User", id, name, 0, streamsList));

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readStreamsFromFile(File streamsFile) {
        try {
            Scanner myReader = new Scanner(streamsFile);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplit = data.split(",");
                Integer streamType = Integer.parseInt(dataSplit[0].substring(1, dataSplit[0].length() - 1));
                Integer streamId = Integer.parseInt(dataSplit[1].substring(1, dataSplit[1].length() - 1));
                Integer streamGenre = Integer.parseInt(dataSplit[2].substring(1, dataSplit[2].length() - 1));
                Long noOfStreams = Long.parseLong(dataSplit[3].substring(1, dataSplit[3].length() - 1));
                Integer streamerId = Integer.parseInt(dataSplit[4].substring(1, dataSplit[4].length() - 1));
                Long length = Long.parseLong(dataSplit[5].substring(1, dataSplit[5].length() - 1));
                Long dateAdded = Long.parseLong(dataSplit[6].substring(1, dataSplit[6].length() - 1));
                String name = "";
                if (dataSplit.length > 8) {
                    name = dataSplit[7]+","+dataSplit[8];
                    name = name.substring(1, name.length() - 1);
                } else {
                    name = dataSplit[7].substring(1, dataSplit[7].length() - 1);
                }
                Stream stream = new Stream.Builder(streamType, streamId, streamGenre, length, name, streamerId)
                        .noOfStreams(noOfStreams)
                        .dateAdded(dateAdded)
                        .build();
                ProiectPOO.getInstance().getStreams().add(stream);
                ProiectPOO.getInstance().getStreamerById(streamerId).getStreams().add(streamId);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readCommandsFromFile(File commandsFile) {
        try {
            Scanner myReader = new Scanner(commandsFile);
            while (myReader.hasNextLine()) {
                String s = myReader.nextLine();
                ProiectPOO.getInstance().getCommands().add(s);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}