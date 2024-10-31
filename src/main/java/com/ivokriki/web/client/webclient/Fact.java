package com.ivokriki.web.client.webclient;

public class Fact {
    String fact;
    int length;

    public Fact() {}

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "fact='" + fact + '\'' +
                ", length=" + length +
                '}';
    }
}
