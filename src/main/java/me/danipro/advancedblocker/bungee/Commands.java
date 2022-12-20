package me.danipro.advancedblocker.bungee;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
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
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (p.hasPermission("advancedblocker.reload")) {
                    p.sendMessage("");
                    plugin.reloadConfig();
                    p.sendMessage("Complete");
                } else {
                    p.sendMessage("Protected by AdvancedBlocker v" + plugin.getDescription().getVersion());
                }
            } else {
                p.sendMessage("Protected by AdvancedBlocker v" + plugin.getDescription().getVersion());
            }
        } else if (args.length == 0) {
            ProxyServer.getInstance().getLogger().info("Protected by AdvancedBlocker v" + plugin.getDescription().getVersion());
        } else if (args[0].equalsIgnoreCase("reload")) {
            ProxyServer.getInstance().getLogger().info("");
            plugin.reloadConfig();
            ProxyServer.getInstance().getLogger().info("Complete");
        }
    }
}
