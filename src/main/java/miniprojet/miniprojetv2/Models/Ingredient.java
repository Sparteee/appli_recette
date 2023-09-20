package miniprojet.miniprojetv2.Models;

import java.util.*;

public class Ingredient {
    private String name;
    private float amount;
    private String unit;


    public Ingredient(String name, float amount, String unit){
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", unit='" + unit + '\'';
    }
}
