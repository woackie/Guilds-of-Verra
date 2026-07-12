package com.guildsofverra;

import com.guildsofverra.bootstrap.GuildBootstrap;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuildsOfVerra implements ModInitializer {

	public static final String MOD_ID = "guilds_of_verra";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		GuildBootstrap.initialize();
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}