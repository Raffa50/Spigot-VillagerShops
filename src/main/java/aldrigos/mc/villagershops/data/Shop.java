package aldrigos.mc.villagershops.data;

import org.bukkit.Location;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String id;
    private List<Trade> trades = new ArrayList<>();

    private LocationAdapter pos;
    public String name;
    public Villager.Profession type;

    Shop(){}

    public Shop(String id, String name, Location pos, Villager.Profession type){
        this.id = id;
        this.name = name;
        this.type = type;
        this.pos = new LocationAdapter(pos);
    }

    public String getId(){ return id; }

    public List<Trade> getTrades(){ return trades; }

    public Location getLocation(){ return pos.toLocation(); }
}
