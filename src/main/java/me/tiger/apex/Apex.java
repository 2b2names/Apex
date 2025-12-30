package me.tiger.apex;

import me.tiger.apex.checks.*;
import me.tiger.apex.util.DiscordWebhook;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Apex extends JavaPlugin implements Listener {

    private final List<Check> checks = new ArrayList<>();
    private DiscordWebhook webhook;
    private boolean enabled = true;

    @Override
    public void onEnable() {
        // Initialize Discord webhook
        webhook = new DiscordWebhook("/"); 

        // Register checks
        checks.add(new NoFly());
        checks.add(new NoKillAura());
        checks.add(new NoAntiKB());
        checks.add(new AntiScaffold());

        getLogger().info("Apex enabled with " + checks.size() + " checks.");

        // Scheduler to scan players every tick
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if (!enabled) return;
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (Check check : checks) {
                    if (check.run(player)) {
                        notifyDiscord(player.getName(), check.getName());
                    }
                }
            }
        }, 20L, 20L); // every second

        Bukkit.getPluginManager().registerEvents(new PlayerEventHandler(this), this);
    }

    private void notifyDiscord(String playerName, String checkName) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String msg = "Player: " + playerName + " | Time: " + time + " | Violation: " + checkName;
        webhook.sendMessage(msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("You must be OP to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("/apex enable | /apex disable");
            return true;
        }

        if (args[0].equalsIgnoreCase("disable")) {
            enabled = false;
            sender.sendMessage("Apex anti-cheat is now disabled.");
            getLogger().info("Apex disabled by " + sender.getName());
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            enabled = true;
            sender.sendMessage("Apex anti-cheat is now enabled.");
            getLogger().info("Apex enabled by " + sender.getName());
            return true;
        }

        return false;
    }

    public boolean isEnabledAC() {
        return enabled;
    }

    public DiscordWebhook getWebhook() {
        return webhook;
    }
}
