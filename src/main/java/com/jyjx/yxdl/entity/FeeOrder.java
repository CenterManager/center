package com.jyjx.yxdl.entity;

import java.util.Date;

public class FeeOrder {

    private String orderId;
    private int productId;
    private String playerId;
    private Date createtime;
    private boolean isCard;
    private boolean isSuperPackage;

    @Override
    public String toString() {
        return "FeeOrder{" +
                "orderId='" + orderId + '\'' +
                ", productId=" + productId +
                ", playerId='" + playerId + '\'' +
                ", createtime=" + createtime +
                ", isCard=" + isCard +
                ", isSuperPackage=" + isSuperPackage +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public boolean isCard() {
        return isCard;
    }

    public void setCard(boolean card) {
        isCard = card;
    }

    public boolean isSuperPackage() {
        return isSuperPackage;
    }

    public void setSuperPackage(boolean superPackage) {
        isSuperPackage = superPackage;
    }
}
