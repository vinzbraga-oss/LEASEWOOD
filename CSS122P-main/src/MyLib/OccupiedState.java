/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author vinzb
 */
public class OccupiedState implements PropertyState {
    @Override
    public void reserve(Property property, String buyerName) {
        System.out.println("Error: Cannot reserve. Property is already sold and occupied.");
    }

    @Override
    public void occupy(Property property) {
        System.out.println("Property is already occupied.");
    }

    @Override
    public void makeAvailable(Property property) {
        property.setBuyer(null);
        property.setState(new AvailableState());
        System.out.println("Property vacated and is now available.");
    }

    @Override
    public String getStatusString() {
        return "Occupied";
    }
}
