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
    
    public Agent(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() { 
        return "Agent"; 
    }
}