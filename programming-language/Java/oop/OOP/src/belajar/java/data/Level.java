package belajar.java.data;

import belajar.java.annotation.Fancy;

@Fancy(name = "Level", tags = {"application", "java"})
public enum Level {
    STANDARD("Standard Level"),
    PREMIUM("Premium Level"),
    VIP("Vip Level"),;

    private String description;

    Level(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
