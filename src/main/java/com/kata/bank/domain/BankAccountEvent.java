package com.kata.bank.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.UUID;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BankAccountEvent {

    @NonNull
    UUID id;
    @NonNull
    EventType eventType;
    @NonNull
    Amount amount;
    @NonNull
    ZonedDateTime occurredAt;

    public static BankAccountEvent depositCash(Amount amount) {
        return new BankAccountEvent(UUID.randomUUID(), EventType.DEPOSIT, amount, ZonedDateTime.now());
    }

    public static BankAccountEvent withdrawCash(Amount amount) {
        return new BankAccountEvent(UUID.randomUUID(), EventType.WITHDRAW, amount, ZonedDateTime.now());
    }
}
