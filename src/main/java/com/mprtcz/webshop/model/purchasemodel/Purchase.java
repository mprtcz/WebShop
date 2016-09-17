package com.mprtcz.webshop.model.purchasemodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Azet on 2016-09-08.
 */
public class Purchase {

    @NotNull
    @Min(value = 1,
        message = "You must order at least 1 item")
    private Integer quantity;

    @NotNull
    private String userName;

    @NotNull
    private Integer itemId;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
