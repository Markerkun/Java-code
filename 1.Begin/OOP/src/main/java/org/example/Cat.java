package org.example;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Cat extends Animal {

    private boolean indoorOnly;

    public Cat() {
        super("Cat",2);
        this.indoorOnly = false;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str+="indoor: "+indoorOnly;
        return str;
    }

    public Cat(String name, int age, boolean indoor)
    {
        super(name, age);
        this.indoorOnly = indoor;
    }


}
