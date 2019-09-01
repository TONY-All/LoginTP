package top.minicraft.logintp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdexcuter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("logintp")){
            if(args.length == 0) {
                sender.sendMessage("§b============= §clogintp §b=============");
                sender.sendMessage("§6/logintp help §2显示帮助");
                sender.sendMessage("§6/logintp set §2设置登录传送点");
                sender.sendMessage("§6/logintp tp §2传送至登录传送点");
                sender.sendMessage("§6/logintp reload §2重新加载配置文件");
                return true;
            }
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("help")){
                    sender.sendMessage("§b============= §clogintp §b=============");
                    sender.sendMessage("§6/logintp help §2显示帮助");
                    sender.sendMessage("§6/logintp set §2设置登录传送点");
                    sender.sendMessage("§6/logintp tp §2传送至登录传送点(WIP)");
                    sender.sendMessage("§6/logintp reload §2重新加载配置文件");
                    return true;
                }
                if (sender instanceof Player){
                    Player p = (Player)sender;
                    if(args[0].equalsIgnoreCase("set") && p.hasPermission("logintp.set")){
                        double x = p.getLocation().getX();
                        double y = p.getLocation().getY();
                        y++;
                        double z = p.getLocation().getZ();
                        float yaw = p.getLocation().getYaw();
                        float pitch = p.getLocation().getPitch();
                        String world = p.getLocation().getWorld().getName();
                        logintp.plugin.getConfig().set("location.x",x);
                        logintp.plugin.getConfig().set("location.y",y);
                        logintp.plugin.getConfig().set("location.z",z);
                        logintp.plugin.getConfig().set("location.yaw",yaw);
                        logintp.plugin.getConfig().set("location.pitch",pitch);
                        logintp.plugin.getConfig().set("location.world",world);
                        logintp.plugin.saveConfig();
                        sender.sendMessage("§a您已成功设置传送点");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("reload") && p.hasPermission("logintp.reload")){
                        logintp.plugin.reloadConfig();
                        sender.sendMessage("§a成功重载插件");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("tp") && p.hasPermission("logintp.tp")){
                        double x = logintp.plugin.getConfig().getDouble("location.x");
                        double y = logintp.plugin.getConfig().getDouble("location.y");
                        double z = logintp.plugin.getConfig().getDouble("location.z");
                        float yaw = (float) logintp.plugin.getConfig().getDouble("location.yaw");
                        float pitch = (float) logintp.plugin.getConfig().getDouble("location.pitch");
                        World w = Bukkit.getWorld(logintp.plugin.getConfig().getString("location.world"));
                        Location l = new Location(w, x, y, z, yaw, pitch);
                        p.teleport(l);
                        return true;
                    }
                }else{
                    sender.sendMessage("§4你不是一名玩家");
                    return true;
                }
            }

        }
        sender.sendMessage("§4你输入的指令有误，请检查后输入");
        return true;
    }
}
