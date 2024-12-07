package de.eintosti.buildsystem.event.world;

import de.eintosti.buildsystem.world.BuildWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event reduces duplicated code.
 * <p>It will be called when</p>
 * <ul>
 *     <li>Breaking Blocks</li>
 *     <li>Placing Blocks</li>
 *     <li>Other modification related stuff</li>
 * </ul>
 * Cancelling this event will affect the parent-Event, which has caused the ManipulationEvent to fire.
 * <p>Expect the manipulation event to be cancelled at {@link org.bukkit.event.EventPriority#LOW} if the player is not allowed to interact with the world.</p>
 *
 * @see de.eintosti.buildsystem.listener.WorldManipulateListener
 * @since TODO
 */
public class BuildWorldManipulationEvent extends BuildWorldEvent implements Cancellable {

    private final Cancellable parentEvent;
    private final Player player;

    public BuildWorldManipulationEvent(Cancellable parentEvent, Player player, BuildWorld buildWorld) {
        super(buildWorld);
        this.parentEvent = parentEvent;
        this.player = player;
    }

    /**
     * @return whether the parent event has been cancelled
     */
    @Override
    public boolean isCancelled() {
        return parentEvent.isCancelled();
    }

    /**
     * @param cancelled true if the parent event should be cancelled.
     */
    @Override
    public void setCancelled(boolean cancelled) {
        parentEvent.setCancelled(cancelled);
    }

    /**
     * @return the event which has caused the manipulation event to fire.
     */
    public Cancellable getParentEvent() {
        return parentEvent;
    }

    public Player getPlayer() {
        return player;
    }


}