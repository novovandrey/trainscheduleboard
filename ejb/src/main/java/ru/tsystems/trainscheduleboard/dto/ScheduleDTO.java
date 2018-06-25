package ru.tsystems.trainscheduleboard.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ScheduleDTO implements Serializable {

    private String traincode;
    private Date departuredate;
    private String depstationname;
    private Date arrivaldate;
    private String arrstationname;
    //todo camelCase

    public int getIntegerIterator() {
        return integerIterator;
    }

    public void setIntegerIterator(int integerIterator) {
        this.integerIterator = integerIterator;
    }

    private int integerIterator;

    public BigDecimal getDoubleIterator() {
        return doubleIterator;
    }

    public void setDoubleIterator(BigDecimal doubleIterator) {
        this.doubleIterator = doubleIterator;
    }

    private BigDecimal doubleIterator;

    public String getTraincode() {
        return traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    public String getDepstationname() {
        return depstationname;
    }

    public void setDepstationname(String depstationname) {
        this.depstationname = depstationname;
    }

    public Date getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public String getArrstationname() {
        return arrstationname;
    }

    public void setArrstationname(String arrstationname) {
        this.arrstationname = arrstationname;
    }

    public ScheduleDTO() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScheduleDTO{");
        sb.append("traincode='").append(traincode).append('\'');
        sb.append(", departuredate=").append(departuredate);
        sb.append(", depstationname='").append(depstationname).append('\'');
        sb.append(", arrivaldate=").append(arrivaldate);
        sb.append(", arrstationname='").append(arrstationname).append('\'');
        sb.append(", integerIterator=").append(integerIterator);
        sb.append(", doubleIterator=").append(doubleIterator);
        sb.append('}');
        return sb.toString();
    }
}
