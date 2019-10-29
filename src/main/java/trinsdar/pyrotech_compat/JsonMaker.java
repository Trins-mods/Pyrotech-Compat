package trinsdar.pyrotech_compat;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import trinsdar.pyrotech_compat.block.rocks.BlockRockIgneous;
import trinsdar.pyrotech_compat.block.rocks.BlockRockMetamorphic;
import trinsdar.pyrotech_compat.block.rocks.BlockRockSedimentary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonMaker {
    private static String[] variants = {"Red_Granite", "Black_Granite", "Rhyolite", "Andesite", "Gabbro", "Basalt", "Komatiite", "Dacite", "Gneiss", "Eclogite", "Marble", "Quartzite", "Blue_Schist", "Green_Schist", "Soapstone", "Migmatite", "Chalk", "Shale", "Siltstone", "Dolomite", "Greywacke", "Chert"};
    private static String[] types = {"", "_Sandstone", "_sand"};
    public static void init(FMLPreInitializationEvent event){
        try {
            for(int i = 0; i < 8; i ++){
                JsonObject object = new JsonObject();
                File file = new File("/home/trinsdar/IdeaProjects/modprojects/Pyrotech-Compat/src/main/resources/assets/pyrotech_compat/recipes/cobble" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock", "") + ".json");
                file.createNewFile();
                JsonWriter writer = new JsonWriter(new FileWriter(file));
                writer.beginObject();
                writer.setIndent("  ");
                writer.name("type").value("minecraft:crafting_shaped");
                writer.name("pattern");
                writer.beginArray();
                    writer.value("RRR");
                    writer.value("RCR");
                    writer.value("RRR");
                writer.endArray();
                writer.name("key");
                writer.beginObject();
                    writer.name("C");
                    writer.beginObject();
                        writer.name("item").value("pyrotech:material");
                        writer.name("data").value(17);
                    writer.endObject();
                    writer.name("R");
                    writer.beginObject();
                        writer.name("item").value(PyrotechCompat.MODID + ":rock_igneous");
                        writer.name("data").value(i);
                    writer.endObject();
                writer.endObject();
                writer.name("result");
                writer.beginObject();
                    writer.name("item").value("undergroundbiome:igneous_cobblestone");
                    writer.name("count").value(1);
                    writer.name("data").value(i);
                writer.endObject();
                writer.endObject();
                writer.close();

                file = new File("/home/trinsdar/IdeaProjects/modprojects/Pyrotech-Compat/src/main/resources/assets/pyrotech_compat/recipes/cobble" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock", "") + ".json");
                file.createNewFile();
                writer = new JsonWriter(new FileWriter(file));
                writer.beginObject();
                writer.setIndent("  ");
                writer.name("type").value("minecraft:crafting_shaped");
                writer.name("pattern");
                writer.beginArray();
                    writer.value("RRR");
                    writer.value("RCR");
                    writer.value("RRR");
                writer.endArray();
                writer.name("key");
                writer.beginObject();
                    writer.name("C");
                    writer.beginObject();
                        writer.name("item").value("pyrotech:material");
                        writer.name("data").value(17);
                    writer.endObject();
                    writer.name("R");
                    writer.beginObject();
                        writer.name("item").value(PyrotechCompat.MODID + ":rock_metamorphic");
                        writer.name("data").value(i);
                    writer.endObject();
                writer.endObject();
                writer.name("result");
                writer.beginObject();
                    writer.name("item").value("undergroundbiome:metamorphic_cobblestone");
                    writer.name("count").value(1);
                    writer.name("data").value(i);
                writer.endObject();
                writer.endObject();
                writer.close();

                if (i == 0 || i == 4){
                    continue;
                }

                int meta = 0;
                if (i < 4){
                    meta = i - 1;
                } else {
                    meta = i - 2;
                }

                file = new File("/home/trinsdar/IdeaProjects/modprojects/Pyrotech-Compat/src/main/resources/assets/pyrotech_compat/recipes/stone" + getSedimentary(meta) + ".json");
                file.createNewFile();
                writer = new JsonWriter(new FileWriter(file));
                writer.beginObject();
                writer.setIndent("  ");
                writer.name("type").value("minecraft:crafting_shaped");
                writer.name("pattern");
                writer.beginArray();
                    writer.value("RRR");
                    writer.value("RCR");
                    writer.value("RRR");
                writer.endArray();
                writer.name("key");
                writer.beginObject();
                    writer.name("C");
                    writer.beginObject();
                        writer.name("item").value("pyrotech:material");
                        writer.name("data").value(17);
                    writer.endObject();
                    writer.name("R");
                    writer.beginObject();
                        writer.name("item").value(PyrotechCompat.MODID + ":rock_sedimentary");
                        writer.name("data").value(meta);
                    writer.endObject();
                writer.endObject();
                writer.name("result");
                writer.beginObject();
                    writer.name("item").value("undergroundbiome:sedimentary_stone");
                    writer.name("count").value(1);
                    writer.name("data").value(i);
                writer.endObject();
                writer.endObject();
                writer.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getSedimentary(int meta){
        return BlockRockSedimentary.EnumType.fromMeta(meta).getName().replace("rock", "");
    }

    public static void writeTextureObject(JsonWriter writer, String variant, String type) throws IOException {
        if (type.equals("_sandstone")){
            type = "_sandstone_top";
        }
        writer.name("textures");
        writer.beginObject();
            writer.name("all").value("undergroundbiomes:blocks/" + variant + type);
            writer.name("particle").value("undergroundbiomes:blocks/" + variant + type);
        writer.endObject();
    }

    public static void writeVariantsArray(JsonWriter writer) throws IOException{
        writer.name("normal");
        writer.beginArray();
            writeVariantsObjects(writer);
        writer.endArray();
    }

    public static void writeVariantsObjects(JsonWriter writer) throws IOException{
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_a");
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_a");
            writer.name("y").value(90);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_a");
            writer.name("y").value(180);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_a");
            writer.name("y").value(270);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_b");
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_b");
            writer.name("y").value(90);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_b");
            writer.name("y").value(180);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_b");
            writer.name("y").value(270);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_c");
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_c");
            writer.name("y").value(90);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_c");
            writer.name("y").value(180);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_c");
            writer.name("y").value(270);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_d");
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_d");
            writer.name("y").value(90);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_d");
            writer.name("y").value(180);
        writer.endObject();
        writer.beginObject();
            writer.name("model").value("pyrotech:rock_small_d");
            writer.name("y").value(270);
        writer.endObject();
    }
}
