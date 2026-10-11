/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MyLib;
import java.io.Serializable;

public class Meeting implements Serializable {

    public String buyer;
    public String agent;
    public String date;
    public String notes;

    public Meeting(String b, String a, String d, String n) {
        buyer = b;
        agent = a;
        date = d;
        notes = n;
    }
}

