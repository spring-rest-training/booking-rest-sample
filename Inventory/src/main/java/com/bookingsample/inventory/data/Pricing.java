package com.bookingsample.inventory.data;

import javax.persistence.*;

/**
 * Created by davut on 12.03.2017.
 */
@Entity(name = "pricings")
public class Pricing {

    private Pricing()
    {

    }

    public Pricing(PricingModel model)
    {
        setPricingModel(model);
    }


    long id;
    double pricingGuest3;
    PricingModel pricingModel;
    double pricingGuest1;
    double pricingGuest2;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public PricingModel getPricingModel() {
        return pricingModel;
    }

    public void setPricingModel(PricingModel pricingModel) {
        this.pricingModel = pricingModel;
    }
    @Column(name = "pricing_guest_1" , nullable = false)
    public double getPricingGuest1() {
        return pricingGuest1;
    }

    public void setPricingGuest1(double pricingGuest1) {
        this.pricingGuest1 = pricingGuest1;
    }
    @Column(name = "pricing_guest_2")
    public double getPricingGuest2() {
        return pricingGuest2;
    }

    public void setPricingGuest2(double pricingGuest2) {
        this.pricingGuest2 = pricingGuest2;
    }
    @Column(name = "pricing_guest_3")
    public double getPricingGuest3() {
        return pricingGuest3;
    }

    public void setPricingGuest3(double pricingGuest3) {
        this.pricingGuest3 = pricingGuest3;
    }


}
