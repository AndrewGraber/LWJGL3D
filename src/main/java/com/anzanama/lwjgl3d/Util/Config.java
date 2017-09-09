package com.anzanama.lwjgl3d.Util;

import java.io.*;
import java.util.HashMap;

public class Config {
    private static String fileName;
    private static HashMap<String, Boolean> bools = new HashMap<>();
    private static HashMap<String, Integer> ints = new HashMap<>();
    private static HashMap<String, Float> floats = new HashMap<>();

    public static void initialize(String nameOfFile) {
        fileName = nameOfFile;
        readConfig();
    }

    public static boolean getBool(String name) {
        return bools.get(name);
    }

    public static int getInt(String name) {
        return ints.get(name);
    }

    public static float getFloat(String name) {
        return floats.get(name);
    }

    public static void reload() {
        readConfig();
    }

    private static void readConfig() {
        try {
            File file = new File("./src/main/resources/" + fileName);
            if(!file.exists()) {
                System.out.println("Config file not found. Generating a new one...");
                generateDefaultConfig(file);
            }
            InputStream fis = new FileInputStream(file);
            BufferedReader r = new BufferedReader(new InputStreamReader(fis));
            String s = "";
            while((s = r.readLine()) != null) {
                if(s.startsWith("#") || s.equals("")) continue;
                char data_type = s.charAt(0);
                String title = s.substring(s.indexOf(':')+1, s.indexOf('='));
                String value = s.substring(s.indexOf('=')+1);

                if(s.startsWith("i")) {
                    //Read in an integer value
                    ints.put(title, Integer.parseInt(value));
                } else if(s.startsWith("b")) {
                    //Read in a boolean value
                    bools.put(title, Boolean.parseBoolean(value));
                } else if(s.startsWith("f")) {
                    //Read in a floating point value
                    floats.put(title, Float.parseFloat(value));
                } else {
                    throw new IOException("Invalid line beginning: " + s.substring(0, 1));
                }
            }
            r.close();
            fis.close();
        } catch (IOException e) {
            System.err.println("Error occurred when attempting to read config file. File may be corrupted and need deletion");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void generateDefaultConfig(File file) {
        try {
            if(file.exists()) file.delete();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.print("#This is the configuration file. If you muck this all up, just delete the file.\n" +
                    "#It will be recreated with default values.\n" +
                    "\n" +
                    "#The Width of the screen (Default: 1280)\n" +
                    "i:screen_width=1280\n" +
                    "\n" +
                    "#The height of the screen (Default: 720)\n" +
                    "i:screen_height=720\n" +
                    "\n" +
                    "#The maximum value for the FPS (Default: 60)\n" +
                    "i:fps_cap=60\n" +
                    "\n" +
                    "#The Sensitivity of the mouse (Default: 0.25f)\n" +
                    "f:mouse_sensitivity=0.25f\n" +
                    "\n" +
                    "#The Sensitivity of the controller joysticks (Default: 1.0f)\n" +
                    "f:controller_sensitivity=1.0f\n" +
                    "\n" +
                    "#The distance one scroll of the mouse wheel (or controller zoom)\n" +
                    "#moves the camera (Default: 0.1f)\n" +
                    "f:zoom_distance=0.1f\n" +
                    "\n" +
                    "#The amount (in degrees) to change pitch of follow camera by for each\n" +
                    "#pixel moved (Default: 0.1f)\n" +
                    "f:pitch_change=0.1f\n" +
                    "\n" +
                    "#The amount (in degrees) to change rotation of follow camera around player\n" +
                    "#for each pixel moved (Default: 0.3f)\n" +
                    "f:rotate_change=0.3f\n" +
                    "\n" +
                    "#The Field of View of the camera (Default: 70f)\n" +
                    "f:camera_fov=70f\n" +
                    "\n" +
                    "#The distance of the near clipping plane (Default: 0.1f)\n" +
                    "f:near_clip=0.1f\n" +
                    "\n" +
                    "#The render distance of the camera (Default: 1000f)\n" +
                    "f:render_distance=1000f\n" +
                    "\n" +
                    "#The Movement speed of the camera (Default: 0.1f)\n" +
                    "f:camera_move_speed=0.1f\n" +
                    "\n" +
                    "#The Movement speed of the player (Default: 0.2f)\n" +
                    "f:player_move_speed=0.2f\n" +
                    "\n" +
                    "#The ambient light (brightness) in the world. This is the\n" +
                    "#amount of light that will show when no light source is in\n" +
                    "#range. 0.0f would be complete darkness. 1.0f would be complete\n" +
                    "#light (no shadows). (Default: 0.2f)\n" +
                    "f:ambient_light=0.2f\n" +
                    "\n" +
                    "#The density of the fog that slowly fades objects away (Default: 0.0035f)\n" +
                    "f:fog_density=0.0035f\n" +
                    "\n" +
                    "#The gradient of the fog (Default: 5f)\n" +
                    "f:fog_gradient=5f\n" +
                    "\n" +
                    "#The color of the sky - in red, green, and blue in that order.\n" +
                    "#(Default: 0.7f, 0.9f, 1.0f)\n" +
                    "f:sky_red=0.7f\n" +
                    "f:sky_green=0.9f\n" +
                    "f:sky_blue=1.0f");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
