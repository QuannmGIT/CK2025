import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuFrame frame = new MenuFrame();
            frame.setVisible(true);
        });
    }
    public MenuFrame(){
        this.setTitle("Menu");
        this.setSize(1200, 600);
        ImageIcon icon = new ImageIcon(getClass().getResource("/ImageFile/HanabiIcon.png"));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

// TẠO CONTAINER 
        JPanel mainContainer = new JPanel(new BorderLayout());

        // 2 Panel con
        MenuPanel menuPanel = new MenuPanel();   
        PricePanel pricePanel = new PricePanel(); 

        mainContainer.add(menuPanel, BorderLayout.CENTER); 
        mainContainer.add(pricePanel, BorderLayout.EAST);

        this.add(mainContainer);
    }
}
