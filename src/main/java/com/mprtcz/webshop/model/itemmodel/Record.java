package com.mprtcz.webshop.model.itemmodel;

import com.mprtcz.webshop.model.usermodel.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Azet on 2016-09-18.
 */
@Entity
@Table(name="RECORDS")
public class Record {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name="ITEM_NAME", nullable=false)
    private
    String itemName;

    @NotNull
    @Column(name="PURCHASE_VALUE", nullable=false, precision = 2)
    private
    BigInteger price;

    @NotNull
    @Column(name="QUANTITY", nullable = false)
    private
    Integer quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "TRANSACTION_TIME", nullable = false, length = 10)
    private Date transactionTime;


    @Column(name="ORIGINAL_ITEM_ID", nullable = false)
    private Integer originalItemId;

    @NotNull
    @Column(name="BUYER_ID", nullable = false)
    private Integer buyerId;

    public Integer getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigInteger getprice() {
        return price;
    }

    public void setprice(BigInteger price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Integer getOriginalItemId() {
        return originalItemId;
    }

    public void setOriginalItemId(Integer originalItemId) {
        this.originalItemId = originalItemId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public static Record getInstance(User user, Item item, Integer quantity){
        Record record = new Record();
        BigInteger quantityBigInt = BigInteger.valueOf(quantity);
        record.setItemName(item.getItemName());
        record.setprice(item.getPrice().multiply(quantityBigInt));
        record.setQuantity(quantity);
        record.setTransactionTime(new Date(System.currentTimeMillis()));
        record.setBuyerId(user.getId());
        record.setOriginalItemId(item.getId());

        return record;
    }
}
