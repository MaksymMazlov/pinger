package ua.pinger.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account_settings", schema = "pinger")
public class AccountSettings
{
    private int id;
    private String key;
    private String value;
    private int accountId;

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

    @Basic
    @Column(name = "key")
    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    @Basic
    @Column(name = "value")
    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Basic
    @Column(name = "account_id")
    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
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
