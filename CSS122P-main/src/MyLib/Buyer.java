/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.io.Serializable;

public class Buyer extends User implements Serializable {
    
    public Buyer(String username, String password) {
        super(username, password); // Passes credentials to User.java
    }

    @Override
    public String getRole() { 
        return "Client"; 
    }
}

