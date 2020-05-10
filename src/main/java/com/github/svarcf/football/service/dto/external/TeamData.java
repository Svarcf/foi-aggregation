package com.github.svarcf.football.service.dto.external;

public class TeamData {

    private long id;
    private String name;
    private String crestUrl;
    private String venue;
    private String tla;
    private String address;
    private int founded;
    private long competition;

    public TeamData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTla() {
        return tla;
    }

    public void setTla(String tla) {
        this.tla = tla;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public long getCompetition() {
        return competition;
    }

    public void setCompetition(long competition) {
        this.competition = competition;
    }
}
