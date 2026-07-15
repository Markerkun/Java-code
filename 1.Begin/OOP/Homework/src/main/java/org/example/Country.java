package org.example;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Human {
    private String Name;
    private int Age;
    private double Weight;
    private double Height;

    public Human(){
        this.Name="Noname";
        this.Age=0;
        this.Weight=1;
        this.Height=1;
    }
}
