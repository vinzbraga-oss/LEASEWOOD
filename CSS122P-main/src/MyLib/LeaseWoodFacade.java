/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.io.*;

/**
 *
 * @author vinzb
 */
public class LeaseWoodFacade {
    
    private static final String DATA_FILE = "leasewood-test.ser";

    // Hides the complex try-catch and file stream logic from the GUI
    public void saveSystemData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(Data.getInstance());
            System.out.println("Data successfully saved to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Failed to save system data: " + e.getMessage());
        }
    }

    public void loadSystemData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting fresh.");
            // GENERATE THE 100 LOTS HERE:
            Data.getInstance().seedInitialData();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Data loadedData = (Data) ois.readObject();
            Data.setInstance(loadedData); 
            System.out.println("Data successfully loaded from " + DATA_FILE);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load system data: " + e.getMessage());
        }
    }
}
