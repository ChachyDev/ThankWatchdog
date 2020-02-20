package club.chachy;

import cc.hyperium.Hyperium;
import cc.hyperium.event.EventBus;
import cc.hyperium.event.InvokeEvent;
import cc.hyperium.event.client.InitializationEvent;
import cc.hyperium.event.network.chat.ChatEvent;
import cc.hyperium.handlers.handlers.HypixelDetector;
import cc.hyperium.internal.addons.IAddon;
import net.minecraft.client.Minecraft;

// All addons must implement IAddon
public class ThankWatchDog implements IAddon {
    private static final String WATCHDOG_BAN_TRIGGER = "A player has been removed from your game for hacking or abuse. Thanks for reporting it!";
    private static final String WATCHDOG_ANNOUNCEMENT_TRIGGER = "[WATCHDOG ANNOUNCEMENT]";
    private static final String THANK_WATCHDOG_MESSAGE = "/achat Thanks Watchdog!";

    /**
     * Invoked once the plugin has successfully loaded
     * {@link cc.hyperium.internal.addons.AddonMinecraftBootstrap#init}
     */
    @Override
    public void onLoad() {
        // Log that the addon has loaded
        Hyperium.LOGGER.info("Successfully loaded ThankWatchdog!");
        EventBus.INSTANCE.register(this);
    }

    @InvokeEvent
    public void onInitialization(InitializationEvent event) {
        // Register a command on initialization
        Hyperium.INSTANCE.getHandlers().getCommandHandler().registerCommand(new ToggleCommand());
        EventBus.INSTANCE.register(this);
    }

    @InvokeEvent
    public void onChat(ChatEvent e) {
        if ((e.getChat().getUnformattedText().contains(WATCHDOG_BAN_TRIGGER) || e.getChat().getUnformattedText().contains(WATCHDOG_ANNOUNCEMENT_TRIGGER)) && Config.INSTANCE.isEnabled() && HypixelDetector.getInstance().isHypixel()) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(THANK_WATCHDOG_MESSAGE);
        }
    }

    /**
     * Invoked once the game has been closed
     * this is executed at the start of {@link net.minecraft.client.Minecraft#shutdown}
     */
    @Override
    public void onClose() {
        // Log that the addon is being closed
        Hyperium.LOGGER.info("Closing...");
    }

    /**
     * Invoked on debug call. Can be used to add things into crash reports
     * <p>
     * This does not need to be overriden if it's not needed
     */
    @Override
    public void sendDebugInfo() {
    }
}
