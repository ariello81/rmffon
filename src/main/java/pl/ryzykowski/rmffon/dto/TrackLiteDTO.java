package pl.ryzykowski.rmffon.dto;

public class TrackLiteDTO {

    private String start;
    private String author;
    private String title;
    private String lenght;
    private String stationName;
    private int votes;
    private int points;


    public TrackLiteDTO() {
    }

    public TrackLiteDTO(String start, String author, String title, String lenght, String stationName, int votes, int points) {
        this.start = start;
        this.author = author;
        this.title = title;
        this.lenght = lenght;
        this.stationName = stationName;
        this.votes = votes;
        this.points = points;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
