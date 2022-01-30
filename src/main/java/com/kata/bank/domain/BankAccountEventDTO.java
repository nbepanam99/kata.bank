package com.kata.bank.domain;

import lombok.Data;

@Data
public class BankAccountEventDTO {

    Long BankAccountId;
    String eventType;
    Amount amount;

}
