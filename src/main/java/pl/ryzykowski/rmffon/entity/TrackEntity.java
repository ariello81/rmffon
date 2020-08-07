package pl.ryzykowski.rmffon.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity (name="track")
public class TrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="station_name")
    private String stationName;

    @Column(name="author")
    private String author;

    @Column(name="title")
    private String title;

    @Column(name="record_title")
    private String recordTitle;

    @Column(name="length")
    private String lenght;

    @Column(name="year")
    private int year;

    @Column(name="start")
    private String start;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TrackEntity{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", recordTitle='" + recordTitle + '\'' +
                ", lenght='" + lenght + '\'' +
                ", year=" + year +
                ", start='" + start + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackEntity that = (TrackEntity) o;
        return Objects.equals(stationName, that.stationName) &&
                Objects.equals(author, that.author) &&
                Objects.equals(title, that.title) &&
                Objects.equals(start, that.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, author, title, start);
    }
}
