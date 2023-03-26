package ru.ievseev.products.cards;

import ru.ievseev.products.AbstractCard;

import java.util.Currency;

public class CurrencyDebitCard extends AbstractCard {

    public CurrencyDebitCard(Currency currency) {
        super("Currency Debit Card", currency);
    }
}
