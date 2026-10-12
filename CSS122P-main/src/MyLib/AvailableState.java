/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.io.Serializable;

/**
 *
 * @author vinzb
 */
public class AvailableState implements PropertyState, Serializable {
    @Override
    public void reserve(Property property, String buyerName) {
        property.setBuyer(buyerName);
        property.setState((PropertyState) new ReservedState());
        System.out.println("Property reserved for " + buyerName + ".");
    }

    @Override
    public void occupy(Property property) {
        System.out.println("Error: Cannot occupy an available property without reserving it first.");
    }

    @Override
    public void makeAvailable(Property property) {
        System.out.println("Property is already available.");
    }

    @Override
    public String getStatusString() {
        return "Available";
    }
}
