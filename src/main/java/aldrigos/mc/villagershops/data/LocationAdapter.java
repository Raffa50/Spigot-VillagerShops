package aldrigos.mc.villagershops.data;

import aldrigos.mc.villagershops.VillagerShopsPlugin;
import org.bukkit.Location;

import java.util.UUID;

public class LocationAdapter {
    public double x, y, z;
    public float yaw, pitch;
    public UUID worldId;

    public LocationAdapter(){}

    public LocationAdapter(Location loc){
        x = loc.getX();
        y = loc.getY();
        z = loc.getZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();
    }

    public Location toLocation(){
        return new Location(VillagerShopsPlugin.server.getWorld(worldId), x, y, z, yaw, pitch);
    }
}
