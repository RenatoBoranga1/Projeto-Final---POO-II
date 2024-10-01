package Conta;

public class Conta<T> {
    private T tipoConta;  
    private double saldo; 

    public Conta(T tipoConta, double saldoInicial) {
        this.tipoConta = tipoConta;
        this.saldo = saldoInicial; 
    }

    public T getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor; 
        } else {
            System.out.println("Valor de depósito deve ser positivo.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor; 
        } else {
            System.out.println("Saque inválido.");
        }
    }
}