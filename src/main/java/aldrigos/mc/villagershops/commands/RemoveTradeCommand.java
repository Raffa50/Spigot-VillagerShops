package aldrigos.mc.villagershops.commands;

import aldrigos.mc.villagershops.*;
import org.bukkit.command.CommandSender;

import java.util.IllegalFormatException;
import java.util.LinkedList;

public class RemoveTradeCommand implements SubCommand {
    private final ShopsManager manager;

    public RemoveTradeCommand(ShopsManager man){
        manager = man;
    }

    @Override
    public boolean execute(CommandSender sender, LinkedList<String> args) {
        if(!sender.hasPermission("vshop.admin.edit")){
            Messages.NO_PERM.send(sender, "vshop.admin.edit");
            return false;
        }
        if(args.size() < 2){
            Messages.PARAM_MISS.send(sender, "/vshop remove <id> <num>");
            return false;
        }

        int num;
        try{
            num = Integer.parseInt(args.get(1));
        } catch (IllegalFormatException e){
            Messages.PARAM_INVALID.send(sender, args.get(1));
            return false;
        }

        if(!manager.existShopId(args.get(0))){
            Messages.SHOP_NOT_EXIST.send(sender, args.get(0));
            return false;
        }

        manager.removeTrade(args.get(0), num);
        return true;
    }
}
