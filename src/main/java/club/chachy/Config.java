package club.chachy;

import cc.hyperium.config.ConfigOpt;

public class Config {
    public static Config INSTANCE = new Config();

    @ConfigOpt
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
