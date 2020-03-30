package com.creditapp.loginservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("salary")
    private double salary;
    @JsonProperty("monthlyExpenses")
    private double monthlyExpenses;
    @JsonProperty("login")
    private String login;
    @JsonProperty("email")
    private String email;
    @JsonProperty("hashKey")
    private byte[] hashKey;
    @JsonProperty("salt")
    private byte[] salt;
    @JsonProperty("freeFund")
    private double freeFund;

    public UserDto(String firstName, String lastName, long salary, long monthlyExpenses, String login, byte[] hashKey, byte[] salt, String email, double freeFund) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.monthlyExpenses = monthlyExpenses;
        this.login = login;
        this.hashKey = hashKey;
        this.salt = salt;
        this.email = email;
        this.freeFund = freeFund;
    }

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public double getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getHashKey() {
        return hashKey;
    }

    public byte[] getSalt() {
        return salt;
    }

    public double getFreeFund() {
        return freeFund;
    }
}
