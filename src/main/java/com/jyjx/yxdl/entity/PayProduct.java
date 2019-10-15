package com.jyjx.yxdl.entity;

public class PayProduct {

    private int payProductId;
    private int money;
    private String description;
    private int value;
    private String label;

    @Override
    public String toString() {
        return "PayProduct{" +
                "payProductId=" + payProductId +
                ", money=" + money +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", label='" + label + '\'' +
                '}';
    }

    public int getPayProductId() {
        return payProductId;
    }

    public void setPayProductId(int payProductId) {
        this.payProductId = payProductId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
