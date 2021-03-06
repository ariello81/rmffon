package pl.ryzykowski.rmffon.dto;

public class TrackDTO {

    private int order;
    private String stationId;
    private String stationName;
    private String author;
    private String authorUrl;
    private String title;
    private String recordTitle;
    private String lenght;
    private int year;
    private String start;
    private String coverUrl;
    private String coverBigUrl;
    private int votes;
    private int points;
    private String average;
    private String stationLink;

    public TrackDTO() {
    }

    public TrackDTO(int order, String stationId, String stationName, String author, String authorUrl, String title, String recordTitle, String lenght,
                    int year, String start, String coverUrl, String coverBigUrl, int votes, int points, String average, String stationLink) {
        this.order = order;
        this.stationId = stationId;
        this.stationName = stationName;
        this.author = author;
        this.authorUrl = authorUrl;
        this.title = title;
        this.recordTitle = recordTitle;
        this.lenght = lenght;
        this.year = year;
        this.start = start;
        this.coverUrl = coverUrl;
        this.coverBigUrl = coverBigUrl;
        this.votes = votes;
        this.points = points;
        this.average = average;
        this.stationLink = stationLink;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
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

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverBigUrl() {
        return coverBigUrl;
    }

    public void setCoverBigUrl(String coverBigUrl) {
        this.coverBigUrl = coverBigUrl;
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

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getStationLink() {
        return stationLink;
    }

    public void setStationLink(String stationLink) {
        this.stationLink = stationLink;
    }

    @Override
    public String toString() {
        return "TrackDTO{" +
                "order=" + order +
                ", stationId='" + stationId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", author='" + author + '\'' +
                ", authorUrl='" + authorUrl + '\'' +
                ", title='" + title + '\'' +
                ", recordTitle='" + recordTitle + '\'' +
                ", lenght='" + lenght + '\'' +
                ", year=" + year +
                ", start='" + start + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", coverBigUrl='" + coverBigUrl + '\'' +
                ", votes=" + votes +
                ", points=" + points +
                ", average='" + average + '\'' +
                '}';
    }
}
