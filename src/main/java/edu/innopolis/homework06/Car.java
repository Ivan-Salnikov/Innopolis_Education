package edu.innopolis.homework06;

public class Car {
    private String number;
    private String mark;
    private String colour;
    private int kmAge;
    private double price;



    public Car(String number, String mark, String colour, int kmAge, double price) {
        this.number = number;
        this.kmAge = kmAge;
        this.price = price;
        this.colour = colour;
        this.mark = mark;
    }

    public String getNumber() {
        return number;
    }

    public String getMark() {
        return mark;
    }

    public String getColour() {
        return colour;
    }

    public int getKmAge() {
        return kmAge;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number='" + number + '\'' +
                ", mark='" + mark + '\'' +
                ", colour='" + colour + '\'' +
                ", kmAge=" + kmAge +
                ", price=" + price +
                '}';
    }
}
