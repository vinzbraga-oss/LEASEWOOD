/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MyLib;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

    public String from;
    public String to;
    public String text;
    public String time;

    public Message(String f, String t, String m) {
        from = f;
        to = t;
        text = m;
        time = LocalDateTime.now().toString()
                .replace('T', ' ').substring(0, 16);
    }
}

