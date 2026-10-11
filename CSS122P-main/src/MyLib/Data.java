/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MyLib;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Data implements Serializable {

    public ArrayList<Property> properties = new ArrayList<>();
    public ArrayList<Buyer> buyers = new ArrayList<>();
    public ArrayList<Request> requests = new ArrayList<>();
    public ArrayList<Message> messages = new ArrayList<>();
    public ArrayList<Meeting> meetings = new ArrayList<>();
    public ArrayList<Sale> sales = new ArrayList<>();

    public HashMap<String, Double> wallets = new HashMap<>();

    public HashMap<String, ArrayList<String>> notifications
            = new HashMap<>();
}

