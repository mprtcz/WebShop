package com.mprtcz.webshop.model.itemmodel;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Azet on 2016-08-27.
 */
@Entity
@Table(name="ITEMS")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name="ITEM_NAME", nullable=false)
    private
    String itemName;

    @NotNull
    @Column(name="PRICE", nullable=false, precision = 2)
    private
    BigInteger price;

    @NotNull
    @Column(name="STOCK", nullable = false)
    private
    BigInteger stock;

    @NotNull
    @Column(name="SELLER_ID", nullable=false)
    private
    Integer sellerId;

    @Column(name="DESCRIPTION")
    private
    String description;

    public Integer getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public BigInteger getPrice() {
        return price;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public String getDescription() {
        return description;
    }

    public BigInteger getStock() {
        return stock;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public void setStock(BigInteger stock) {
        this.stock = stock;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        return itemName != null ? itemName.equals(item.itemName) : item.itemName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", sellerId=" + sellerId +
                ", description='" + description + '\'' +
                '}';
    }
}
