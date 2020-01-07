package aldrigos.mc.villagershops.commands;

import org.bukkit.command.CommandSender;

import java.util.LinkedList;

public interface SubCommand {
    boolean execute(CommandSender sender, LinkedList<String> args);
}
