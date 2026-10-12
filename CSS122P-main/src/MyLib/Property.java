/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;
import java.io.Serializable;

public class Property implements Serializable {
    private String id;       // e.g., "B1-L01-KAYLA"
    private int block;       // 1 to 5
    private int lotNumber;   // 1 to 20
    private double lotSize;  // in square meters
    private double price;    // Total Contract Price (TCP)
    private String buyer;
    private boolean listed;
    
    // The current state of the property
    private PropertyState state;

    public Property(String id, int block, int lotNumber, double lotSize, double price) {
        this.id = id;
        this.block = block;
        this.lotNumber = lotNumber;
        this.lotSize = lotSize;
        this.price = price;
        this.buyer = null;
        this.listed = true;
        this.state = new AvailableState(); // Default state
    }

    // State Pattern Delegation Methods
    public void reserve(String buyerName) {
        state.reserve(this, buyerName);
    }

    public void occupy() {
        state.occupy(this);
    }

    public void makeAvailable() {
        state.makeAvailable(this);
    }

    // Standard Getters and Setters
    public int getBlock() { return block; }
    
    public double getLotSize() { return lotSize; }
    
    public double getPrice() { return price; }
    
    public String getId() { return id; }
    
    public String getStatus() { return state.getStatusString(); }
    
    public boolean isListed() { return listed; }
    
    public void setListed(boolean listed) { this.listed = listed; }
    
    public String getBuyer() { return buyer; }
    
    public void setBuyer(String buyer) { this.buyer = buyer; }
    
    public void setState(PropertyState state) { this.state = state; }

}