package com.mprtcz.webshop.model.usermodel;

import com.mprtcz.webshop.model.itemmodel.Item;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Azet on 2016-08-27.
 */
@Entity
@Table(name="APP_USER")
public abstract class User implements Serializable {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name="SSO_ID", unique=true, nullable=false)
    private String ssoId;

    @NotEmpty
    @Column(name="PASSWORD", nullable=false)
    private String password;

    @NotEmpty
    @Column(name="FIRST_NAME", nullable=false)
    private String firstName;

    @NotEmpty
    @Column(name="LAST_NAME", nullable=false)
    private String lastName;

    @NotEmpty
    @Column(name="EMAIL", nullable=false)
    private String email;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>(); //set bcuz an user can have every role (admin and customer and...)

    private List<Item> orderedItems = new ArrayList<>(); //TODO hook up table

    private List<Item> purchaseHistory = new ArrayList<>(); // TODO hook up table

    public Integer getId() {
        return id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public List<Item> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void setPurchaseHistory(List<Item> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof User))
            return false;
        if (this == o) return true;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return ssoId != null ? ssoId.equals(user.ssoId) : user.ssoId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ssoId != null ? ssoId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ssoId='" + ssoId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userProfiles=" + userProfiles +
                ", orderedItems=" + orderedItems +
                ", purchaseHistory=" + purchaseHistory +
                '}';
    }
}
