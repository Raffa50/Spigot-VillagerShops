package aldrigos.mc.villagershops.data;

import org.bukkit.Location;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.Collection;

public class Shop {
    private String id;
    private Collection<Trade> trades = new ArrayList<>();

    public Location pos;
    public String name;
    public Villager.Profession type;

    Shop(){}

    public Shop(String id, String name, Location pos, Villager.Profession type){
        this.id = id;
        this.name = name;
        this.type = type;
        this.pos = pos;
    }

    public String getId(){ return id; }

    public Collection<Trade> getTrades(){ return trades; }
}
