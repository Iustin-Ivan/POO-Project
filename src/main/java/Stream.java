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
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = sdf.format(new java.util.Date(dateAdded * 1000));

        String s = "";
        s += "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"streamerName\":\"" + streamerName +
                "\",\"noOfListenings\":\"" + noOfStreams + "\",\"length\":\"" + timeFormat + "\",\"dateAdded\":\"" + date + "\"}";
        return s;
    }

    public static class Builder {
        private Integer streamType;
        private Integer id;
        private Integer streamGenre;
        private Long noOfStreams = 0L;
        private Integer streamerId;
        private Long length;
        private Long dateAdded = 1673568000L;
        private String name;
        private String streamerName;


        public Builder(Integer streamType, Integer streamId, Integer streamGenre, Long length,
                       String name, Integer streamerId) {
            this.id = streamId;
            this.streamType = streamType;
            this.streamGenre = streamGenre;
            this.length = length;
            this.name = name;
            this.streamerId = streamerId;
            Human h = ProiectPOO.getInstance().getStreamerById(streamerId);
            this.streamerName = h.getName();
        }

        public Builder noOfStreams(Long noOfStreams) {
            this.noOfStreams = noOfStreams;
            return this;
        }

        public Builder dateAdded(Long dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Stream build() {
            return new Stream(this);
        }
    }

        private Stream(Builder builder) {
                this.streamType = builder.streamType;
                this.id = builder.id;
                this.streamGenre = builder.streamGenre;
                this.noOfStreams = builder.noOfStreams;
                this.streamerId = builder.streamerId;
                this.length = builder.length;
                this.dateAdded = builder.dateAdded;
                this.name = builder.name;
                this.streamerName = builder.streamerName;
            }
    }
