package com.example.kongsgaard.ialistbuilderv1;

/**
 * Created by Kongsgaard on 05-02-2017.
 */

public class CardClass {
    public int _id ;
    public String Name;
    public int PointCost;
    public int CardImage;
    public boolean Elite;



    public CardClass (String name, int pointCost, int imagePath, boolean elite)
    {
        //this._id = id;
        this.Name = name;
        this.PointCost = pointCost;
        this.CardImage = imagePath;
        this.Elite = elite;

    }
    public CardClass (int id,String name, int pointCost, int imagePath, boolean elite)
    {
        this._id = id;
        this.Name = name;
        this.PointCost = pointCost;
        this.CardImage = imagePath;
        this.Elite = elite;

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
    public int get_id() {
        return _id;
    }

    public void set_id(int ID) {
        this._id = ID;
    }
    public boolean isElite() {
        return Elite;
    }

    public void setElite(boolean elite) {
        Elite = elite;
    }


}