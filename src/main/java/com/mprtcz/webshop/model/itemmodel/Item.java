package com.mprtcz.webshop.model.itemmodel;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * Created by Azet on 2016-08-27.
 */
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name="NAME", nullable=false)
    String name;

    @NotEmpty
    @Column(name="PRICE", nullable=false)
    BigInteger price;

    @NotEmpty
    @Column(name="IMAGE_PATH", nullable=false)
    String imagePath;

    @Column(name="DESCRIPTION")
    String description;

    @NotEmpty
    @Column(name="STOCK", nullable=false)
    Integer stock;
}
