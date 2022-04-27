package me.danipro.advancedblocker.bungee;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Commands extends Command {
    public static Main plugin = Main.getInstance();

    public Commands() {
        super("ablocker");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            if (args.length == 0) {
                p.sendMessage("Protected by AdvancedBlocker v" + plugin.getDescription().getVersion());
                p.sendMessage("Danipro_2007");
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (p.hasPermission("advancedblocker.reload")) {
                    p.sendMessage("");
                            plugin.reloadConfig();
                    p.sendMessage("Complete");
                } else {
                    p.sendMessage("Protected by AdvancedBlocker v" + plugin.getDescription().getVersion());
                    p.sendMessage("Danipro_2007");
                }
            } else {
                p.sendMessage("Protected by AdvancedBlocker v" + plugin.getDescription().getVersion());
                p.sendMessage("Danipro_2007");
            }
        } else if (args.length == 0) {
            BungeeCord.getInstance().getLogger().info("Protected by AdvancedBlocker v" + plugin.getDescription().getVersion());
            BungeeCord.getInstance().getLogger().info("Danipro_2007");
        } else if (args[0].equalsIgnoreCase("reload")) {
            BungeeCord.getInstance().getLogger().info("");
                    plugin.reloadConfig();
            BungeeCord.getInstance().getLogger().info("Complete");
        }
    }
}
