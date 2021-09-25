package com.example.queue_management_project.Model;

import java.util.Date;

public class Event {

    private String NameEvent;
    private String DateEvent;
    private String TimeEvent;

    public Event(String name, String Date , String Time) {
        this.NameEvent = name;
        this.DateEvent=Date;
        this.TimeEvent=Time;

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

    public String getTimeEvent() {
        return TimeEvent;
    }

    public void setTimeEvent(String timeEvent) {
        TimeEvent = timeEvent;
    }



}

