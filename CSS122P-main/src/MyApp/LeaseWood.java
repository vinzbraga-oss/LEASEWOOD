package MyApp;

import MyLib.*;
import GUIs.Client; // Assuming Client.java is your Login JFrame
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
    public static void generateAmortization(String option, double price, double salary, JTextArea outputArea) {
        HashMap<String, FinancingStrategy> strategies = new HashMap<>();
        strategies.put("Bank", new BankFinancing());
        strategies.put("In-house", new InHouseFinancing());
        strategies.put("Pag-IBIG", new PagIbigFinancing());

        FinancingStrategy strategy = strategies.get(option);
        
        if (strategy == null) {
            outputArea.setText("Error: Please select a valid financing option.");
            return;
        }

        double down = price * 0.10; // Standard 10% down payment
        double principal = price - down;

        StringBuilder sb = new StringBuilder("ILLUSTRATIVE ESTIMATE ONLY\n");
        sb.append("====================================\n");
        sb.append("Financing Type: ").append(option).append("\n");
        sb.append("Down payment (10%): ").append(money(down)).append("\n");
        sb.append("Loan principal: ").append(money(principal)).append("\n");
        sb.append("Assumed annual interest: ").append(String.format("%.2f", strategy.getAnnualInterestRate() * 100)).append("%\n");
        sb.append("Max suggested payment (30% of income): ").append(money(salary * 0.30)).append("\n");
        sb.append("====================================\n\n");

        // Loop through standard year terms and validate against the strategy
        for (int years : new int[]{5, 10, 15, 20, 25, 30}) {
            if (!strategy.isValidTerm(years)) continue; 
            
            double payment = strategy.calculateMonthlyAmortization(principal, years);
            String afford = (payment <= salary * 0.30) ? "Within income guideline" : "Above income guideline";
            sb.append(String.format("%2d years: %-20s (%s)\n", years, money(payment) + " / month", afford));
        }
        
        outputArea.setText(sb.toString());
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
        
        // 1. Load data before any JFrame appears
        systemFacade.loadSystemData();

        // 2. Create a Shutdown Hook. 
        // This guarantees the Facade saves the data whenever the application exits, 
        // regardless of which JFrame the user closes.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Application closing. Saving data...");
            systemFacade.saveSystemData();
        }));

        // 3. Launch the Login JFrame on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new Client().setVisible(true); // Launch your Login frame
        });
    }
}