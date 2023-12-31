package net.arkadiyhimself.combatimprovement.Networking.packets.CapabilityUpdate;

import dev._100media.capabilitysyncer.network.IPacket;
import net.arkadiyhimself.combatimprovement.util.Capability.Abilities.DataSincyng.AttachDataSync;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;

public class DeltaMovementC2S implements IPacket {
    private final Vec3 movement;
    public DeltaMovementC2S(Vec3 vec3) {
        this.movement = vec3;

    }
    @Override
    public void handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            if (context.getSender() == null) { return; }
            AttachDataSync.get(context.getSender()).ifPresent(dataSync -> dataSync.setDeltaMovement(movement));
        });
        context.setPacketHandled(true);
    }
    public static DeltaMovementC2S read(FriendlyByteBuf packetBuf) {
        return new DeltaMovementC2S(new Vec3(packetBuf.readDouble(), packetBuf.readDouble(), packetBuf.readDouble()));
    }
    @Override
    public void write(FriendlyByteBuf packetBuf) {
        packetBuf.writeDouble(movement.x());
        packetBuf.writeDouble(movement.y());
        packetBuf.writeDouble(movement.z());
    }
    public static void register(SimpleChannel channel, int id) {
        IPacket.register(channel, id, NetworkDirection.PLAY_TO_SERVER, DeltaMovementC2S.class, DeltaMovementC2S::read);
    }
}
