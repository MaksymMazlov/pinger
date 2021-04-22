package ua.pinger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.pinger.domain.enumeration.AccountSetting;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account_settings")
public class AccountSettings
{
    private int id;
    private AccountSetting key;
    private String value;
    private int accountId;
    private Account account;

    @Id
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Column(name = "key")
    @Enumerated(EnumType.STRING)
    public AccountSetting getKey()
    {
        return key;
    }

    public void setKey(AccountSetting key)
    {
        this.key = key;
    }

    @Column(name = "value")
    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Column(name = "account_id")
    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        AccountSettings that = (AccountSettings) o;
        return id == that.id && Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, key, value);
    }
}