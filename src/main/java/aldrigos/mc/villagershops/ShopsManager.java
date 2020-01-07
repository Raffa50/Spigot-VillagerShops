package aldrigos.mc.villagershops;

import aldrigos.mc.villagershops.data.*;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopsManager {
    private Map<UUID, Shop> shops = new HashMap<>();
    private transient VillagerShopsPlugin p;

    public UUID createShop(String id, String name, Location pos, Villager.Profession type){
        return createShop(new Shop(id, name, pos, type));
    }

    public UUID createShop(Shop shop){
        var vshop = (Villager) shop.pos.getWorld()
                .spawnEntity(shop.pos.add(0,1,0), EntityType.VILLAGER);
        setShop(vshop, shop);
        shops.put(vshop.getUniqueId(), shop);
        return vshop.getUniqueId();
    }

    private void setShop(Villager v, Shop s){
        v.setProfession(s.type);
        v.setCustomName(s.name);

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

    public String getShopIds(){
        var sb = new StringBuilder();
        for(var s: shops.values())
            sb.append(s.getId()).append(", ");

        if(sb.length() > 2)
            sb.deleteCharAt(sb.length() -2);

        return sb.toString();
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
        var vshop = (Merchant) p.getServer().getEntity(entityId);
        vshop.getRecipes().add(trade.getRecipe());
    }

    public void removeTrade(String id, int num){
        var entityId = getShopUID(id);
        if(entityId == null)
            return;

        var shop = shops.get(entityId);
        var vshop = (Merchant) p.getServer().getEntity(entityId);
        vshop.getRecipes().remove(num);
    }
}
