/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;
import java.io.Serializable;

public class Property implements Serializable {
private static final long serialVersionUID = 1L;

    public int block;
    public int lot;
    public double area;
    public double floor;
    public double price;

    public String status = "Available";
    public boolean listed = true;
    public String buyer = "";

    public Property(int b, int l, double a, double f, double p) {
        block = b;
        lot = l;
        area = a;
        floor = f;
        price = p;
    }

    public String id() {
        return "Block " + block + " - Lot " + lot;
    }
}
