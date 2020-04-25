package javaapplication3;

import java.awt.HeadlessException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;


public class JavaApplication3 { 

public static class SavingsAccount extends BankAccount { 
protected boolean isActive = (super.balance > 25); 

public SavingsAccount() { 
super(); 
} 
public SavingsAccount(double balance, double annualInterest) { 
super(balance, annualInterest); 
} 

@Override
public double getMonthlyCharge() { 
this.monthlyProcess(); 
return monthlyCharge; 
} 

@Override
public void monthlyProcess() { 
if (super.numberOfWithdrawals > 4) { 
super.monthlyCharge += super.numberOfWithdrawals - 4; 
if (super.balance < 25) { 
this.isActive = false; 
} 
} 
} 

@Override
public boolean withdraw(double amount) { 
if (isActive) { 
return super.withdraw(amount); 
} else { 
return false; 
} 
} 

@Override
public boolean deposit(double amount) { 
super.deposit(amount); 
if ((!this.isActive) && super.balance > 25) { 
this.isActive = true; 
return true; 
} else { 
return false; 
} 

} 

@Override
public boolean getIsActive() { 
return isActive; 
} 
public void setActive(boolean isActive) { 
this.isActive = isActive; 
} 

} 

public static abstract class BankAccount { 
protected double balance; 
protected int numberOfDeposits; 
protected int numberOfWithdrawals; 
protected double annualInterest; 
protected double monthlyCharge; 


public BankAccount(double balance, double annualInterest) { 
this.setAnnualInterest(annualInterest);
this.setBalance(balance); 
} 

public BankAccount() {} 


public void monthlyProcess() { 
this.balance -= this.monthlyCharge; 
this.calcInterest(); 
this.monthlyCharge = 0; 
this.numberOfDeposits = 0; 
this.numberOfWithdrawals = 0; 
} 


protected void calcInterest() { 
double monthlyInterestRate; 
double monthlyInterest; 

monthlyInterestRate = this.annualInterest / 12; 
monthlyInterest = this.balance * monthlyInterestRate; 
this.balance = this.balance + monthlyInterest; 
} 


public boolean withdraw(double amount) { 
if ((this.balance > amount) && (amount > 0)) { 
this.balance -= amount; 
this.numberOfWithdrawals++; 
return true; 
} else { 
return false; 
} 
} 


public boolean deposit(double amount) { 
if (amount > 0) { 
this.balance += amount; 
this.numberOfDeposits++; 
return true; 
} else { 
return false; 
} 
} 

 
public double getBalance() { 
return balance; 
} 

public void setBalance(double balance) { 
this.balance = balance; 
} 

public int getNumDepositsThisMonth() { 
return numberOfDeposits; 
} 

public void setNumDepositsThisMonth(int numDepositsThisMonth) { 
this.numberOfDeposits = numDepositsThisMonth; 
} 

public double getAnnualInterest() { 
return annualInterest; 
} 

public void setAnnualInterest(double annualInterest) { 
this.annualInterest = annualInterest; 
} 

public double getMonthlyCharge() { 
return monthlyCharge; 
} 

public void setMonthlyCharge(double monthlyCharge) { 
this.monthlyCharge = monthlyCharge; 
} 
public boolean getIsActive() { 
 
return (Boolean) null; 
} 
} 

public static void main(String[] args) { 
double balance = 0; 
double annualInterest = 0; 
String output; 
String input; 
DecimalFormat df = new DecimalFormat("#0.00"); 

do { 
try { 
output = "Masukkan Saldo Anda $:"; 
input = JOptionPane.showInputDialog(output); 

balance = Double.parseDouble(input); 
if (balance < 0) { 
throw new NumberFormatException(); 
} 
break; 
} catch (HeadlessException e) { 
errorMsg(); 
} catch (NumberFormatException e) { 
errorMsg(); 
} 
} while (true); 

do { 
try { 
output = "Silahkan Masukkan Tingkat Bunga Tahunan Anda : \n" 
+ "Contoh : Bunga 4% tingkat bunganya adalah 0.04"; 
input = JOptionPane.showInputDialog(output); 

annualInterest = Double.parseDouble(input); 
if (annualInterest < 0) { 
throw new NumberFormatException(); 
} 
break; 
} catch (HeadlessException e) { 
errorMsg(); 
} catch (NumberFormatException e) { 
errorMsg(); 
} 
} while (true); 

 
BankAccount savings = new SavingsAccount(balance, annualInterest); 

do { 
try { 
output = "Masukkan Jumlah Uang yang Akan Anda Tarik :"; 
input = JOptionPane.showInputDialog(output); 

savings.numberOfWithdrawals = Integer.parseInt(input); 
if (savings.numberOfWithdrawals < 0) { 
throw new NumberFormatException(); 
} 
break; 
} catch (HeadlessException e) { 
errorMsg(); 
} catch (NumberFormatException e) { 
errorMsg(); 
} 
} while (true); 

do { 
try { 
output = "Masukkan Jumlah Setoran Anda :"; 
input = JOptionPane.showInputDialog(output); 

savings.numberOfDeposits = Integer.parseInt(input); 
if (savings.numberOfDeposits < 0) { 
throw new NumberFormatException(); 
} 
break; 
} catch (HeadlessException e) { 
errorMsg(); 
} catch (NumberFormatException e) { 
errorMsg(); 
} 
} while (true); 

 
savings.calcInterest(); 

output = "Saldo Akun dengan Bunga : $" + df.format(savings.getBalance()); 
output += "\nBiaya Bulan yang Akan Dikurangkan : $" + df.format(savings.getMonthlyCharge()); 
output += "\nStatus Akun : "; 
output += savings.getIsActive() ? "Aktif" : "Tidak Aktif"; 

JOptionPane.showMessageDialog(null, output); 

} 

private static void errorMsg() { 
String output; 
output = "Error: There was an error with your entry"; 
JOptionPane.showMessageDialog(null, output); 
} 

}