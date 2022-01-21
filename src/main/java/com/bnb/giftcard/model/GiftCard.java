package com.bnb.giftcard.model;

import com.bnb.giftcard.exception.IllegalFieldValuesException;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
public class GiftCard {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private long cardNumber;

    @DecimalMax(value="50.00", message="Please enter a value between $0.00 and $50.00")
    @DecimalMin(value="0.00", message="Please enter a value between $0.00 and $50.00")
    @NotNull (message = "Please enter a value between $0.00 and $50.00")
    private BigDecimal initialBalance;

    private BigDecimal remainingBalance;

    private LocalDateTime activationTime;

    private boolean active;

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

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        if (initialBalance != null) {
            //Round off any extra decimals (>2):
            this.initialBalance = initialBalance.setScale(2, RoundingMode.DOWN);
        } else {
            this.initialBalance = null;
        }
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public LocalDateTime getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(LocalDateTime activationTime) {
        this.activationTime = activationTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        if (remainingBalance.compareTo(new BigDecimal("00.00")) < 0) {
            throw new IllegalFieldValuesException(
                    "Cannot process purchase: The selected gift card does not have a high enough balance for this purchase");
        }
        this.remainingBalance = remainingBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    @Override
    public String toString() {
        return "GiftCard{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", initialBalance=" + initialBalance +
                ", remainingBalance=" + remainingBalance +
                ", activationTime=" + activationTime +
                ", active=" + active +
                ", customer=" + customer +
                '}';
    }

    //Utility method to make sure that both sides of the bi-directional relationship are updated.
    public void associatePurchaseWithGiftCard(Purchase purchase) {
        purchases.add(purchase);
        purchase.setGiftCard(this);
    }
}
