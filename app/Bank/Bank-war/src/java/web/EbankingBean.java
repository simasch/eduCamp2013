package web;

import business.AccountManager;
import business.TransactionManager;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Account;
import model.Transaction;

@Named(value = "ebankingBean")
@SessionScoped
public class EbankingBean implements Serializable {

    private List<Account> accounts = new ArrayList<Account>();
    private List<Transaction> transactions = new ArrayList<Transaction>();
    private String from;
    private String to;
    private BigDecimal amount;
    @Inject
    private AccountManager accountManager;
    @Inject
    private TransactionManager transactionManager;
    private List<String> ibans = new ArrayList<String>();

    @PostConstruct
    public void init() {
        accounts = accountManager.getAccounts();
        for (Account a : accounts) {
            ibans.add(a.getIban());
        }
    }

    public String showTransactions(Account account) {
        transactions = transactionManager.getTransactions();
        from = account.getIban();
        return "/ebanking/transactions.xhtml";
    }

    public String send() {
        transactionManager.transfer(from, from, amount);
        return "/ebanking/accounts.xhtml";
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<String> getAccountIbans() {
        return ibans;
    }
}
