package fishcute.celestial.version.dependent;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.Tesselator;
import fishcute.celestialmain.api.minecraft.IMcVector;
import fishcute.celestialmain.api.minecraft.wrappers.IBufferBuilderWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IResourceLocationWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IShaderInstanceWrapper;
import fishcute.celestialmain.version.independent.Instances;

public class VInstances {
    public static void setInstances() {
        Instances.minecraft = new VMinecraftInstance();
        Instances.renderSystem = new VRenderSystem();

        Instances.bufferBuilderFactory = new Factories.BufferBuilder();
        Instances.shaderInstanceFactory = new Factories.ShaderInstance();
        Instances.resourceLocationFactory = new Factories.ResourceLocation();
        Instances.vectorFactory = new Factories.Vector();
    }

    protected interface Factories {
        class BufferBuilder implements IBufferBuilderWrapper.Factory {
            @Override
            public IBufferBuilderWrapper build() {
                return (IBufferBuilderWrapper) Tesselator.getInstance().getBuilder();
            }
        }

        class ShaderInstance implements IShaderInstanceWrapper.Factory {
            @Override
            public IShaderInstanceWrapper build() {
                return (IShaderInstanceWrapper) RenderSystem.getShader();
            }
        }

        class ResourceLocation implements IResourceLocationWrapper.Factory {
            @Override
            public IResourceLocationWrapper build(String name) {
                return (IResourceLocationWrapper) new net.minecraft.resources.ResourceLocation(name);
            }
        }

        class Vector implements IMcVector.Factory {

            @Override
            public IMcVector build(float x, float y, float z) {
            return new fishcute.celestial.version.dependent.Vector(x, y, z);
            }
        }
    }
}
