package Conta;

public class ContaCorrente extends Conta<String> {
    public ContaCorrente(double saldoInicial) {
        super("Conta Corrente", saldoInicial); 
    }
}