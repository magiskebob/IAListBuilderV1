package com.example.kongsgaard.ialistbuilderv1;

import java.util.List;

/**
 * Created by magiskebob on 11-02-2017.
 */

public class ArmyList {
    public int _id;
    public String Name;
    public List<CardClass> armylist;
    public int TotalCost;

    public ArmyList(){

    }
    public ArmyList(List<CardClass> list, String name, int cost){
        this.Name = name;
        this.armylist = list;
        this.TotalCost = cost;
    }
    public ArmyList(String name, int cost){
        this.Name = name;
        this.TotalCost = cost;
    }


    public List<CardClass> getArmylist() {
        return armylist;
    }

    public void setArmylist(List<CardClass> armylist) {
        this.armylist = armylist;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
