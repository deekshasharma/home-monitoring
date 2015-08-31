package com.homemonitoring.model;

public class Sound {

    private String moduleId;
    private String reading;
    private String dateTime;

    public Sound(String moduleId, String reading, String dateTime) {
        this.moduleId = moduleId;
        this.reading = reading;
        this.dateTime = dateTime;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


}
