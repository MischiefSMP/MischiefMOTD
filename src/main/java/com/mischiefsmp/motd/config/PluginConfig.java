package com.mischiefsmp.motd.config;

import com.mischiefsmp.core.config.ConfigFile;
import com.mischiefsmp.core.config.ConfigManager;
import com.mischiefsmp.core.config.ConfigValue;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

@Getter
public class PluginConfig extends ConfigFile {

    @ConfigValue(path = "motd")
    private ArrayList<String> motd;

    public PluginConfig(Plugin plugin) {
        super(plugin, "config.yml", "config.yml");
        ConfigManager.init(this);
    }
}
