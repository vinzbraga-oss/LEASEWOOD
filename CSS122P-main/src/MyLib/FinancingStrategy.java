/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MyLib;

/**
 *
 * @author vinzb
 */
public interface FinancingStrategy {
    double getAnnualInterestRate();
    boolean isValidTerm(int years);
    double calculateMonthlyAmortization(double principal, int years);
}
