package top.minicraft.logintp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class EventDealer implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        double x = logintp.plugin.getConfig().getDouble("location.x");
        double y = logintp.plugin.getConfig().getDouble("location.y");
        double z = logintp.plugin.getConfig().getDouble("location.z");
        float yaw = (float) logintp.plugin.getConfig().getDouble("location.yaw");
        float pitch = (float) logintp.plugin.getConfig().getDouble("location.pitch");
        World w = Bukkit.getWorld(logintp.plugin.getConfig().getString("location.world"));
        Location l = new Location(w, x, y, z, yaw, pitch);
        e.getPlayer().teleport(l);
    }
}
