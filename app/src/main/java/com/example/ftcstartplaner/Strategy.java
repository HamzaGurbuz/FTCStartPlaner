package com.example.ftcstartplaner;

import java.io.Serializable;

public class Strategy implements Serializable {
    public String name;
    public String description;
    public String alliance;
    public String startPosition;
    public String endGoal;
    public String drawingPath;  // Çizimin bitmap dosya yolu (kayıt ettiğin yer)

    public Strategy(String name, String description, String alliance, String startPosition, String endGoal, String drawingPath) {
        this.name = name;
        this.description = description;
        this.alliance = alliance;
        this.startPosition = startPosition;
        this.endGoal = endGoal;
        this.drawingPath = drawingPath;
    }
}
