package me.tiger.apex;

import me.tiger.apex.checks.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.entity.Player;

public class PlayerEventHandler implements Listener {

    private final Apex plugin;

    public PlayerEventHandler(Apex plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFlight(PlayerToggleFlightEvent event) {
        if (!plugin.isEnabledAC()) return;

        Player player = event.getPlayer();
        if (player.isOp()) return; // Skip OPs

        NoFly noFly = new NoFly();
        noFly.handleEvent(event);

        if (player.isOnline() && !player.isOp()) {
            plugin.getWebhook().sendMessage(
                    player.getName() + " triggered NoFly"
            );
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!plugin.isEnabledAC()) return;
        if (!(event.getDamager() instanceof Player player)) return;
        if (player.isOp()) return; // Skip OPs

        NoKillAura check = new NoKillAura();
        if (check.run(player)) {
            plugin.getWebhook().sendMessage(player.getName() + " triggered NoKillAura");
        }
    }

    @EventHandler
    public void onVelocity(PlayerVelocityEvent event) {
        if (!plugin.isEnabledAC()) return;

        Player player = event.getPlayer();
        if (player.isOp()) return; // Skip OPs

        NoAntiKB check = new NoAntiKB();
        if (check.run(player)) {
            plugin.getWebhook().sendMessage(player.getName() + " triggered NoAntiKB");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!plugin.isEnabledAC()) return;

        Player player = event.getPlayer();
        if (player.isOp()) return; // Skip OPs

        AntiScaffold check = new AntiScaffold();
        if (check.run(player)) {
            plugin.getWebhook().sendMessage(player.getName() + " triggered AntiScaffold");
        }
    }
}
