package top.minicraft.logintp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class logintp extends JavaPlugin {
    public static logintp plugin;
    @Override
    public void onEnable(){
        plugin = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EventDealer(),this);
        this.getServer().getPluginCommand("logintp").setExecutor(new cmdexcuter());
    }
}
