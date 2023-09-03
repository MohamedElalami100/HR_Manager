package com.MercureIT.HR_Manager.models;

public enum AppicationStatus {
	HIRED("Hired"),
	SHORTLISTED("ShortListed"),
	INTERVIEWSCHEDULED("InterviewScheduled"),
    REJECTED("Rejected");
	
    private final String status;

    AppicationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
