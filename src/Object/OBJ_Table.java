package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Table extends SuperObject{
    public OBJ_Table(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/stul_3x.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
