package ru.ievseev.products.cards;

import ru.ievseev.products.AbstractCard;

import static ru.ievseev.currencies.Currencies.RUB;

public class DebitCard extends AbstractCard {

    public DebitCard() {
        super("Debit Card", RUB);
    }
}
