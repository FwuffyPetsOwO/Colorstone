package net.fwuffypetsowo.colorstone;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ColorstoneMain implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("colorstone");

    public void onInitialize() {
        LOGGER.info("Redstone has been colorized");
        ColorUtil.CUSTOM_COLORS = ColorUtil.RED_COLORS;
    }
}