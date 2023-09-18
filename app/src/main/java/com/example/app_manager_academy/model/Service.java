package com.example.app_manager_academy.model;

import java.util.Date;

public class Service {

    private String id;
    private String clienteId;
    private String employeeId;
    private Double totalPrice;
    private Double totalDiscount;
    private String cupom_descontoId;
    private String pacoteId;
    private Date   date;
    private Date    hour;
    private String formOfPaymentId;
    private String obs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getCupom_descontoId() {
        return cupom_descontoId;
    }

    public void setCupom_descontoId(String cupom_descontoId) {
        this.cupom_descontoId = cupom_descontoId;
    }

    public String getPacoteId() {
        return pacoteId;
    }

    public void setPacoteId(String pacoteId) {
        this.pacoteId = pacoteId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public String getFormOfPaymentId() {
        return formOfPaymentId;
    }

    public void setFormOfPaymentId(String formOfPaymentId) {
        this.formOfPaymentId = formOfPaymentId;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
