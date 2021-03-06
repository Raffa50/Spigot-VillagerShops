package aldrigos.mc.villagershops;

import aldrigos.mc.villagershops.data.*;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.stream.Collectors;

public class ShopsManager {
    private Map<UUID, Shop> shops = new HashMap<>();
    private transient VillagerShopsPlugin p;

    public Collection<Shop> getShops(){ return shops.values(); }

    public UUID createShop(String id, String name, Location pos, Villager.Profession type){
        return createShop(new Shop(id, name, pos, type));
    }

    public UUID createShop(Shop shop){
        var vshop = (Villager) shop.getLocation().getWorld()
                .spawnEntity(shop.getLocation(), EntityType.VILLAGER);
        setShop(vshop, shop);
        shops.put(vshop.getUniqueId(), shop);
        return vshop.getUniqueId();
    }

    private void setShop(Villager v, Shop s){
        v.setProfession(s.type);
        v.setCustomName(s.name);
        v.setAI(false);

        v.getPersistentDataContainer().set(new NamespacedKey(p, "shopid"), PersistentDataType.STRING, s.getId());

        var m = (Merchant) v;
        m.setRecipes(
            s.getTrades().stream()
                .map(Trade::getRecipe).collect(Collectors.toList())
        );
    }

    public void deleteShop(String id){
        for(var k: shops.keySet()) {
            if (shops.get(k).getId().equals(id)) {
                p.getServer().getEntity(k).remove();
                shops.remove(k);
                break;
            }
        }
    }

    public Shop getById(String id){
        for(var s: shops.values())
            if(s.getId().equals(id))
                return s;

        return null;
    }

    public boolean existShopId(String id){
        return getById(id) != null;
    }

    public boolean isShop(UUID id){ return shops.containsKey(id); }

    void load(VillagerShopsPlugin p){
        this.p = p;

        for(UUID id: shops.keySet()) {
            var e = (Villager) p.getServer().getEntity(id);
            if(e != null)
                setShop(e, shops.get(id));
            else{
                shops.remove(id);
                createShop(shops.get(id));
            }
        }
    }

    private UUID getShopUID(String id){
        for(var k: shops.keySet())
            if(shops.get(k).getId().equals(id))
                return k;

        return null;
    }

    public void addTrade(String id, Trade trade) {
        var entityId = getShopUID(id);
        if(entityId == null)
            return;

        var shop = shops.get(entityId);
        shop.getTrades().add(trade);
        setShop((Villager) p.getServer().getEntity(entityId), shop);
    }

    public void removeTrade(String id, int num){
        var entityId = getShopUID(id);
        if(entityId == null)
            return;

        var shop = shops.get(entityId);
        shop.getTrades().remove(num);
        setShop((Villager) p.getServer().getEntity(entityId), shop);
    }
}
