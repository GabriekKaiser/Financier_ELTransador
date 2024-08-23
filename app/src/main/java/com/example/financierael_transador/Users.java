package com.example.financierael_transador;

public class Users {
    private String username;
    private String password;
    private double saldo; // Saldo del usuario
    private String numCuenta; // Número de cuenta

    // Constructor
    public Users(String username, String password, double saldo, String numCuenta) {
        this.username = username;
        this.password = password;
        this.saldo = saldo;
        this.numCuenta = numCuenta;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }
}
