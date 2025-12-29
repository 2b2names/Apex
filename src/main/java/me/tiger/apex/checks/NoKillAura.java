package me.tiger.apex.checks;

import org.bukkit.entity.Player;

public class NoKillAura extends Check {

    public NoKillAura() {
        super("NoKillAura");
    }

    @Override
    public boolean run(Player player) {
        // Placeholder: detect attacking too fast or impossible reach
        // Can expand with attack event timestamps and distance
        return false;
    }
}
