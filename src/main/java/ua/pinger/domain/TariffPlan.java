package ua.pinger.domain;

import ua.pinger.domain.enumeration.PlanName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarif_plan")
public class TariffPlan
{
    private int id;
    private PlanName name;
    private String description;
    private int resourceLimit;

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

    
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    public PlanName getName()
    {
        return name;
    }

    public void setName(PlanName name)
    {
        this.name = name;
    }

    
    @Column(name = "description")
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    
    @Column(name = "account_resource_limit")
    public int getResourceLimit()
    {
        return resourceLimit;
    }

    public void setResourceLimit(int resourceLimit)
    {
        this.resourceLimit = resourceLimit;
    }
}
