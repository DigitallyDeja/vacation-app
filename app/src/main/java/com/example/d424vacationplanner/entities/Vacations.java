package com.example.d424vacationplanner.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.d424vacationplanner.UI.Location;

@Entity(tableName = "vacation")
public class Vacations extends Location {

    @PrimaryKey(autoGenerate = true)
    private int vacationID;
    private String vacationName;
    private String hotelName;
    private String startDate;
    private String endDate;

    public Vacations (int vacationID, String vacationName, String hotelName, String startDate, String endDate) {
        super(vacationID, vacationName, startDate);
        this.vacationID = vacationID;
        this.vacationName = vacationName;
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getVacationID() {
        return vacationID;
    }

    public String toString(){
        return vacationName;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public String getVacationName() {
        return vacationName;
    }

    public void setVacationName(String vacationName) {
        this.vacationName = vacationName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getLocationDetails() {
        return "Vacation: " + getName() + "\nHotel: " + hotelName + "\nStart: " + startDate + "\nEnd: " + endDate;
    }
}
