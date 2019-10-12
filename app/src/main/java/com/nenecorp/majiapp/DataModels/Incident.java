package com.nenecorp.majiapp.DataModels;

public class Incident {
    String incidentTitle, incidentDate, incidentTicketNo, incidentMessage, incidentPic;



    public void setIncidentTitle(String incidentTitle) {
        this.incidentTitle = incidentTitle;
    }


    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }



    public void setIncidentTicketNo(String incidentTicketNo) {
        this.incidentTicketNo = incidentTicketNo;
    }



    public void setIncidentMessage(String incidentMessage) {
        this.incidentMessage = incidentMessage;
    }



    public void setIncidentPic(String incidentPic) {
        this.incidentPic = incidentPic;
    }

    public Incident(String incidentTitle) {
        this.incidentTitle = incidentTitle;
    }
}
