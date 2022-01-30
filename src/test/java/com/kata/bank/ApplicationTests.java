package com.kata.bank;

import com.kata.bank.domain.Amount;
import com.kata.bank.domain.BankAccount;
import com.kata.bank.domain.BankAccountEvent;
import com.kata.bank.exception.BankAccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApplicationTests {

	@Test
	void itShouldAddMoneyToBankAccount() {
		// Given
		BankAccount underTest = BankAccount.builder().amount(Amount.ZERO).build();
		BankAccountEvent depositCash = BankAccountEvent.depositCash(Amount.of(100, 20));

		// When
		underTest.addBankEvent(depositCash);

		// Then
		assertThat(Amount.of(100,20)).isEqualTo(underTest.getAmount());
	}

	@Test
	void itShouldWithdrawMoneyToBankAccount() {
		// Given
		BankAccount underTest = BankAccount.builder().bankAccountEvents(new ArrayList<>()).amount(Amount.of(500,10)).build();
		BankAccountEvent withdrawCash = BankAccountEvent.withdrawCash(Amount.of(100, 20));

		// When
		underTest.addBankEvent(withdrawCash);

		//Then
		Amount expected = Amount.of(399,90);
		assertThat(underTest.getAmount()).isEqualTo(expected);
	}


	@Test
	void itShouldReturnExceptionFromBankAccount() {
		// Given
		BankAccount underTest = BankAccount.builder()
				.bankAccountEvents(new ArrayList<>()).amount(Amount.of(0,10))
				.build();
		BankAccountEvent withdrawCash = BankAccountEvent.withdrawCash(Amount.of(100, 0));

		// When
		BankAccountException thrown = Assertions
				.assertThrows(BankAccountException.class, () -> {
					underTest.addBankEvent(withdrawCash);
				}, "Not enough money");

		//Then
		Assertions.assertEquals("Sorry not enough money", thrown.getMessage());
	}

	@Test
	void itShouldReturnBankAccountHistory() {
		// Given
		BankAccountEvent depositCash = BankAccountEvent.depositCash(Amount.of(100, 20));
		BankAccountEvent withdrawCash = BankAccountEvent.withdrawCash(Amount.of(50, 0));
		BankAccount underTest = BankAccount.builder().bankAccountEvents(Arrays.asList(depositCash,withdrawCash)).amount(Amount.ZERO).build();

		// Then
		assertThat(underTest.getBankAccountEvents().size()).isEqualTo(2);
		underTest.getBankAccountEvents().forEach(System.out::println);

	}



}
