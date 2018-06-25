package ru.tsystems.trainscheduleboard.dto;

public class StationDTO {

    private int id;
    private String stationname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public StationDTO() {
    }

    public StationDTO(int id, String stationname) {
        this.id = id;
        this.stationname = stationname;
    }
}
