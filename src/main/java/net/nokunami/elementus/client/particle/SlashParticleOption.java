//package net.nokunami.elementus.client.particle;
//
//import com.mojang.brigadier.StringReader;
//import com.mojang.brigadier.exceptions.CommandSyntaxException;
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.particles.ParticleOptions;
//import net.minecraft.core.particles.ParticleType;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.core.particles.VibrationParticleOption;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.world.level.gameevent.BlockPositionSource;
//import net.minecraft.world.level.gameevent.PositionSource;
//import net.minecraft.world.level.gameevent.PositionSourceType;
//import net.minecraft.world.phys.Vec3;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Locale;
//
//public class SlashParticleOption implements ParticleOptions {
//    public static final Codec<SlashParticleOption> CODEC = RecordCodecBuilder.create((optionInstance) ->
//            optionInstance.group(PositionSource.CODEC.fieldOf("destination").forGetter((slashParticleOption) ->
//                    slashParticleOption.destination), Codec.INT.fieldOf("arrival_in_ticks").forGetter((slashParticleOption) ->
//                    slashParticleOption.arrivalInTicks)).apply(optionInstance, SlashParticleOption::new));
//    public static final ParticleOptions.Deserializer<VibrationParticleOption> DESERIALIZER = new ParticleOptions.Deserializer<VibrationParticleOption>() {
//        public @NotNull VibrationParticleOption fromCommand(@NotNull ParticleType<VibrationParticleOption> p_175859_, StringReader p_175860_) throws CommandSyntaxException {
//            p_175860_.expect(' ');
//            float f = (float)p_175860_.readDouble();
//            p_175860_.expect(' ');
//            float f1 = (float)p_175860_.readDouble();
//            p_175860_.expect(' ');
//            float f2 = (float)p_175860_.readDouble();
//            p_175860_.expect(' ');
//            int i = p_175860_.readInt();
//            BlockPos blockpos = BlockPos.containing(f, f1, f2);
//            return new VibrationParticleOption(new BlockPositionSource(blockpos), i);
//        }
//
//        public @NotNull VibrationParticleOption fromNetwork(@NotNull ParticleType<VibrationParticleOption> p_175862_, @NotNull FriendlyByteBuf p_175863_) {
//            PositionSource positionsource = PositionSourceType.fromNetwork(p_175863_);
//            int i = p_175863_.readVarInt();
//            return new VibrationParticleOption(positionsource, i);
//        }
//    };
//    private final PositionSource destination;
//    private final int arrivalInTicks;
//
//    public SlashParticleOption(PositionSource source, int arrivalInTicks) {
//        this.destination = source;
//        this.arrivalInTicks = arrivalInTicks;
//    }
//
//    public void writeToNetwork(@NotNull FriendlyByteBuf pBuffer) {
//        PositionSourceType.toNetwork(this.destination, pBuffer);
//        pBuffer.writeVarInt(this.arrivalInTicks);
//    }
//
//    public @NotNull String writeToString() {
//        Vec3 vec3 = this.destination.getPosition(null).get();
//        double d0 = vec3.x();
//        double d1 = vec3.y();
//        double d2 = vec3.z();
//        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %d", BuiltInRegistries.PARTICLE_TYPE.getKey(this.getType()), d0, d1, d2, this.arrivalInTicks);
//    }
//
//    public @NotNull ParticleType<VibrationParticleOption> getType() {
//        return ParticleTypes.VIBRATION;
//    }
//
//    public PositionSource getDestination() {
//        return this.destination;
//    }
//
//    public int getArrivalInTicks() {
//        return this.arrivalInTicks;
//    }
//}
