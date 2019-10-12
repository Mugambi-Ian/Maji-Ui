package com.nenecorp.majiapp.DataModels;

public class InAppMessage {
    private class Status {
        String Status;

        Status(String status) {
            Status = status;
        }
    }

    private Status status;
    private Incident incident;

    public InAppMessage(Incident incident) {
        this.incident = incident;
    }

    public InAppMessage(String status) {
        this.status = new Status(status);
    }

    public boolean isStatus() {
        if (status != null) {
            return status.Status != null;
        } else {
            return false;
        }
    }

    public String getStatus() {
        return status.Status;
    }

    public String getIncidentTitle() {
        return incident.incidentTitle;
    }

    public String getIncidentDate() {
        return incident.incidentDate;
    }

    public String getIncidentTicketNo() {
        return incident.incidentTicketNo;
    }

    public String getIncidentMessage() {
        return incident.incidentMessage;
    }

    public String getIncidentPic() {
        return incident.incidentPic;
    }


    public Incident getIncident() {
        return incident;
    }
}
