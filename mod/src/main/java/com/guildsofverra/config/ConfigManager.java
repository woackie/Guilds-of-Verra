package com.guildsofverra.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.guildsofverra.GuildsOfVerra;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ConfigManager {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Type MINING_CONFIG_TYPE =
            new TypeToken<Map<String, Integer>>() { }.getType();

    private static final Path CONFIG_DIRECTORY = FabricLoader.getInstance()
            .getConfigDir()
            .resolve(GuildsOfVerra.MOD_ID);

    private static final Path MINING_CONFIG_PATH =
            CONFIG_DIRECTORY.resolve("mining.json");

    private static Map<ResourceLocation, Integer> miningExperience =
            Collections.emptyMap();

    private ConfigManager() {
    }

    public static void initialize() {
        miningExperience = loadMiningConfig();
        GuildsOfVerra.LOGGER.info(
                "Loaded {} Mining XP entries.",
                miningExperience.size()
        );
    }

    public static int getMiningExperience(ResourceLocation blockId) {
        return miningExperience.getOrDefault(blockId, 0);
    }

    private static Map<ResourceLocation, Integer> loadMiningConfig() {
        try {
            Files.createDirectories(CONFIG_DIRECTORY);

            if (Files.notExists(MINING_CONFIG_PATH)) {
                writeDefaultMiningConfig();
            }

            try (Reader reader = Files.newBufferedReader(MINING_CONFIG_PATH)) {
                Map<String, Integer> rawValues =
                        GSON.fromJson(reader, MINING_CONFIG_TYPE);

                if (rawValues == null) {
                    throw new JsonSyntaxException("Mining config cannot be null.");
                }

                Map<ResourceLocation, Integer> parsedValues = new LinkedHashMap<>();

                rawValues.forEach((idText, experience) -> {
                    ResourceLocation id = ResourceLocation.tryParse(idText);

                    if (id == null) {
                        GuildsOfVerra.LOGGER.warn(
                                "Ignoring invalid block ID in mining.json: {}",
                                idText
                        );
                        return;
                    }

                    if (experience == null || experience <= 0) {
                        GuildsOfVerra.LOGGER.warn(
                                "Ignoring non-positive Mining XP value for {}",
                                idText
                        );
                        return;
                    }

                    parsedValues.put(id, experience);
                });

                return Collections.unmodifiableMap(parsedValues);
            }
        } catch (IOException | JsonSyntaxException exception) {
            GuildsOfVerra.LOGGER.error(
                    "Could not load mining.json. Using built-in defaults.",
                    exception
            );

            return Collections.unmodifiableMap(parseDefaults());
        }
    }

    private static void writeDefaultMiningConfig() throws IOException {
        try (Writer writer = Files.newBufferedWriter(MINING_CONFIG_PATH)) {
            GSON.toJson(defaultMiningConfig(), MINING_CONFIG_TYPE, writer);
        }
    }

    private static Map<ResourceLocation, Integer> parseDefaults() {
        Map<ResourceLocation, Integer> parsed = new LinkedHashMap<>();

        defaultMiningConfig().forEach((idText, experience) -> {
            ResourceLocation id = ResourceLocation.tryParse(idText);

            if (id != null) {
                parsed.put(id, experience);
            }
        });

        return parsed;
    }

    private static Map<String, Integer> defaultMiningConfig() {
        Map<String, Integer> values = new LinkedHashMap<>();

        values.put("minecraft:stone", 1);
        values.put("minecraft:deepslate", 1);

        values.put("minecraft:coal_ore", 4);
        values.put("minecraft:deepslate_coal_ore", 4);

        values.put("minecraft:copper_ore", 5);
        values.put("minecraft:deepslate_copper_ore", 5);

        values.put("minecraft:iron_ore", 8);
        values.put("minecraft:deepslate_iron_ore", 8);

        values.put("minecraft:gold_ore", 12);
        values.put("minecraft:deepslate_gold_ore", 12);

        values.put("minecraft:redstone_ore", 10);
        values.put("minecraft:deepslate_redstone_ore", 10);

        values.put("minecraft:lapis_ore", 12);
        values.put("minecraft:deepslate_lapis_ore", 12);

        values.put("minecraft:diamond_ore", 25);
        values.put("minecraft:deepslate_diamond_ore", 25);

        values.put("minecraft:emerald_ore", 30);
        values.put("minecraft:deepslate_emerald_ore", 30);

        values.put("minecraft:nether_quartz_ore", 6);
        values.put("minecraft:nether_gold_ore", 8);
        values.put("minecraft:ancient_debris", 40);

        return values;
    }
}
