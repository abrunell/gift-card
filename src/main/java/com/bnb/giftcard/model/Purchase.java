package com.bnb.giftcard.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
@Table
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime purchaseDate;

    private BigDecimal amount;

    @ManyToOne
    private GiftCard giftCard;

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime dateTime) {
        this.purchaseDate = dateTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount != null) {
            //Round off any extra decimals (>2):
            this.amount = amount.setScale(2, RoundingMode.DOWN);
        } else {
            this.amount = null;
        }
    }

    public GiftCard getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(GiftCard giftCard) {
        this.giftCard = giftCard;
    }


    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", amount=" + amount +
                ", giftCard=" + giftCard +
                '}';
    }
}

