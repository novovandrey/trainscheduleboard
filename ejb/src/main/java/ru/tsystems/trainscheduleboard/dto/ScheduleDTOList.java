package ru.tsystems.trainscheduleboard.dto;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
@SessionScoped
public class ScheduleDTOList implements Serializable {

    private List<ScheduleDTO> scheduleDTOList;

    public List<ScheduleDTO> getScheduleDTOList() {
        return scheduleDTOList;
    }

    public void setScheduleDTOList(List<ScheduleDTO> scheduleDTOList) {
        this.scheduleDTOList = scheduleDTOList;
    }
}
