package aldrigos.mc.villagershops.data;

import org.bukkit.Material;
import org.bukkit.inventory.*;

public class Trade {
    public short RequestQty = 1, ItemQty = 1;
    public Material Request = Material.EMERALD, Item;

    public void setResult(Material mat, short qty){
        this.Item = mat;
        this.ItemQty = qty;
    }

    public void setIngredient(Material mat, short qty){
        this.Request = mat;
        this.RequestQty = qty;
    }

    public MerchantRecipe getRecipe(){
        var r= new MerchantRecipe(new ItemStack(Item, ItemQty), 999999);
        r.addIngredient(new ItemStack(Request, RequestQty));
        return r;
    }
}
