package com.example.queue_management_project.Model;

import java.util.Date;

public class Event {

    private String NameEvent;
    private String DateEvent;
    private  int Position;


    public Event(String nameEvent, String dateEvent, int pos) {
        NameEvent = nameEvent;
        DateEvent = dateEvent;
        Position = pos;
    }


    public Event(String name, String Date) {
        this.NameEvent = name;
        this.DateEvent=Date;

    }
    public Event(){}

    public String getNameEvent() {
        return NameEvent;
    }

    public void setNameEvent(String nameEvent) {
        NameEvent = nameEvent;
    }

    public String getDateEvent() {
        return DateEvent;
    }

    public void setDateEvent(String dateEvent) {
        DateEvent = dateEvent;
    }

    public int getPositionEvent() {
        return Position;
    }

    public void setPositionEvent(int  pos) {
        Position = pos;
    }

    public Event(String date){
        this.DateEvent=date;
    }


}

