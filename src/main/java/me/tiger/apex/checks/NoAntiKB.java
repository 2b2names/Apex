package me.tiger.apex.checks;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class NoAntiKB extends Check {

    public NoAntiKB() {
        super("NoAntiKB");
    }

    @Override
    public boolean run(Player player) {
        Vector v = player.getVelocity();
        if (v.length() < 0.01) { // Example: no knockback
            onViolation(player);
            return true;
        }
        return false;
    }
}
