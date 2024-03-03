package fishcute.celestial.version.dependent;

import fishcute.celestialmain.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import oshi.util.tuples.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

public class VMinecraftInstance {
    private static Minecraft minecraft = Minecraft.getInstance();
    public static boolean doesLevelExist() {
        return minecraft.level != null;
    }
    public static boolean doesPlayerExist() {
        return minecraft.player != null;
    }
    public static String getLevelPath() {
        return minecraft.level.dimension().location().getPath();
    }
    public static float getTickDelta() {
        return minecraft.getDeltaFrameTime();
    }
    public static Vector getPlayerEyePosition() {
        return Vector.fromVec(minecraft.player.getEyePosition(getTickDelta()));
    }

    public static void sendFormattedErrorMessage(String error, String type, String location) {
        minecraft.player.displayClientMessage(
                Component.literal(ChatFormatting.DARK_RED +
                "[Celestial] " + type +
                        ChatFormatting.GRAY + " at " +
                        ChatFormatting.YELLOW + location +
                        ChatFormatting.GRAY + ": " +
                        ChatFormatting.WHITE + error
                ), false);
    }
    public static void sendInfoMessage(String i) {
        minecraft.player.displayClientMessage(Component.literal(
                ChatFormatting.DARK_AQUA + "[Celestial] " +
                ChatFormatting.AQUA + i), false);
    }
    public static void sendErrorMessage(String i) {
        minecraft.player.displayClientMessage(
                Component.literal(ChatFormatting.DARK_RED +
                        "[Celestial] " +
                        ChatFormatting.RED + i
                ), false);
    }
    public static void sendRedMessage(String i) {
        minecraft.player.displayClientMessage(
                Component.literal(ChatFormatting.RED + i
                ), false);
    }
    public static InputStream getResource(String path) throws IOException {
        return minecraft.getResourceManager().getResource(new ResourceLocation(path)).get().open();
    }
    public static boolean isGamePaused() {
        return minecraft.isPaused();
    }
    public static void sendMessage(String text, boolean actionBar) {
        minecraft.player.displayClientMessage(Component.literal(text), actionBar);
    }
    public static double getPlayerX() {
        return minecraft.player.getX();
    }
    public static double getPlayerY() {
        return minecraft.player.getY();
    }
    public static double getPlayerZ() {
        return minecraft.player.getZ();
    }
    public static double getRainLevel() {
        return minecraft.level.getRainLevel(getTickDelta());
    }
    public static boolean isPlayerInWater() {
        return minecraft.player.isInWater();
    }
    public static long getGameTime() {
        return minecraft.level.getGameTime();
    }
    public static long getWorldTime() {
        return minecraft.level.dayTime();
    }
    public static float getStarBrightness() {
        return minecraft.level.getStarBrightness(getTickDelta());
    }
    public static float getTimeOfDay() {
        return minecraft.level.getTimeOfDay(getTickDelta());
    }
    public static float getViewXRot() {
        return minecraft.player.getViewXRot(getTickDelta());
    }
    public static float getViewYRot() {
        return minecraft.player.getViewYRot(getTickDelta());
    }
    public static float getCameraLookVectorTwilight(float h) {
        return minecraft.gameRenderer.getMainCamera().getLookVector().dot(new Vector3f(h, 0.0F, 0.0F));
    }

