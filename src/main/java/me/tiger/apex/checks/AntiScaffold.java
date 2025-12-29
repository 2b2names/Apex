package me.tiger.apex.checks;

import org.bukkit.entity.Player;

public class AntiScaffold extends Check {

    public AntiScaffold() {
        super("AntiScaffold");
    }

    @Override
    public boolean run(Player player) {
        // Placeholder: detect rapid unnatural block placing
        return false;
    }
}
