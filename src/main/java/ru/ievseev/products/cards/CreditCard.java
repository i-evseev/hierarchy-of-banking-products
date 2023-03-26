package ru.ievseev.products.cards;

import ru.ievseev.products.AbstractCard;

import java.math.BigDecimal;

import static ru.ievseev.currencies.Currencies.RUB;

public class CreditCard extends AbstractCard {

    BigDecimal interestRate;

    public CreditCard(BigDecimal interestRate) {
        super("Credit Card", RUB);

        this.interestRate = interestRate;
    }

    public BigDecimal requestDebt() {
        return BigDecimal.ZERO;
    }

}
