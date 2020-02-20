package club.chachy;

import cc.hyperium.Hyperium;
import cc.hyperium.commands.BaseCommand;
import net.minecraft.util.EnumChatFormatting;

public class ToggleCommand implements BaseCommand {

    /**
     * Gets the name of the command
     */
    @Override
    public String getName() {
        return "thankwatchdog";
    }

    /**
     * Gets the usage string for the command.
     */
    @Override
    public String getUsage() {
        return "/" + getName();
    }

    /**
     * Callback when the command is invoked
     *
     * @param args
     */
    @Override
    public void onExecute(String[] args) {
        Config.INSTANCE.setEnabled(!Config.INSTANCE.isEnabled());
        String toggle = getEnabledOrDisabled(Config.INSTANCE.isEnabled());
        Hyperium.INSTANCE.getHandlers().getGeneralChatHandler().sendMessage("Successfully " + toggle + " Thank Watchdog");
    }

    public String getEnabledOrDisabled(boolean b) {
        return b ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled";
    }
}
