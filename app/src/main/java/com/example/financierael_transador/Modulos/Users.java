package com.example.financierael_transador.Modulos;

public class Users {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private double saldo; // Saldo del usuario

    // Constructor
    public Users(Integer id, String username, String password, String email, double saldo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.saldo = saldo;
    }

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}

