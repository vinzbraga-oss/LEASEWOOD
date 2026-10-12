/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MyLib;
import java.util.HashMap;
import java.io.Serializable;

public class Data implements Serializable{
    private static Data instance;

    public HashMap<String, Buyer> buyers = new HashMap<>();
    public HashMap<String, Agent> agents = new HashMap<>();
    
    // Add this missing line right here:
    public HashMap<String, Property> properties = new HashMap<>();

    public java.util.ArrayList<Request> clientRequests = new java.util.ArrayList<>();
    private Data() {
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }
    
    // Add this method below your getInstance() method
    public void seedInitialData() {
        // Prevent re-seeding if data already exists
        if (!properties.isEmpty()) {
            return; 
        }

        System.out.println("Generating 100 subdivision lots...");
        
        // --- ADD THIS BLOCK FOR TEST ACCOUNTS ---
        System.out.println("Generating default test accounts...");
        MyLib.Buyer testBuyer = new MyLib.Buyer("buyer", "1234");
        buyers.put(testBuyer.getUsername(), testBuyer);
        // ----------------------------------------
        // Loop through 5 blocks
        for (int b = 1; b <= 5; b++) {
            // Loop through 20 lots per block
            for (int l = 1; l <= 20; l++) {
                // Generate a formatted ID like "B1-L01"
                String id = "B" + b + "-L" + String.format("%02d", l);
                
                // Variable pricing and sizing logic based on the block
                double lotSize = 90.0 + (b * 10.0); // e.g., Block 1 = 100sqm, Block 5 = 140sqm
                double pricePerSqm = 15000.0;       // PHP 15,000 per square meter
                double price = lotSize * pricePerSqm;
                
                // Instantiate the Property and save it to the HashMap
                properties.put(id, new Property(id, b, l, lotSize, price));
            }
        }
    }
    public static void setInstance(Data loadedData) {
        instance = loadedData;
    }
}
