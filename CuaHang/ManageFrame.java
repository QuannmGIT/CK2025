import javax.swing.*;
import java.awt.*;

public class ManageFrame extends JFrame {
    
    private String loggedInUser; // Biến lưu username

    // Constructor nhận username
    public ManageFrame(String username){
        this.loggedInUser = username; // Lưu lại

        this.setTitle("Hanabi Cafe");
        this.setSize(1200, 600);
        ImageIcon icon = new ImageIcon(getClass().getResource("/ImageFile/HanabiIcon.png"));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel mainContainer = new JPanel(new BorderLayout());

        MenuPanel menuPanel = new MenuPanel();   
        
        // TRUYỀN USERNAME VÀO PERSON PANEL
        PersonPanel personPanel = new PersonPanel(this.loggedInUser); 

        mainContainer.add(menuPanel, BorderLayout.WEST); 
        mainContainer.add(personPanel, BorderLayout.CENTER);

        this.add(mainContainer);
    }
}