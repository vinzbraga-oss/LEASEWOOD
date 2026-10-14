/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MyLib;
import java.io.Serializable;

public class Request implements Serializable {
    private String meetingSchedule;
    private String buyerUsername;
    private String propertyId;
    private double monthlyIncome;
    private String preferredPayment;
    private String status; // e.g., "Pending", "Acknowledged"

    public Request(String buyerUsername, String propertyId, double monthlyIncome, String preferredPayment) {
        this.buyerUsername = buyerUsername;
        this.meetingSchedule = "Not Scheduled";
        this.propertyId = propertyId;
        this.monthlyIncome = monthlyIncome;
        this.preferredPayment = preferredPayment;
        this.status = "Pending";
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public String getPreferredPayment() {
        return preferredPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMeetingSchedule() { 
        return meetingSchedule; 
    }
    public void setMeetingSchedule(String meetingSchedule) { 
        this.meetingSchedule = meetingSchedule; 
    }
}

