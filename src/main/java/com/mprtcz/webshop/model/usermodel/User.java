package com.mprtcz.webshop.model.usermodel;

import com.mprtcz.webshop.model.itemmodel.Item;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-08-27.
 */
@Entity
@Table(name="APP_USER")
public class User implements Serializable {

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

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private
    UserProfile userProfile; //set bcuz an user can have every role (admin and customer and...)


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_ITEM",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ITEM_ID") })
    private List<Item> boughtItemsList = new ArrayList<>();


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

    public UserProfile getUserProfile() {
        return userProfile;
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

    public void setUserProfile (UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<Item> getBoughtItemsList() {
        return boughtItemsList;
    }

    public void setBoughtItemsList(List<Item> boughtItemsList) {
        this.boughtItemsList = boughtItemsList;
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
                ", userProfile=" + userProfile +
                '}';
    }
}
