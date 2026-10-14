package MyApp;

import MyLib.*;
import GUIs.Login; // Assuming Login.java is your Login JFrame
import javax.swing.*;
import java.util.HashMap;

public class LeaseWood {
    
    // The Facade handles data saving/loading globally
    public static LeaseWoodFacade systemFacade = new LeaseWoodFacade();

    /**
     * STRATEGY PATTERN IMPLEMENTATION:
     * Calculates the amortization table dynamically based on the selected financing strategy.
     * Accessible globally via LeaseWood.generateAmortization(...)
     */
    public static void generateAmortization(String option, double price, double salary, javax.swing.table.DefaultTableModel model, javax.swing.JLabel lblSummary) {
        HashMap<String, FinancingStrategy> strategies = new HashMap<>();
        strategies.put("Bank", new BankFinancing());
        strategies.put("In-house", new InHouseFinancing());
        strategies.put("Pag-IBIG", new PagIbigFinancing());

        FinancingStrategy strategy = strategies.get(option);
        
        if (strategy == null) return;

        double down = price * 0.10; 
        double principal = price - down;
        
        // Update the summary label above the table
        lblSummary.setText(String.format("TCP: %s | 10%% DP: %s | Principal: %s", money(price), money(down), money(principal)));

        // Clear existing table rows
        model.setRowCount(0);

        for (int years : new int[]{5, 10, 15, 20, 25, 30}) {
            if (!strategy.isValidTerm(years)) continue; 
            
            double payment = strategy.calculateMonthlyAmortization(principal, years);
            String afford = (payment <= salary * 0.30) ? "Eligible" : "Not Eligible (Exceeds 30%)";
            
            // Add the calculation as a new row in the JTable
            model.addRow(new Object[]{years + " Years", money(payment), afford});
        }
    }

    /**
     * Utility Method: Formats double values to PHP currency
     */
    private static String money(double amount) {
        return String.format("PHP %,.2f", amount);
    }
    
    /**
     * Main Execution - Application Bootstrapper
     */
    public static void main(String[] args) {

        systemFacade.loadSystemData();
        
        if (MyLib.Data.getInstance().properties.isEmpty()) {
            System.out.println("No save file found. Generating default subdivision lots...");
            MyLib.Data.getInstance().seedInitialData();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Application closing. Saving data...");
            systemFacade.saveSystemData();
        }));

        
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true); 
        });
    }
}