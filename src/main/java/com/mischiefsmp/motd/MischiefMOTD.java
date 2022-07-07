package com.mischiefsmp.motd;

import com.mischiefsmp.motd.config.PluginConfig;
import com.mischiefsmp.motd.events.JoinEvent;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

public class MischiefMOTD extends JavaPlugin {
    @Getter
    private static MischiefMOTD instance;

    @Getter
    private static PluginConfig pluginConfig;

    public void ensureCore(boolean required) {
        if(getServer().getPluginManager().getPlugin("MischiefCore") != null) return;
        getLogger().log(Level.INFO, "Downloading MischiefCore...");
        File pluginFile = new File(new File("").getAbsolutePath() + File.separator + "plugins" + File.separator + "MischiefCore.jar");
        try {
            InputStream in = new URL("https://github.com/MischiefSMP/MischiefCore/releases/latest/download/MischiefCore.jar").openStream();
            Files.copy(in, pluginFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            getServer().getPluginManager().enablePlugin(getServer().getPluginManager().loadPlugin(pluginFile));
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Error downloading MischiefCore! Message: " + e.getMessage());
            if(required) getServer().shutdown();
        }
        getLogger().log(Level.INFO, "Done downloading MischiefCore!");
    }

    @Override
    public void onEnable() {
        ensureCore(true);

        instance = this;
        pluginConfig = new PluginConfig(this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    }
}
