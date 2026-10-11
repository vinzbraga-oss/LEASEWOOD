/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MyLib;
import java.io.Serializable;

public class Request implements Serializable {

    public String buyer;
    public String property;
    public String status = "Pending";
    public String agent = "";
    public String type;

    public Request(String b, String p, String t) {
        buyer = b;
        property = p;
        type = t;
    }
}

