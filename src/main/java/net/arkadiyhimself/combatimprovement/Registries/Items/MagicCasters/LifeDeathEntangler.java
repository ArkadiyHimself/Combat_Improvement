package net.arkadiyhimself.combatimprovement.Registries.Items.MagicCasters;

import net.arkadiyhimself.combatimprovement.HandlersAndHelpers.UsefulMethods;
import net.arkadiyhimself.combatimprovement.Registries.MobEffects.MobEffectRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LifeDeathEntangler extends SpellCasters {
    public LifeDeathEntangler(SoundEvent castSound) {
        super(50, castSound, Component.translatable("Entangle").withStyle(ChatFormatting.BOLD, ChatFormatting.LIGHT_PURPLE), Ability.SELF, 0);
    }

    @Override
    public boolean conditionNotMet(ServerPlayer player) {
        return (!(player.getHealth() <= 2));
    }

    @Override
    public void activeAbility(@NotNull ServerPlayer player) {
        if (conditionNotMet(player) || hasCooldown(player)) { return; }
        UsefulMethods.Abilities.addEffectWithoutParticles(player, MobEffectRegistry.ABSOLUTE_BARRIER.get(), 10);
        super.activeAbility(player);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatable(" "));
        List<Component> active = new ArrayList<>();
        List<Component> passive = new ArrayList<>();

        ChatFormatting[] text = new ChatFormatting[]{ChatFormatting.LIGHT_PURPLE};
        ChatFormatting[] ability = new ChatFormatting[]{ChatFormatting.BOLD, ChatFormatting.DARK_RED};
        ChatFormatting[] list = new ChatFormatting[]{ChatFormatting.BLUE};

        UsefulMethods.Gui.addComponent(passive, "tooltip.combatimprovement.entangler.release.1", new ChatFormatting[]{ChatFormatting.BOLD, ChatFormatting.DARK_RED}, null);

        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.common.active", text, ability, abilityName);
        active.add(Component.translatable(" "));
        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.entangler.press.1", text, null);
        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.entangler.press.2", text, null);
        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.entangler.press.3", text, null);
        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.entangler.press.4", text, null);
        active.add(Component.translatable(" "));
        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.entangler.press.5", text, ability, abilityName, (float) MAX_RECH / 20);
        active.add(Component.translatable(" "));
        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.common.passives", new ChatFormatting[]{ChatFormatting.DARK_BLUE}, null);
        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.entangler.passive.1", list, null);
        UsefulMethods.Gui.addComponent(active, "tooltip.combatimprovement.entangler.passive.2", list, null);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.addAll(active);
        } else { pTooltipComponents.addAll(passive); }
    }
}
