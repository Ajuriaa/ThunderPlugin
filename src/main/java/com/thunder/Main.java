package com.thunder;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class Main extends org.bukkit.plugin.java.JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getLogger().info("ThunderPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ThunderPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // Check permissions
        if (!sender.hasPermission("thunder.use")) {
            sender.sendMessage("You don't have permission to use this command.");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /thunder <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            sender.sendMessage("Player not found or is not online.");
            return true;
        }

        target.getWorld().strikeLightning(target.getLocation());
        sender.sendMessage("Summoned lightning on " + target.getName() + ".");
        return true;
    }
}
