package system.services;

public abstract class Services {
    protected String name;
    protected double discount;
    private String provider;
    public abstract double getDiscount();
    public abstract void updateDiscount(double d);

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
