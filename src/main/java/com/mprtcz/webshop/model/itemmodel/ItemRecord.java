package com.mprtcz.webshop.model.itemmodel;

import com.mprtcz.webshop.model.usermodel.User;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "item")
@AssociationOverrides({
		@AssociationOverride(name = "pk.item", joinColumns = @JoinColumn(name = "ITEM_ID")),
		@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "USER_ID")) })
public class ItemRecord implements java.io.Serializable {

	private ItemRecordId pk = new ItemRecordId();

	private Date transactionTime;

	private BigInteger value;

	public ItemRecord() {
	}

	@EmbeddedId
	public ItemRecordId getPk() {
		return pk;
	}

	public void setPk(ItemRecordId pk) {
		this.pk = pk;
	}

	@Transient
	public Item getItem() {
		return getPk().getItem();
	}

	public void setItem(Item Item) {
		getPk().setItem(Item);
	}

	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User User) {
		getPk().setUser(User);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRANSACTION_TIME", nullable = false, length = 10)
	public Date getTransactionTime() {
		return this.transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	@Column(name = "ITEM_PRICE", nullable = false, length = 10)
	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}
}