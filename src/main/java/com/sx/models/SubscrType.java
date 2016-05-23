package com.sx.models;

public enum SubscrType {TWELVE(12, "12 Sessies (12 weken)"), TWENTYFOUR(24, "24 Sessies (12 weken)");

    private int sessions;
    private String description;


    private SubscrType(int sessions, String description) {
        this.sessions = sessions;
        this.description = description;
    }

    public int getSessions() {
        return sessions;
    }

    public void setSessions(int sessions) {
        this.sessions = sessions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
