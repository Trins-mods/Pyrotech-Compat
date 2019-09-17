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
    private static String[] variants = {"andesite", "basalt", "black_granite", "blue_schist", "chalk", "dacite", "dolomite", "eclogite", "gabbro", "gneiss", "green_schist", "greywacke", "komalite", "lignite", "limestone", "marble", "migmatite", "red_granite", "rhyolite", "siltstone", "soapstone"};
    private static String[] types = {"", "_sandstone", "_sand"};
    public static void init(FMLPreInitializationEvent event){
        for (String type : types){
            for (String variant : variants){
                try {
                    JsonObject object = new JsonObject();
                    File file = new File(event.getModConfigurationDirectory(),"pyresources/rock_" + variant + type + ".json");
                    file.createNewFile();
                    JsonWriter writer = new JsonWriter(new FileWriter(file));
                    writer.beginObject();
                    writer.setIndent("  ");
                    writer.name("parent").value("item/generated");
                    writer.name("textures");
                    writer.beginObject();
                        writer.name("layer0").value("pyrotech_compat:items/rock_" + variant + type);
                    writer.endObject();
                    writer.endObject();
                    writer.close();

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
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
