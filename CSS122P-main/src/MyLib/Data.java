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
        MyLib.Buyer testBuyer = new MyLib.Buyer("buyer", "1234");
        buyers.put(testBuyer.getUsername(), testBuyer);
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }
    
    // Add this method below your getInstance() method
    public void seedInitialData() {
        if (!properties.isEmpty()) {
            return; 
        }

        System.out.println("Generating property models...");
        
        // Default test account
        MyLib.Buyer testBuyer = new MyLib.Buyer("buyer", "1234");
        buyers.put(testBuyer.getUsername(), testBuyer);

        String[] models = {"KAYLA", "ALEXANDRIA", "AMARA", "ARYA"};

        for (int b = 1; b <= 5; b++) {
            for (int l = 1; l <= 20; l++) {
                
                // Distribute models evenly using modulo arithmetic
                String assignedModel = models[(b + l) % 4]; 
                
                String id = "B" + b + "-L" + String.format("%02d", l) + "-" + assignedModel;
                
                double lotArea = 0;
                double floorArea = 0;
                double tcp = 0;
                String features = "";

                switch (assignedModel) {
                    case "KAYLA":
                        lotArea = 96.0;
                        floorArea = 79.0;
                        tcp = 7097440.0;
                        features = "<html>Two-storey<br>Single-attached<br>3 bedrooms<br>2 toilet & bath<br>High-ceiling living area<br>Dining and Kitchen Area<br>Carport with Deck<br>Complete finish</html>";
                        break;
                    case "ALEXANDRIA":
                        lotArea = 138.0;
                        floorArea = 159.0;
                        tcp = 12206760.0;
                        features = "<html>Two-storey<br>Single-attached with attic<br>5 bedrooms<br>3 toilet & bath<br>Sliding door to lanai<br>Living, Dining and Kitchen Area<br>Carport with Deck<br>Complete finish</html>";
                        break;
                    case "AMARA":
                        lotArea = 110.0;
                        floorArea = 131.0;
                        tcp = 9444400.0;
                        features = "<html>Two-storey<br>Single-attached with attic<br>3 bedrooms<br>3 toilet & bath<br>Maid's room<br>Living, Dining and Kitchen Area<br>Carport with Deck<br>Complete finish</html>";
                        break;
                    case "ARYA":
                        lotArea = 96.0;
                        floorArea = 84.0;
                        tcp = 7265440.0;
                        features = "<html>Two-storey<br>Single-attached<br>3 bedrooms<br>2 toilet & bath<br>Family room<br>Dining and Kitchen Area<br>Carport with Deck<br>Complete finish</html>";
                        break;
                }

                // Instantiate the specific property model
                properties.put(id, new Property(id, b, l, assignedModel, lotArea, floorArea, tcp, features));
            }
        }
    }
    public java.util.ArrayList<Message> allMessages = new java.util.ArrayList<>();
    public static void setInstance(Data loadedData) {
        instance = loadedData;
    }
}