    public static BlockPos getPlayerBlockPosition() {
        return minecraft.player.blockPosition();
    }
    public static float getRenderDistance() {
        return minecraft.options.getEffectiveRenderDistance();
    }
    public static float getMoonPhase() {
        return minecraft.level.getMoonPhase();
    }
    public static float getSkyDarken() {
        return minecraft.level.getSkyDarken();
    }
    public static float getBossSkyDarken() {
        return minecraft.gameRenderer.getDarkenWorldAmount(getTickDelta());
    }
    public static float getSkyFlashTime() {
        return minecraft.level.getSkyFlashTime();
    }
    public static float getThunderLevel() {
        return minecraft.level.getThunderLevel(getTickDelta());
    }
    public static float getSkyLight() {
        return minecraft.level.getBrightness(LightLayer.SKY, getPlayerBlockPosition());
    }
    public static float getBlockLight() {
        return minecraft.level.getBrightness(LightLayer.BLOCK, getPlayerBlockPosition());
    }
    public static float getBiomeTemperature() {
        return minecraft.level.getBiome(getPlayerBlockPosition()).value().getBaseTemperature();
    }
    public static float getBiomeDownfall() {
        return minecraft.level.getBiome(getPlayerBlockPosition()).value().hasPrecipitation() ? 1 : 0;
    }
    public static boolean getBiomeSnow() {
        return minecraft.level.getBiome(getPlayerBlockPosition()).value().coldEnoughToSnow(getPlayerBlockPosition());
    }
    public static boolean isRightClicking() {
        return minecraft.mouseHandler.isRightPressed();
    }
    public static boolean isLeftClicking() {
        return minecraft.mouseHandler.isLeftPressed();
    }
    public static ResourceLocation getMainHandItemKey() {
        return BuiltInRegistries.ITEM.getKey(minecraft.player.getMainHandItem().getItem());
    }
    public static String getMainHandItemNamespace() {
        return getMainHandItemKey().getNamespace();
    }
    public static String getMainHandItemPath() {
        return getMainHandItemKey().getPath();
    }

    static HashMap<Biome, Pair<String, String>> biomeNameMap = new HashMap<>();

    static void addToBiomeMap(Holder<Biome> b) {
        biomeNameMap.put(b.value(),
                new Pair<>(
                        b.unwrapKey().get().location().getNamespace() + ":" + b.unwrapKey().get().location().getPath(),
                        b.unwrapKey().get().location().getPath()
                ));
    }
    public static boolean equalToBiome(Vector position, String... name) {
        Holder<Biome> b = minecraft.level.getBiome(position == null ? getPlayerBlockPosition() : position.toBlockPos());
        if (!biomeNameMap.containsKey(b.value()))
            addToBiomeMap(b);
        return Arrays.stream(name).toList().contains(biomeNameMap.get(b.value()).getA()) || Arrays.stream(name).toList().contains(biomeNameMap.get(b.value()).getB());
    }
    public static double[] getBiomeSkyColor() {
        double[] c = new double[3];
        Util.getRealSkyColor = true;
        Vec3 vec = CubicSampler.gaussianSampleVec3(minecraft.player.position(), (ix, jx, kx) -> {
            return Vec3.fromRGB24((minecraft.level.getBiome(new BlockPos(ix, jx, kx)).value()).getSkyColor());
        });
        Util.getRealSkyColor = false;
        c[0] = vec.x;
        c[1] = vec.y;
        c[2] = vec.z;
        return c;
    }
    public static double[] getBiomeFogColor() {
        double[] c = new double[3];
        Util.getRealFogColor = true;
        Vec3 vec = CubicSampler.gaussianSampleVec3(minecraft.player.position(), (ix, jx, kx) -> {
            return Vec3.fromRGB24((minecraft.level.getBiome(new BlockPos(ix, jx, kx)).value()).getFogColor());
        });
        Util.getRealFogColor = false;
        c[0] = vec.x;
        c[1] = vec.y;
        c[2] = vec.z;
        return c;
    }

    public static boolean disableFogChanges() {
        return minecraft.gameRenderer.getMainCamera().getFluidInCamera() !=
                FogType.NONE || minecraft.player.hasEffect(MobEffects.BLINDNESS);
    }
    public static boolean isCameraInWater() {
        return minecraft.gameRenderer.getMainCamera().getFluidInCamera() == FogType.WATER;
    }
    public static double getNightVisionModifier(float tickDelta) {
        if (!doesPlayerExist() || !minecraft.player.hasEffect(MobEffects.NIGHT_VISION))
            return 0;
        return GameRenderer.getNightVisionScale(minecraft.player, tickDelta);
    }
    public static boolean isSneaking() {
        return minecraft.player.isShiftKeyDown();
    }

    public static float getDarknessFogEffect(float fogStart, float tickDelta) {
        return Mth.lerp((minecraft.player.getEffect(MobEffects.DARKNESS).getFactorData().get()).getFactor(minecraft.player, tickDelta), fogStart, 15.0F);
    }
    public static boolean hasDarkness() {
        return minecraft.player.hasEffect(MobEffects.DARKNESS);
    }
}
