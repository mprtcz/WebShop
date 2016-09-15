package com.mprtcz.webshop.model.itemmodel;

import com.mprtcz.webshop.model.usermodel.User;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemRecordId implements java.io.Serializable {


	private Item item;

    private User user;

	public ItemRecordId(Item item, User user) {
		this.item = item;
		this.user = user;
	}

	public ItemRecordId() {
	}

	@ManyToOne
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

    @ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ItemRecordId)) return false;

		ItemRecordId that = (ItemRecordId) o;

		if (item != null ? !item.equals(that.item) : that.item != null) return false;
		return user != null ? user.equals(that.user) : that.user == null;

	}

	@Override
	public int hashCode() {
		int result = item != null ? item.hashCode() : 0;
		result = 31 * result + (user != null ? user.hashCode() : 0);
		return result;
	}
}