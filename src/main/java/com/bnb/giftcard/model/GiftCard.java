package com.bnb.giftcard.model;

import com.bnb.giftcard.exception.IllegalFieldValuesException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
public class GiftCard {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    @NotNull
    @Min(1)
    private long cardNumber;
    private LocalDateTime activationTime;
    @DecimalMax("50.00")
    @DecimalMin("0.00")
    @NotNull
    private BigDecimal initialBalance;
    private BigDecimal remainingBalance;
    private String phoneNumber;
    //If we don't cascade the purchases, we get an error when we try to save a GiftCard.
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Purchase> purchases;
    @ManyToOne
    private Customer customer;

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(LocalDateTime activationTime) {
        this.activationTime = activationTime;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        if (remainingBalance.compareTo(new BigDecimal("00.00")) < 0) {
            throw new IllegalFieldValuesException(
                    "Cannot process purchase: The selected gift card does not have a high enough balance for this purchase");
        }
        this.remainingBalance = remainingBalance;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    @Override
    public String toString() {
        return "GiftCard{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", activationTime=" + activationTime +
                ", initialBalance=" + initialBalance +
                ", remainingBalance=" + remainingBalance +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    //Utility method to make sure that both sides of the bi-directional relationship are updated.
    public void associatePurchaseWithGiftCard(Purchase purchase) {
        purchases.add(purchase);
        purchase.setGiftCard(this);
    }
}
