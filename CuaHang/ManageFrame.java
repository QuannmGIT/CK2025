import javax.swing.*;
import java.awt.*;

public class ManageFrame extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManageFrame frame = new ManageFrame();
            frame.setVisible(true);
        });
    }
    public ManageFrame(){
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
        PersonPanel pricePanel = new PersonPanel(); 

        mainContainer.add(menuPanel, BorderLayout.WEST); 
        mainContainer.add(pricePanel, BorderLayout.EAST);

        this.add(mainContainer);
    }
}