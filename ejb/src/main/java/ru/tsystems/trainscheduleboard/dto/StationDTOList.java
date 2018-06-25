package ru.tsystems.trainscheduleboard.dto;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class StationDTOList implements Serializable {

    private List<StationDTO> stationDTOList;

    public List<StationDTO> getStationDTOList() {
        return stationDTOList;
    }

    public void setStationDTOList(List<StationDTO> stationDTOList) {
        this.stationDTOList = stationDTOList;
    }
}
