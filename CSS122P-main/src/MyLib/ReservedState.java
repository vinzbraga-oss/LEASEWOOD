/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author vinzb
 */
public class ReservedState implements PropertyState {
    @Override
    public void reserve(Property property, String buyerName) {
        System.out.println("Error: Property is already reserved.");
    }

    @Override
    public void occupy(Property property) {
        property.setState(new OccupiedState());
        System.out.println("Property is now fully occupied/sold.");
    }

    @Override
    public void makeAvailable(Property property) {
        property.setBuyer(null);
        property.setState(new AvailableState());
        System.out.println("Reservation cancelled. Property is available again.");
    }

    @Override
    public String getStatusString() {
        return "Reserved";
    }
}
