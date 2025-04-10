package xyz.raitaki.rweapons;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.raitaki.rweapons.commands.TestParticleCommand;
import xyz.raitaki.rweapons.utils.console.JDAMessageListener;
import xyz.raitaki.rweapons.utils.console.LogAppender;
import xyz.raitaki.rweapons.weapon.Events;
import xyz.raitaki.rweapons.weapon.skills.ConsoleSkill;
import xyz.raitaki.rweapons.weapon.weapons.Allah1;

import java.io.File;
import java.util.UUID;

import static org.bukkit.Bukkit.getPluginManager;

public final class RWeapon extends JavaPlugin {

    public static Object main;
    @Getter private static RWeapon instance;
    @Getter private static xyz.raitaki.rweapons.Json jsonConfig;
    @Getter private static JDA jda;
    private Logger logger;
    private LogAppender appender;

    private boolean discordEnabled = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getPluginManager().registerEvents(new Events(), this);
        new Allah1(UUID.fromString("49d24d8f-3986-4c8e-a580-c1ee83991b90"));
        getCommand("testparticle").setExecutor(new TestParticleCommand());

        logger = (Logger) LogManager.getRootLogger();
        appender = new LogAppender();

        jsonConfig = new Json(new File(this.getDataFolder().getAbsolutePath() + "/config.json"));

        String token = jsonConfig.getOrSetDefault("discord.token", "token");

        if(discordEnabled) {
            if (token.length() < 10) {
                getLogger().warning("§4Please set a valid discord bot token in the config.json file!");
            } else {
                jda = JDABuilder.createDefault(token)
                        .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
                        .addEventListeners(new JDAMessageListener()).build();
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.error(appender);
        for(ConsoleSkill consoleSkill : ConsoleSkill.getConsoleViewers()){
            consoleSkill.getViewer().removeDisplays();
        }
        if(discordEnabled)
            jda.shutdownNow();
    }
}
