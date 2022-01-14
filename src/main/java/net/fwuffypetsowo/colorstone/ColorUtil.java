package net.fwuffypetsowo.colorstone;

import net.minecraft.Util;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class ColorUtil {
    public static Vec3[] CUSTOM_COLORS = new Vec3[16];

    public static final Vec3[] RED_COLORS = Util.make(new Vec3[16], vec3s -> {
        for (int i = 0; i <= 15; i++) {
            float f = (float)i / 15.0f;
            float g = Mth.clamp((i == 0 ? 0.3f : 0.4f) + (f * 0.6f), 0.0f, 1.0f);
            vec3s[i] = new Vec3(g, 0.0f, 0.0f);
        }
    });

    public static final Vec3[] BLUE_COLORS = Util.make(new Vec3[16], vec3s -> {
        for (int i = 0; i <= 15; i++) {
            float f = (float)i / 15.0f;
            float g = Mth.clamp((i == 0 ? 0.3f : 0.4f) + (f * 0.6f), 0.0f, 1.0f);
            float h = Mth.clamp(f * f * 0.7f - 0.5f, 0.0f, 1.0f);
            vec3s[i] = new Vec3(0.0f, h, g);
        }
    });

    public static final Vec3[] GREEN_COLORS = Util.make(new Vec3[16], vec3s -> {
        for (int i = 0; i <= 15; i++) {
            float f = (float)i / 15.0f;
            float g = Mth.clamp((i == 0 ? 0.3f : 0.4f) + (f * 0.6f), 0.0f, 1.0f);
            float h = Mth.clamp(f * f * 0.7f - 0.5f, 0.0f, 1.0f);
            vec3s[i] = new Vec3(0.0f, g, h);
        }
    });

    public void setColorForPower(int power, float r, float g, float b) {
        float cr = Mth.clamp(r, 0.0f, 1.0f);
        float cg = Mth.clamp(g, 0.0f, 1.0f);
        float cb = Mth.clamp(b, 0.0f, 1.0f);
        CUSTOM_COLORS[power] = new Vec3(cr, cg, cb);
    }
}