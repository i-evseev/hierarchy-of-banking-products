package ru.ievseev.products;

import java.math.BigDecimal;
import java.util.Currency;

public class AbstractDeposit implements Deposit {

    String name;
    Currency currency;
    BigDecimal balance;

    public AbstractDeposit(String name, Currency currency) {
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
    public void balanceInquiry() {

    }

    @Override
    public void close() {

    }
}
