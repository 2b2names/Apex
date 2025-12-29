package me.tiger.apex.checks;

import org.bukkit.entity.Player;

public class NoKillAura extends Check {

    public NoKillAura() {
        super("NoKillAura");
    }

    @Override
    protected boolean check(Player player) {
        // Placeholder: detect impossible attack speed or reach
        // Currently just returns false until proper logic is added
        return false;
    }
}
