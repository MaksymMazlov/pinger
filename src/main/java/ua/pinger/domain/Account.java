package ua.pinger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account implements UserDetails
{
    private int id;
    private String email;
    private String password;
    private Timestamp created;
    private String token;
    private List<AccountSettings> settings;
    private int tarifPlanId;
    private TarifPlan plan;

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

    @Column(name = "email", unique = true)
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

    @Column(name = "created")
    public Timestamp getCreated()
    {
        return created;
    }

    public void setCreated(Timestamp created)
    {
        this.created = created;
    }

    @Column(name = "token")
    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    public List<AccountSettings> getSettings()
    {
        return settings;
    }

    public void setSettings(List<AccountSettings> settings)
    {
        this.settings = settings;
    }

    @Column(name = "tarif_plan_id")
    public int getTarifPlanId()
    {
        return tarifPlanId;
    }

    public void setTarifPlanId(int tarifPlanId)
    {
        this.tarifPlanId = tarifPlanId;
    }

    @ManyToOne
    @JoinColumn(name = "tarif_plan_id", referencedColumnName = "id", insertable = false, updatable = false)
    public TarifPlan getPlan()
    {
        return plan;
    }

    public void setPlan(TarifPlan plan)
    {
        this.plan = plan;
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
