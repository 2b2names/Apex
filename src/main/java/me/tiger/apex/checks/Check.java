package me.tiger.apex.checks;

import org.bukkit.entity.Player;

public abstract class Check {
    protected final String name;

    public Check(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Run the check, return true if violation detected
    public abstract boolean run(Player player);

    // Kick and notify
    public void onViolation(Player player) {
        player.kickPlayer("You have been kicked for: " + name);
    }
}
