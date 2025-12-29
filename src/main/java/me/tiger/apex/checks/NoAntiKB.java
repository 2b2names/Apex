package me.tiger.apex.checks;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class NoAntiKB extends Check {

    public NoAntiKB() {
        super("NoAntiKB");
    }

    @Override
    protected boolean check(Player player) {
        // Example: detect no knockback (velocity too small)
        Vector v = player.getVelocity();
        if (v.length() < 0.01) {
            onViolation(player);
            return true;
        }
        return false;
    }
}
