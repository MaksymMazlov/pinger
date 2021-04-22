package ua.pinger.domain.enumeration;

public enum PlanName
{
    FREE(1),
    BASIC(2),
    STANDARD(3),
    PREMIUM(4);

    private final int id;

    PlanName(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
}
