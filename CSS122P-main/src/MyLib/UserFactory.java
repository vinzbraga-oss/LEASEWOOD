/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author vinzb
 */
public class UserFactory {
    
    public static User authenticateUser(String username, String password) {
        // 1. Detect Admin (Pre-plugged credentials)
        if (username.equals("admin") && password.equals("admin123")) {
            return new Admin(username, password);
        }
        
        // 2. Detect Agent (Pre-plugged into the system by the Admin)
        Agent agent = Data.getInstance().agents.get(username);
        if (agent != null && agent.getPassword().equals(password)) {
            return agent;
        }
        
        // 3. Detect Buyer (Accounts created via the Register UI)
        Buyer buyer = Data.getInstance().buyers.get(username);
        if (buyer != null && buyer.getPassword().equals(password)) {
            return buyer;
        }
        
        return null; // Invalid credentials
    }

    public static User registerNewClient(String username, String password) {
        if (Data.getInstance().buyers.containsKey(username)) {
            return null; // Username is already taken
        }
        Buyer newBuyer = new Buyer(username, password);
        Data.getInstance().buyers.put(username, newBuyer);
        return newBuyer;
    }
}
