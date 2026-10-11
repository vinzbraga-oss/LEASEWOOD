/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MyLib;
import java.io.Serializable;

public class Sale implements Serializable {

    public String property;
    public String buyer;
    public String agent;
    public double tcp;
    public double paid;

    public Sale(String p, String b, String a, double t, double d) {
        property = p;
        buyer = b;
        agent = a;
        tcp = t;
        paid = d;
    }
}

