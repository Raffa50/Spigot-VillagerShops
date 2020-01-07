package aldrigos.mc.villagershops.commands;

import aldrigos.mc.villagershops.Messages;
import aldrigos.mc.villagershops.ShopsManager;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;

public class ListShopsCommand implements SubCommand {
    private final ShopsManager manager;

    public ListShopsCommand(ShopsManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean execute(CommandSender sender, LinkedList<String> args) {
        if(!sender.hasPermission("vshop.admin.list")){
            Messages.NO_PERM.send(sender, "vshop.admin.list");
            return false;
        }

        var sb = new StringBuilder("[VS] shops by id: ");
        for(var s: manager.getShops())
            sb.append(s.getId()).append(", ");

        sb.deleteCharAt(sb.length() -2);
        sender.sendMessage(sb.toString());

        return true;
    }
}
