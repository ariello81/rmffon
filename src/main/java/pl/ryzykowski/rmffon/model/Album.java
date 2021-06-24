package pl.ryzykowski.rmffon.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Album {

    private String title;

    @JsonAlias("cover_uri")
    private String coverUri;

    private String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
