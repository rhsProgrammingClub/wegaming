import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Map {

    ArrayList<Ground> platforms = new ArrayList<>();
    Scene scene;
    File mapFile;
    String backgroundPath;

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

        try {
            FileReader fr = new FileReader(mapFile);
            String build = "";
            int ch;
            while ((ch=fr.read())!=-1) {
                build += (char)ch;
            }

            String[] lines = build.split("\n");
            for(String line : lines) {
                // split by lines now, thank god
                boolean isTraditional = line.matches("^(\\d.(\\d){1,} ){1,}(\\w){1,}$"); // should we use the traditional way of spawning assets?
                String[] spaceSplit = line.split(" ");
                if(isTraditional) {
                    double x = Double.parseDouble(spaceSplit[0]) * Main.width;
                    double y = Double.parseDouble(spaceSplit[1]) * Main.height;
                    int width = (int)(Double.parseDouble(spaceSplit[2]) * Main.width);
                    int height = (int)(Double.parseDouble(spaceSplit[3]) * Main.height);

                    Ground newGround = new Ground(x,y,width,height, spaceSplit[4]);
                    platforms.add(newGround);
                    newGround.setVisible(true);
                    scene.add(newGround);
                } else if(line.matches("^assets\\/(.){1,} background$")) {
                    backgroundPath = spaceSplit[0];
                }
            }

            fr.close();
                
        } catch (Exception e) {
            System.out.println("Caught an exception in map loading");
            System.out.println(e);
            return;
        }
        
        mapFile = null;
    }
}