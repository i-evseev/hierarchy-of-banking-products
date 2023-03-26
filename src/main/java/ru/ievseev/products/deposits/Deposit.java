package ru.ievseev.products.deposits;

import ru.ievseev.products.AbstractDeposit;

import static ru.ievseev.currencies.Currencies.RUB;

public class Deposit extends AbstractDeposit {

    public Deposit() {
        super("Ordinary Deposit", RUB);
    }
}
