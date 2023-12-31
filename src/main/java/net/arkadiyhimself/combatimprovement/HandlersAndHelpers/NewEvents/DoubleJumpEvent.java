package net.arkadiyhimself.combatimprovement.HandlersAndHelpers.NewEvents;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Start is fired when a player attempts to perform a double jump. <br>
 * <br>
 * This event is {@link Cancelable}.<br>
 * If it is canceled, the player does not start dashing.<br>
 * <br>
 * This event does not have a result. {@link HasResult}<br>
 */
@Cancelable
public class DoubleJumpEvent extends PlayerEvent {
    public DoubleJumpEvent(Player player) {
        super(player);
    }
}
