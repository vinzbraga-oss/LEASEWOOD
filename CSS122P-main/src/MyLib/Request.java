/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MyLib;
import java.io.Serializable;

public class Request implements Serializable {
    private String buyerUsername;
    private String propertyId;
    private double monthlyIncome;
    private String preferredPayment;
    private String status; // e.g., "Pending", "Acknowledged"

    public Request(String buyerUsername, String propertyId, double monthlyIncome, String preferredPayment) {
        this.buyerUsername = buyerUsername;
        this.propertyId = propertyId;
        this.monthlyIncome = monthlyIncome;
        this.preferredPayment = preferredPayment;
        this.status = "Pending";
    }

    // Add getters for all fields here...
}

