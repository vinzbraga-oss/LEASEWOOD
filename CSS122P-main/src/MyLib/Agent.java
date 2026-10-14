/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MyLib;

import java.io.Serializable;

/**
 *
 * @author vinzb
 */

public class Agent extends User implements Serializable {
    private double commissionBalance;
    
    public Agent(String username, String password) {
        super(username, password);
        this.commissionBalance = 50000.00;
    }
    
    public double getCommissionBalance() { 
        return commissionBalance; 
    }
    
    public void addCommission(double amount) { 
        this.commissionBalance += amount; 
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= commissionBalance) {
            commissionBalance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String getRole() { 
        return "Agent"; 
    }
}