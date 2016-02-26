package com.example.richard.parking_lot;

/**
 * Created by richard on 1/31/2016.
 */
public class ParkingInfo {

    int sum = 200;
    String lotName;
    int availNo;
    double availPer ;
    int availNo2;
    double availPer2 ;
    int type;
    boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAvailNo2() {
        return availNo2;
    }

    public void setAvailNo2(int availNo2) {
        this.availNo2 = availNo2;
    }

    public double getAvailPer2() {
        return (availNo2 * 100.0) / sum;
    }

    public void setAvailPer2(int availPer2) {
        this.availPer2 = availPer2;
    }

    public ParkingInfo(String lotName, int availNo, int availNo2, int type) {
        this.lotName = lotName;
        this.availNo = availNo;

        this.availNo2 = availNo2;

        this.type = type;
    }

    public ParkingInfo(String lotName, int availNo, int type) {
        this.lotName = lotName;
        this.availNo = availNo;

        this.type = type;
    }

    public ParkingInfo() {
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public double getAvailPer() {
        return (availNo * 100.0) / sum ;
    }

    public void setAvailPer(int availPer) {
        this.availPer = availPer;
    }

    public int getAvailNo() {
        return availNo;
    }

    public void setAvailNo(int availNo) {
        this.availNo = availNo;
    }
}
