package TreasureHunt.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Boot extends SuperObject {

    public OBJ_Boot() {
        name = "Boot";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/objects/boots.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
