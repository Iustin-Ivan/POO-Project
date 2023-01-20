import java.util.ArrayList;
import java.util.List;

public class HumanFactory {
    public Human getHuman(String humanType, Integer id, String name, Integer streamerType, ArrayList<Integer> streams){
        if(humanType == null){
            return null;
        }
        if(humanType.equalsIgnoreCase("Streamer")){
            return new Streamer(id, name, streamerType);

        } else if(humanType.equalsIgnoreCase("User")){
            return new User(id, name, streams);
        }

        return null;
    }
}
