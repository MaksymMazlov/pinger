package ua.pinger.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "pinger")
public class Account implements UserDetails
{
    private int id;
    private String email;
    private String password;
    private Timestamp created;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.singletonList(new SimpleGrantedAuthority("user"));
    }

    @Basic
    @Column(name = "password")
    public String getPassword()
    {
        return password;
    }

    @Override
    @Transient
    public String getUsername()
    {
        return email;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled()
    {
        return true;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated()
    {
        return created;
    }

    public void setCreated(Timestamp created)
    {
        this.created = created;
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
        Account account = (Account) o;
        return id == account.id && Objects.equals(email, account.email) && Objects.equals(password, account.password) && Objects.equals(created, account.created);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, email, password, created);
    }
}
