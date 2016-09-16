package com.mprtcz.webshop.model.itemmodel;

import com.mprtcz.webshop.model.usermodel.User;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "item4")
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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ItemRecord)) return false;

		ItemRecord that = (ItemRecord) o;

		if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;
		if (transactionTime != null ? !transactionTime.equals(that.transactionTime) : that.transactionTime != null)
			return false;
		return value != null ? value.equals(that.value) : that.value == null;

	}

	@Override
	public int hashCode() {
		int result = pk != null ? pk.hashCode() : 0;
		result = 31 * result + (transactionTime != null ? transactionTime.hashCode() : 0);
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}
}