package aldrigos.mc.villagershops.commands;

import aldrigos.mc.villagershops.*;
import aldrigos.mc.villagershops.data.Trade;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;

public class AddTradeCommand implements SubCommand {
    private final ShopsManager manager;

    public AddTradeCommand(ShopsManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean execute(CommandSender sender, LinkedList<String> args) {
        if(args.size() < 5){
            Messages.PARAM_MISS.send(sender, "/vshop add <shopid> <requestedQty> <requestedItem> <resultQty> <result>");
            return false;
        }

        if(!sender.hasPermission("vshop.admin.edit")){
            Messages.NO_PERM.send(sender, "vshop.admin.edit");
            return false;
        }

        if(!manager.existShopId(args.get(0))){
            Messages.SHOP_NOT_EXIST.send(sender, args.get(0));
            return false;
        }

        Material request, result;
        short requestQty, resultQty;

        try{
            requestQty = Short.parseShort(args.get(1));
        } catch(Exception ex){
            Messages.PARAM_INVALID.send(sender, args.get(1));
            return false;
        }

        try{
            request = Material.valueOf(args.get(2).toUpperCase());
        } catch(Exception ex){
            Messages.PARAM_INVALID.send(sender, args.get(2));
            return false;
        }

        try{
            resultQty = Short.parseShort(args.get(3));
        } catch(Exception ex){
            Messages.PARAM_INVALID.send(sender, args.get(3));
            return false;
        }

        try{
            result = Material.valueOf(args.get(4).toUpperCase());
        } catch(Exception ex){
            Messages.PARAM_INVALID.send(sender, args.get(4));
            return false;
        }

        var trade = new Trade();
        trade.setIngredient(request, requestQty);
        trade.setResult(result, resultQty);
        manager.addTrade(args.get(0), trade);
        Messages.TRADE_ADDED.send(sender, args.get(0));

        return true;
    }
}
