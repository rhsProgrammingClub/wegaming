import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    ArrayList<Ground> platforms;
    Scene scene;
    File mapFile;

    public Map() {

    }

    public Map(String filename, Scene scene) {
        mapFile = new File(filename);
        this.scene = scene;
        initialize();
    }

    public void initialize() {
        if (mapFile.equals(null))
            return;
        Scanner mapScan;
        try {
            mapScan = new Scanner(mapFile);
        } catch (FileNotFoundException ex) {
            System.out.println(mapFile.getAbsolutePath() + " cannot be found.");
            return;
        }

        platforms = new ArrayList<>();

        while (mapScan.hasNextLine()) {
            platforms.add(new Ground(mapScan.nextDouble()*Main.width, mapScan.nextDouble()*Main.height,
                (int)(mapScan.nextDouble()*Main.width), (int)(mapScan.nextDouble()*Main.height), 
                mapScan.next()));
            platforms.get(platforms.size()-1).setVisible(true);
            scene.add(platforms.get(platforms.size()-1));
        }

        mapScan.close();
        mapFile = null;
        mapScan = null;
    }
}