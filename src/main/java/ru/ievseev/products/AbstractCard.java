package ru.ievseev.products;

import java.math.BigDecimal;
import java.util.Currency;

public abstract class AbstractCard implements Card {

    String name;
    Currency currency;
    BigDecimal balance;

    public AbstractCard(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void replenishment() {

    }

    @Override
    public void writeoff() {

    }

    @Override
    public void balanceInquiry() {

    }
}
