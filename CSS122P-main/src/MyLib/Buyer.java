/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;
import java.io.Serializable;

public class Buyer implements Serializable {

    public String email;
    public String name;
    public String password;
    public String agent = "";

    public Buyer(String e, String n, String p) {
        email = e;
        name = n;
        password = p;
    }
}

