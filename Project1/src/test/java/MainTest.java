import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.revature.Project1.Account;
import com.revature.Project1.LoginMenu;
import com.revature.Project1.Pending;
import com.revature.Project1.Transactions;
public class MainTest {
@Test
void employees_can_view_accounts(){
	Account a = new Account();
	a.viewAccount();
}
@Test
void employees_can_view_transaction_log() {
	Transactions t = new Transactions();
	t.viewTransactions();
}
@Test
void employees_can_view_pending_users() {
	Pending p = new Pending();
	p.viewPending();
	
}
void employees_can_view_pending_bank_accounts() {
	Pending p = new Pending();
	p.viewPendingBank();
	
}
@Test
void customers_can_view_previous_transaction() {
	Transactions t = new Transactions();
	t.viewPrevious(1);
}
@Test
void customers_can_initiate_transfer() {
	Transactions t = new Transactions();
	t.transfer(1, 1, 1);
}
@Test
void customers_can_register() {
	LoginMenu l = new LoginMenu();
	l.createAccount("A", 0, "A", "A", "A");
}

}
