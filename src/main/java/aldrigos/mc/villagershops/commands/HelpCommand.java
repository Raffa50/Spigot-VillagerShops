package aldrigos.mc.villagershops.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;

public class HelpCommand implements SubCommand {
    private static final String help =
        ChatColor.YELLOW+"[VS]Commands:\n" +
        "/vshop new <id> <name> [type] :create a new shop\n" +
        "/vshop delete <id> :deletes a shop\n" +
        "/vshop list :shows all shop ids\n" +
        "/vshop add <id> <qty> <request> <qty> <item> :add to shop id a trade\n" +
        "/vshop remove <id> <index> :remove the trade at position index\n"+
        "/vshop goto <id> :teleports to the shop\n"+
        "/vshop info : enter this command and right click to get shop info"+ChatColor.RESET;

    @Override
    public boolean execute(CommandSender sender, LinkedList<String> args) {
        sender.sendMessage(help);
        return true;
    }
}
