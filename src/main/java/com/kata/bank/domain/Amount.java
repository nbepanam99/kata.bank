package com.kata.bank.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Amount {

    public static double maxValue() {
        return 9E13;
    }

    public static double minValue() {
        return -maxValue();
    }

    public static final Amount ZERO = new Amount(0, 0);

    private long cents;

    private Amount(final int euros, final int cents) {
        final double centsAsDouble = 100.0 * euros + cents;
        this.cents = Math.round(centsAsDouble);
    }

    public Amount(final double euros) {
        if (Double.isNaN(euros) || euros < minValue() || euros > maxValue()) {
            throw new IllegalArgumentException();
        }
        cents = Math.round(euros * 100d);
    }

    public static Amount of(int euros, int cents) {
        return new Amount(euros, cents);
    }

    public Amount plus(final Amount other) {
        final double doubleResult = this.toDouble() + other.toDouble();
        return new Amount(doubleResult);
    }

    public int compareTo(final Amount other) {
        return Long.compare(cents, other.cents);
    }

    public Amount minus(final Amount other) {
        final double result = this.toDouble() - other.toDouble();
        return new Amount(result);
    }

    public double toDouble() {
        return cents / 100.0;
    }

    public String toString() {
        return String.format("%.2f", toDouble());
    }

    @SuppressWarnings("unused")
    private Amount() {
    }

    public long getCents() {
        return cents;
    }
}
