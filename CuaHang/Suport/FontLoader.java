import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader {
    public static Font load(String path, float size) {
        try {
            File fontFile = new File(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
            return font;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Trả về font mặc định
            return new Font("Segoe UI", Font.BOLD, (int) size);
        }
    }
}