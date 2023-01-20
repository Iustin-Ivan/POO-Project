import java.util.*;
import java.text.*;

public class Stream {
    private Integer streamType;
    private Integer id;
    private Integer streamGenre;
    private Long noOfStreams;
    private Integer streamerId;
    private Long length;
    private Long dateAdded;
    private String name;
    private String streamerName;

    public Stream(Integer streamType, Integer id, Integer streamGenre, Long noOfStreams,
                  Integer streamerId, Long length, Long dateAdded, String name, String streamerName) {
        this.streamType = streamType;
        this.id = id;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
        this.streamerName = streamerName;
    }

    public Integer getStreamType() {
        return streamType;
    }

    public void setStreamType(Integer streamType) {
        this.streamType = streamType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStreamGenre() {
        return streamGenre;
    }

    public void setStreamGenre(Integer streamGenre) {
        this.streamGenre = streamGenre;
    }

    public Long getNoOfStreams() {
        return noOfStreams;
    }

    public void setNoOfStreams(Long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public Integer getStreamerId() {
        return streamerId;
    }

    public void setStreamerId(Integer streamerId) {
        this.streamerId = streamerId;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String timeFormat = "";
        if (length < 3600) {
            timeFormat = String.format("%02d:%02d", length / 60, length % 60);
        } else {
            timeFormat = String.format("%02d:%02d:%02d", length / 3600, (length % 3600) / 60, length % 60);
        }
        String date = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date (dateAdded*1000));
        String s = "";
        s += "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"streamerName\":\"" + streamerName +
                "\",\"noOfListenings\":\"" + noOfStreams + "\",\"length\":\"" + timeFormat + "\",\"dateAdded\":\"" + date + "\"}";
        return s;
    }
}
