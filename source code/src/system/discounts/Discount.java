package system.discounts;

import system.services.Services;

import java.util.Vector;


// Discount class act as the subject class who the services are interested in its update
public abstract class Discount {

    // a vector holding the registered services (observers) to be notified when a discount is added
    protected Vector<Services> services = new Vector <Services>();

    // adding services to be notified
    public void registerService(Services service) {
        services.add(service);
    }

    // a function that will notify the registered services with the updated discount
    public abstract boolean setDiscount(double amount);

    // another function that will notify the registered services by removing the discount
    public abstract void removeDiscount();
}
