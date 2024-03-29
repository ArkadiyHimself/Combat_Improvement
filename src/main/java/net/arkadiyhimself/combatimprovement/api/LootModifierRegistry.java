package net.arkadiyhimself.combatimprovement.api;

import com.mojang.serialization.Codec;
import net.arkadiyhimself.combatimprovement.CombatImprovement;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LootModifierRegistry {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, CombatImprovement.MODID);

    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }

}
