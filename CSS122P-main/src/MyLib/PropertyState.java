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
public interface PropertyState extends Serializable{
    void reserve(Property property, String buyerName);
    void occupy(Property property);
    void makeAvailable(Property property);
    String getStatusString();
}