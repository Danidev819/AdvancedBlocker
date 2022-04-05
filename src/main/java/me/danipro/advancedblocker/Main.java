package me.danipro.advancedblocker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

public class Main extends Plugin implements Listener {
    static Configuration config;

    File file;

    String startdate;

    private static Main instance;

    static Main plugin;

    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerListener(this, new PlayerListener(this));
        getProxy().getPluginManager().registerListener(this, this);
        getLogger().info("Bungee AdvancedBlocker has been enabled");
        getLogger().info("By Danidev819");
        plugin = this;
        LoadFile();
        SetupFileManager();
        getProxy().getPluginManager().registerCommand(this, new Commands());
        log("Plugin loaded");
    }

    static void LoadFile() {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();
        File configfile;
        if (!(configfile = new File(plugin.getDataFolder(), "config.yml")).exists())
            try {
                Files.copy(plugin.getResourceAsStream("config.yml"), configfile.toPath(), new java.nio.file.CopyOption[0]);
            } catch (IOException iOException) {}
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void playercmd(ChatEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)event.getSender();
            if (!event.getMessage().startsWith("/"))
                return;
            String[] command = event.getMessage().toLowerCase().split(" ", 2);
            if (config.getStringList("CommandsBlocked").contains(command[0].substring(1)))
                for (ProxiedPlayer proxy : BungeeCord.getInstance().getPlayers()) {
                    if (proxy.hasPermission("advancedblocker.msg") &&
                            !proxy.getName().equals(player.getName()))
                        proxy.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("AlertMsg").replaceAll("%player%", player.getName()).replaceAll("%cmd%", command[0].substring(1))));
                }
            if (player.hasPermission("advancedblocker.bypass.cmd"))
                return;
            if (config.getStringList("CommandsBlocked").contains(command[0].substring(1))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&' ,config.getString("NoPermsMsg")));
                        log(String.valueOf(player.getName()) + " try to use command: " + command[0]);
                if (config.getBoolean("EnableKick") &&
                        !player.hasPermission("advancedblocker.bypass.kick"))
                    player.disconnect((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("KickMessage"))));
                            event.setCancelled(true);
            }
        }
    }

    public void onDisable() {
        getLogger().info("Bungee AdvancedBlocker has been disabled");
    }

    public void SetupFileManager() {
        if (config.getBoolean("EnableLog")) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd-hhmmss");
            this.startdate = dateFormat2.format(date);
            File theDir = new File(getDataFolder() + "/logs/");
            if (!theDir.exists())
                try {
                    theDir.mkdir();
                } catch (Exception exception) {}
            this.file = new File(getDataFolder() + "/logs/log-" + this.startdate + ".log");
            if (!this.file.exists())
                try {
                    this.file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } else {
            getLogger().info("Log in disable mod!");
        }
    }

    public void log(String Text) {
        if (config.getBoolean("EnableLog")) {
            Date date = Calendar.getInstance().getTime();
            Exception exception2, exception1 = null;
        }
    }

    public static Main getInstance() {
        return instance;
    }
}
