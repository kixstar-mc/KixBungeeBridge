package me.kixstar.kixbungeebridge;

import me.kixstar.kixbungeebridge.command.NickCommand;
import me.kixstar.kixbungeebridge.feature.NicknameService;
import me.kixstar.kixbungeebridge.feature.ServerCommandService;
import me.kixstar.kixbungeebridge.feature.TeleportTransaction;
import me.kixstar.kixbungeebridge.rabbitmq.RabbitMQ;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public final class KixBungeeBridge extends Plugin {

    private static KixBungeeBridge plugin;

    private static Configuration config;

    @Override
    public void onEnable() {
        plugin = this;

        //connect to RabbitMQ
        RabbitMQ.bind(Config.getServerHandle());

        //connect services that depend on RabbitMQ
        NicknameService.register();
        ServerCommandService.register();
        TeleportTransaction.register();

        this.getProxy().getPluginManager().registerCommand(this, new NickCommand());

    }

    @Override
    public void onDisable() {

        ServerCommandService.unregister();
        TeleportTransaction.unregister();
        NicknameService.unregister();

        RabbitMQ.unbind();

        plugin = null;
    }

    public static KixBungeeBridge getInstance() {
        return plugin;
    }
}