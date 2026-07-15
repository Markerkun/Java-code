package org.example;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Dog extends Animal {

    private int commandsAmount;

    public Dog() {
        super("Dog",2);
        this.commandsAmount = 2;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str+="Amount of commands: "+commandsAmount;
        return str;
    }


}
