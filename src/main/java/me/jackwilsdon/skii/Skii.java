package me.jackwilsdon.skii;

import me.jackwilsdon.ecs.Engine;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.parser.ComponentBuilder;
import me.jackwilsdon.ecs.parser.ComponentTemplate;
import me.jackwilsdon.ecs.parser.EntityBuilder;
import me.jackwilsdon.ecs.parser.EntityLoader;
import me.jackwilsdon.ecs.parser.FileEntityLoader;
import me.jackwilsdon.ecs.parser.JsonEntityLoader;
import me.jackwilsdon.skii.components.Player;
import me.jackwilsdon.skii.components.Speed;

import java.io.IOException;

public class Skii {
    public static void main(String[] args) throws IOException {
        Engine engine = new Engine();
        FileEntityLoader entityLoader = new JsonEntityLoader();

        entityLoader.load(Skii.class.getResourceAsStream("/player.json"));

        Entity entity = EntityBuilder.build(engine, entityLoader.getEntityTemplate("Player"));

        System.out.println(entity.getComponent(Player.class).upKey);
    }
}
