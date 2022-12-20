package me.danipro.advancedblocker.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {

    Main plugin;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = 64)
    public void onTab(TabCompleteEvent e) {
        if (e.isCancelled())
            return;
        if (!(e.getSender() instanceof ProxiedPlayer))
            return;
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        if(p.hasPermission("advancedblocker.bypass.tab"))
            return;
        p.hasPermission("advancedblocker.bypass.tab");
        String[] args = e.getCursor().split(" ");
        String checked = ((args.length > 0) ? args[args.length - 1] : e.getCursor()).toLowerCase();
        if (checked.equals("/") || checked.equals("/bukkit:ver") || checked.equals("/bukkit:about") || checked.equals("/bukkit:version") || checked.equals("/bukkit:?") || checked.equals("/bukkit:pl") || checked.equals("/bukkit:plugins") || checked.equals("/ver") || checked.equals("/about") || checked.equals("/version") || checked.equals("/?") || checked.equals("/pl") || checked.equals("/icanhasbukkit") || checked.equals("/plugins") || checked.equals("/help") || checked.equals("/a") || checked.equals("/b") || checked.equals("/c") || checked.equals("/d") || checked.equals("/e") || checked.equals("/f") || checked.equals("/g") || checked.equals("/h") || checked.equals("/i") || checked.equals("/j") || checked.equals("/k") || checked.equals("/l") || checked.equals("/m") || checked.equals("/n") || checked.equals("/") || checked.equals("/o") || checked.equals("/p") || checked.equals("/q") || checked.equals("/r") || checked.equals("/s") || checked.equals("/t") || checked.equals("/u") || checked.equals("/v") || checked.equals("/w") || checked.equals("/x") || checked.equals("/y") || checked.equals("/z"))
            this.plugin.log(String.valueOf(p.getName()) + " try to use TAB!");
        e.setCancelled(true);
    }

}
