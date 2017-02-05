package com.example.kongsgaard.ialistbuilderv1;

/**
 * Created by Kongsgaard on 05-02-2017.
 */

public class CardClass {
    public int ID ;
    public String Name;
    public int PointCost;
    public int CardImage;

    public CardClass (String name, int pointCost, int imagePath)
    {
        this.Name = name;
        this.PointCost = pointCost;
        this.CardImage = imagePath;
    }

    public CardClass() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public int getPointCost() {
        return PointCost;
    }

    public void setPointCost(int pointCost) {
        PointCost = pointCost;
    }
    public int getCardImage() {
        return CardImage;
    }

    public void setCardImage(int cardImage) {
        CardImage = cardImage;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}