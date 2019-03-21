package utils;

import javax.swing.*;
import java.awt.*;

public class ImageUtil {
    public static Image getImage(String path){
        return new ImageIcon(ImageUtil.class.getResource(path)).getImage();
    }
}
