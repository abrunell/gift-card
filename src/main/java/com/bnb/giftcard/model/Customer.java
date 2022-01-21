package com.bnb.giftcard.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    @NotBlank(message="Please enter a phone number")
    private String phoneNumber;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GiftCard> giftCards;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /*
     * A phone number will always be stored as 10 sequential digits, regardless of how the app user enters
     * the phone number. Whitespaces and special characters are removed.
     */
    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9]","");
        this.phoneNumber = phoneNumber;
    }

    public Set<GiftCard> getGiftCards() {
        return giftCards;
    }

    public void setGiftCards(Set<GiftCard> giftCards) {
        this.giftCards = giftCards;
    }

    //Utility method to make sure that both sides of the bi-directional relationship are updated.
    public void associateGiftCardWithCustomer(GiftCard giftCard) {
        giftCards.add(giftCard);
        giftCard.setCustomer(this);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
