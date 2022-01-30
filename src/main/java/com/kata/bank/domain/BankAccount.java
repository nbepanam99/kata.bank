package com.kata.bank.domain;

import com.kata.bank.exception.BankAccountException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Builder
@ToString
@EqualsAndHashCode
public class BankAccount {

    private Long id;
    private String name;
    private Amount amount;
    private List<BankAccountEvent> bankAccountEvents;

    public void addBankEvent(BankAccountEvent accountEvent) throws BankAccountException {

        this.bankAccountEvents= List.of(accountEvent);

        if (accountEvent.getEventType().equals(EventType.DEPOSIT)){
            this.amount = this.amount.plus(accountEvent.getAmount());
        } else if (accountEvent.getEventType().equals(EventType.WITHDRAW)) {
            if (accountEvent.getAmount().getCents()>this.amount.getCents()){
                throw new BankAccountException("Sorry not enough money");
            } else {
                this.amount = this.amount.minus(accountEvent.getAmount());
            }
        } else {
            throw new BankAccountException("This is not Deposit or Withdraw operation");
        }

    }

    public String getName() {
        return name;
    }

    public Amount getAmount() {
        return amount;
    }

    public List<BankAccountEvent> getBankAccountEvents(){
        return bankAccountEvents;
    }

}
