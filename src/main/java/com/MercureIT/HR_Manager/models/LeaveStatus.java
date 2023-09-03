package com.MercureIT.HR_Manager.models;

public enum LeaveStatus {

	PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");
	
    private final String status;

    LeaveStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
