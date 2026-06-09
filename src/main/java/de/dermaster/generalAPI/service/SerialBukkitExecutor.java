package de.dermaster.generalAPI.service;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Supplier;

public class SerialBukkitExecutor {

    private final Queue<SerialTask> queue = new ArrayDeque<>();
    private SerialTask active;
    private final JavaPlugin plugin;

    public SerialBukkitExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public synchronized void execute(Supplier<SerialTask> supplier) {
        queue.add(supplier.get());
        if (active == null) {
            scheduleNext();
        }
    }

    public synchronized void scheduleNext() {
        if (active != null) {
            active.runnable().cancel();
        }

        if ((active = queue.poll()) != null) {
            active.runnable().runTaskTimer(plugin, 0L, 20L * active.period());
        }
    }

    public record SerialTask(BukkitRunnable runnable, long period) {}
}