package net.fwuffypetsowo.colorstone;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

public class ResourcePackLoader {
    static boolean complete;

    public static void loadAll(ResourceManager resourceManager) {
        complete = false;
        Iterator<PackResources> iterator = resourceManager.listPacks().iterator();
        while (iterator.hasNext()) {
            PackResources pack = iterator.next();
            load(pack);
        }
    }

    private static void load(PackResources pack) {
        for (String namespace : pack.getNamespaces(PackType.CLIENT_RESOURCES)) {
            if (namespace.equals("colorstone")) {
                Collection<ResourceLocation> ids = pack.getResources(PackType.CLIENT_RESOURCES, namespace, "colors", Integer.MAX_VALUE, path -> path.endsWith(".colorstone"));
                for (ResourceLocation id : ids) {
                    try (InputStream stream = pack.getResource(PackType.CLIENT_RESOURCES, id)) {
                        Properties properties = new Properties();
                        properties.load(stream);
                        readToArray(properties);
                    } catch (IOException e) {}
                }
                complete = true;
            }
        }
    }

    public static void readToArray(Properties properties) {
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            String level = keys.nextElement().toString();
            String color = properties.getProperty(level);
            if (color.length() == 6 && level.length() <= 2) {
                try {
                    ColorUtil.CUSTOM_COLORS[Integer.parseInt(level)] = new Vec3(Mth.clamp((float) Integer.parseInt(color.substring(0, 2), 16)/255.0f, 0.0f, 1.0f), Mth.clamp((float) Integer.parseInt(color.substring(2, 4), 16)/255.0f, 0.0f, 1.0f), Mth.clamp((float) Integer.parseInt(color.substring(4, 6), 16)/255.0f, 0.0f, 1.0f));
                } catch (NumberFormatException e) {}
            }
        }
    }
}
