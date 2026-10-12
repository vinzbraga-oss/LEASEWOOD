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
public class Admin extends User implements Serializable {
    
    // Ensure Admin also accepts both parameters to satisfy the User constructor
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() { 
        return "Admin"; 
    }
}