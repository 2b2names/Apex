package me.tiger.apex.checks;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class NoFly extends Check {

    public NoFly() {
        super("NoFly");
    }

    @Override
    protected boolean check(Player player) {
        if (player.isFlying() && !player.hasPermission("apex.fly")) {
            onViolation(player);
            return true;
        }
        return false;
    }

    public void handleEvent(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) return; // Skip OPs
        if (check(player)) event.setCancelled(true);
    }
}
