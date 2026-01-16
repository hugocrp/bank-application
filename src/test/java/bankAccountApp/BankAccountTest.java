package bankAccountApp;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {

	private BankAccount account;
	private Person holder;

	@Before
	public void setUp() throws Exception {
		holder = new Person("John Doe", 'M', 30, 75.5f, 180.0f, "black", "brown", "john@example.com");
		account = new BankAccount(100.0, 50.0, "2026-01-09", holder);
	}


	@Test
	public void testDepositMoney_ValidAmount() {
		double depositAmount = 50.0;

		account.depositMoney(depositAmount);

		assertEquals(150.0, account.getBalance(), 0.01);
	}

	@Test
	public void testDepositMoney_NegativeAmount() {

		double depositAmount = -50.0;

		account.depositMoney(depositAmount);

		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_ValidAmount() {
		double withdrawAmount = 30.0;

		boolean success = account.withdrawMoney(withdrawAmount);

		assertTrue(success);
		assertEquals(70.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_InsufficientFunds() {
		double withdrawAmount = 150.0;
		boolean success = account.withdrawMoney(withdrawAmount);

		assertFalse(success);
		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testDepositMoney_ZeroAmount() {
		account.depositMoney(0.0);
		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testDepositMoney_VeryLargeAmount() {
		account.depositMoney(Double.MAX_VALUE);
		assertTrue(account.getBalance() > 100.0);
	}

	@Test
	public void testDepositMoney_InfinityValue() {
		double initialBalance = account.getBalance();
		account.depositMoney(Double.POSITIVE_INFINITY);
		assertFalse(Double.isInfinite(account.getBalance()));
	}

	@Test
	public void testDepositMoney_NaNValue() {
		double initialBalance = account.getBalance();
		account.depositMoney(Double.NaN);
		assertFalse(Double.isNaN(account.getBalance()));
	}

	@Test
	public void testDepositMoney_MultipleDeposits() {
		account.depositMoney(25.0);
		account.depositMoney(25.0);
		account.depositMoney(50.0);
		assertEquals(200.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_ExactlyWithdrawLimit() {
		boolean success = account.withdrawMoney(50.0);
		assertTrue("Devrait pouvoir retirer exactement la limite de retrait", success);
		assertEquals(50.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_ZeroAmount() {
		boolean success = account.withdrawMoney(0.0);
		assertTrue(success);
		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_NegativeAmount() {
		boolean success = account.withdrawMoney(-50.0);
		assertFalse(success);
		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_ExactBalance() {
		boolean success = account.withdrawMoney(49.0);
		assertTrue(success);
		assertEquals(51.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_MultipleWithdrawalsAccumulation() {
		boolean success1 = account.withdrawMoney(25.0);
		assertTrue("Premier retrait devrait réussir", success1);
		assertEquals(75.0, account.getBalance(), 0.01);

		boolean success2 = account.withdrawMoney(25.0);
		assertTrue("Deuxième retrait devrait réussir (50 total < 50 limite)", success2);
		assertEquals(50.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_ExceedsAccumulatedLimit() {
		account.withdrawMoney(30.0);
		assertEquals(70.0, account.getBalance(), 0.01);

		boolean success = account.withdrawMoney(25.0);
		assertFalse("Ne devrait pas pouvoir dépasser la limite cumulée", success);
		assertEquals(70.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_InfinityValue() {
		boolean success = account.withdrawMoney(Double.POSITIVE_INFINITY);
		assertFalse(success);
		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_NaNValue() {
		boolean success = account.withdrawMoney(Double.NaN);
		assertFalse(success);
		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_JustBelowLimit() {
		boolean success = account.withdrawMoney(49.99);
		assertTrue("Devrait pouvoir retirer 49.99 avec une limite de 50", success);
		assertEquals(50.01, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawMoney_JustAboveLimit() {
		// Test de retrait juste au-dessus de la limite
		boolean success = account.withdrawMoney(50.01);
		assertFalse("Ne devrait pas pouvoir retirer 50.01 avec une limite de 50", success);
		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testCombinedOperations_DepositThenWithdraw() {
		account.depositMoney(100.0);
		assertEquals(200.0, account.getBalance(), 0.01);

		boolean success = account.withdrawMoney(50.0);
		assertTrue(success);
		assertEquals(150.0, account.getBalance(), 0.01);
	}

	@Test
	public void testAmountWithdrawnTracking() {
		account.withdrawMoney(20.0);
		assertEquals(20.0, account.getAmountWithdrawn(), 0.01);

		account.withdrawMoney(15.0);
		assertEquals(35.0, account.getAmountWithdrawn(), 0.01);
	}

}

