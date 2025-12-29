package me.tiger.apex.checks;

import org.bukkit.entity.Player;

public class AntiScaffold extends Check {

    public AntiScaffold() {
        super("AntiScaffold");
    }

    @Override
    protected boolean check(Player player) {
        // Placeholder: detect rapid unnatural block placing
        // For now just return false until logic is added
        return false;
    }
}
