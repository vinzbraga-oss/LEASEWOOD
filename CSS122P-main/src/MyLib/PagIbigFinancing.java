/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author vinzb
 */
public class PagIbigFinancing implements FinancingStrategy {
    @Override
    public double getAnnualInterestRate() {
        return 0.063; // 6.3%
    }

    @Override
    public boolean isValidTerm(int years) {
        return true; // Pag-IBIG allows up to 30 years
    }

    @Override
    public double calculateMonthlyAmortization(double principal, int years) {
        double r = getAnnualInterestRate() / 12;
        int n = years * 12;
        return principal * r * Math.pow(1 + r, n) / (Math.pow(1 + r, n) - 1);
    }
}
