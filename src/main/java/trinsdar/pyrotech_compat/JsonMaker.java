package trinsdar.pyrotech_compat;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonMaker {
    private static String[] variants = {"Red_Granite", "Black_Granite", "Rhyolite", "Andesite", "Gabbro", "Basalt", "Komatiite", "Dacite", "Gneiss", "Eclogite", "Marble", "Quartzite", "Blue_Schist", "Green_Schist", "Soapstone", "Migmatite", "Chalk", "Shale", "Siltstone", "Dolomite", "Greywacke", "Chert"};
    private static String[] types = {"", "_Sandstone", "_sand"};
    public static void init(FMLPreInitializationEvent event){
        try {
            JsonObject object = new JsonObject();
            File file = new File("/home/trinsdar/IdeaProjects/modprojects/Pyrotech-Compat/src/main/resources/assets/pyrotech_compat/lang/en_us.json");
            file.createNewFile();
            JsonWriter writer = new JsonWriter(new FileWriter(file));
            writer.beginObject();
            writer.setIndent("  ");
            for (String type : types){
                for (String variant : variants){
                    if (type.equalsIgnoreCase("_sand")){
                        writer.name("tile.pyrotech_compat.rock_" + variant.toLowerCase() + type.toLowerCase() + ".name").value(variant.replace('_',' ')  + " Sand Pile");
                    } else {
                        writer.name("tile.pyrotech_compat.rock_" + variant.toLowerCase() + type.toLowerCase() + ".name").value(variant.replace('_',' ') + type.replace('_', ' ') + " Rock");
                    }

                }
            }
            writer.endObject();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
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
