import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    private JPanel rightContainer; // Panel chứa Login và SignIn
    private LoginPanel loginPanel;
    private SignInPanel signInPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    public MainFrame() {
        // Cài đặt cơ bản
        this.setTitle("Hanabi Cafe");
        this.setSize(1000, 600);
        ImageIcon icon = new ImageIcon(getClass().getResource("/ImageFile/HanabiIcon.png"));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());


        BannerPanel banner = new BannerPanel();
        this.add(banner, BorderLayout.WEST);


        rightContainer = new JPanel();
        rightContainer.setLayout(new CardLayout()); 

        // 2 panel con
        loginPanel = new LoginPanel(this);
        signInPanel = new SignInPanel(this);

        // Thêm vào container
        rightContainer.add(loginPanel, "LOGIN");
        rightContainer.add(signInPanel, "REGISTER");

        this.add(rightContainer, BorderLayout.CENTER);
    }

    // Hàm chuyển sang Đăng Ký
    public void showRegisterPanel() {
        CardLayout cl = (CardLayout) rightContainer.getLayout();
        cl.show(rightContainer, "REGISTER");
    }

    // Hàm chuyển về Đăng Nhập
    public void showLoginPanel() {
        CardLayout cl = (CardLayout) rightContainer.getLayout();
        cl.show(rightContainer, "LOGIN");
    }
}