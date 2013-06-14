package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = Customer.getAccountsByUser,
            query = "select a from Customer c join c.accounts a where c.username = :user"),
    @NamedQuery(name = Customer.getTransactionsByUser,
            query = "select t from Transaction t where t.debit in "
            + "(select a.iban from Customer c join c.accounts a where c.username = :user)")
})
public class Customer implements Serializable {

    public static final String getAccountsByUser = "Customer.findByUser";
    public static final String getTransactionsByUser = "Customer.getTransactionsByUser";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    private String address;
    private String pin;
    private String username;
    @OneToMany
    @JoinColumn(name = "customer_id")
    private Set<Account> accounts = new HashSet<>();
    @Version
    private Integer version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
