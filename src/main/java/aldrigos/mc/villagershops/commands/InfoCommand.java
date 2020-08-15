package aldrigos.mc.villagershops.commands;

import aldrigos.mc.villagershops.Messages;
import aldrigos.mc.villagershops.VillagerShopsPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class InfoCommand implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, LinkedList<String> args) {
        if(!sender.hasPermission("vshop.admin.info")){
            Messages.NO_PERM.send(sender, "vshop.admin.info");
            return false;
        }

        if(!(sender instanceof Player)){
            Messages.PLAYERCMD.send(sender);
            return false;
        }

        var player = (Player)sender;
        VillagerShopsPlugin.playerVsInfo.add(player.getUniqueId());

        return false;
    }
}
