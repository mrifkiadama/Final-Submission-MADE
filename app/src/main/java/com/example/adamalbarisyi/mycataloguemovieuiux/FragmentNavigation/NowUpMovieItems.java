package com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation;


 public class NowUpMovieItems {
    private int id;
    private String backDropPath;
    private String Title;
    private String oriTitle;
    private String oriLanguage;
    private String Vote;
    private String Rating;
    private String ReleaseDate;
    private String Overview;
    private String posterPath;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getOriTitle() {
        return oriTitle;
    }

    public void setOriTitle(String oriTitle) {
        this.oriTitle = oriTitle;
    }

    public String getOriLanguage() {
        return oriLanguage;
    }

    public void setOriLanguage(String oriLanguage) {
        this.oriLanguage = oriLanguage;
    }

    public String getVote() {
        return Vote;
    }

    public void setVote(String vote) {
        this.Vote = vote;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
       this.Rating = rating;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.ReleaseDate = releaseDate;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
       this.Overview = overview;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
