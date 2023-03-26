package ru.ievseeev.tests.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.ievseev.products.AbstractCard;
import ru.ievseev.products.Card;
import ru.ievseev.products.cards.CreditCard;
import ru.ievseev.products.cards.CurrencyDebitCard;
import ru.ievseev.products.cards.DebitCard;
import ru.ievseev.products.deposits.Deposit;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.commons.support.HierarchyTraversalMode.TOP_DOWN;
import static org.junit.platform.commons.support.ReflectionSupport.findFields;
import static org.junit.platform.commons.support.ReflectionSupport.findMethods;
import static ru.ievseev.currencies.Currencies.RUB;
import static ru.ievseev.currencies.Currencies.USD;

public class ProductsTests {

    static Stream<Arguments> creditCardMustHaveAdditionalFieldAndMethod() {
        return Stream.of(new CreditCard(BigDecimal.valueOf(3))).map(Arguments::of);
    }

    static Stream<Arguments> userCanSetCurrencyForCurrencyCard() {
        return Stream.of(
                Arguments.of(new CurrencyDebitCard(RUB), RUB),
                Arguments.of(new CurrencyDebitCard(USD), USD)
        );
    }

    static Stream<Arguments> cardsAndDepositsMustHaveProperties() {
        return Stream.of(
                new CreditCard(BigDecimal.ZERO),
                new CurrencyDebitCard(USD),
                new DebitCard(),
                new Deposit()
        ).map(Arguments::of);
    }

    static Stream<Arguments> cardsMustHaveMethods() {
        return Stream.of(
                new CreditCard(BigDecimal.ZERO),
                new CurrencyDebitCard(USD),
                new DebitCard()
        ).map(Arguments::of);
    }

    static Stream<Arguments> depositsMustHaveMethods() {
        return Stream.of(new Deposit())
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Кредитная карта должна иметь дололнительный метод запроса задолженности " +
            "и дополнительный параметр процентной ставки")
    void creditCardMustHaveAdditionalFieldAndMethod(Card card) {
        assertAll(() -> assertDoesNotThrow(() -> card.getClass().getDeclaredField("interestRate"),
                        "Отсутствует поле"),
                () -> assertDoesNotThrow(() -> card.getClass().getDeclaredMethod("requestDebt"),
                        "Отсутствует метод"));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("При инициализации можно указать валюту для валютной карты")
    void userCanSetCurrencyForCurrencyCard(AbstractCard card, Currency expected) {
        assertEquals(card.getCurrency(), expected);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Карты и вклады имеют параметры: валюта, баланс и название")
    void cardsAndDepositsMustHaveProperties(Object product) {
        assertAll("Карты и вклады должны содержать обязательные параметры",
                () -> assertFalse(findFields(product.getClass(), field -> field.getName().equals("currency"), TOP_DOWN).isEmpty(),
                        "Отсутствует поле валюты"),
                () -> assertFalse(findFields(product.getClass(), field -> field.getName().equals("name"), TOP_DOWN).isEmpty(),
                        "Отсутствует поле названия"),
                () -> assertFalse(findFields(product.getClass(), field -> field.getName().equals("balance"), TOP_DOWN).isEmpty(),
                        "Отсутствует поле баланса")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Карты содержат методы пополнения, списания, запроса баланса")
    void cardsMustHaveMethods(Object product) {
        assertAll("Карты должны содержать обязательные методы",
                () -> assertFalse(findMethods(product.getClass(), method -> method.getName().equals("replenishment"), TOP_DOWN).isEmpty(),
                        "Отсутствует метод пополнения"),
                () -> assertFalse(findMethods(product.getClass(), method -> method.getName().equals("writeoff"), TOP_DOWN).isEmpty(),
                        "Отсутствует метод списания"),
                () -> assertFalse(findMethods(product.getClass(), method -> method.getName().equals("balanceInquiry"), TOP_DOWN).isEmpty(),
                        "Отсутствует метод запроса баланса")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Вклады содержат методы пополнения, запроса баланса и закрытия")
    void depositsMustHaveMethods(Object product) {
        assertAll("Вклады должны содержать обязательные методы",
                () -> assertFalse(findMethods(product.getClass(), method -> method.getName().equals("replenishment"), TOP_DOWN).isEmpty(),
                        "Отсутствует метод пополнения"),
                () -> assertFalse(findMethods(product.getClass(), method -> method.getName().equals("close"), TOP_DOWN).isEmpty(),
                        "Отсутствует метод закрытия"),
                () -> assertFalse(findMethods(product.getClass(), method -> method.getName().equals("balanceInquiry"), TOP_DOWN).isEmpty(),
                        "Отсутствует метод запроса баланса")
        );
    }
}
