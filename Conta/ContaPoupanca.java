package Conta;

public class ContaPoupanca extends Conta<String> {
    public ContaPoupanca(double saldoInicial) {
        super("Conta Poupança", saldoInicial); 
    }
}