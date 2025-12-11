import javax.swing.*;
import java.awt.*;

public class ManageFrame extends JFrame {
    
    private String loggedInUser; // Biến lưu username
    private JPanel bodyPanel;    // Panel chứa nội dung thay đổi (Ở giữa)

    // Constructor nhận username
    public ManageFrame(String username){
        this.loggedInUser = username; // Lưu lại

        // --- Cài đặt Frame ---
        this.setTitle("Hanabi Cafe - Xin chào " + username);
        this.setSize(1200, 600);
        
        // Thử set Icon, nếu lỗi thì bỏ qua
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/ImageFile/HanabiIcon.png"));
            this.setIconImage(icon.getImage());
        } catch (Exception e) {}
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // --- Layout chính ---
        JPanel mainContainer = new JPanel(new BorderLayout());

        // 1. Menu bên trái (Cố định)
        MenuPanel menuPanel = new MenuPanel();   
        mainContainer.add(menuPanel, BorderLayout.WEST); 

        // 2. Body bên phải (Thay đổi được)
        bodyPanel = new JPanel(new BorderLayout());
        mainContainer.add(bodyPanel, BorderLayout.CENTER);

        // Mặc định ban đầu hiện PersonPanel
        showPersonPanel();

        this.add(mainContainer);
    }

    // --- HÀM CHUYỂN ĐỔI GIAO DIỆN ---

    // Hàm 1: Hiển thị form Đăng ký (SignInPanel)
    public void showSignInPanel() {
        bodyPanel.removeAll(); // Xóa nội dung cũ
        
        // Tạo SignInPanel mới, truyền 'this' để nó có thể gọi nút Quay lại
        SignInPanel signIn = new SignInPanel(this); 
        
        bodyPanel.add(signIn, BorderLayout.CENTER); // Thêm vào body
        bodyPanel.revalidate(); // Vẽ lại giao diện
        bodyPanel.repaint();
    }

    // Hàm 2: Hiển thị thông tin cá nhân (PersonPanel)
    // Hàm này được gọi khi khởi động app HOẶC khi bấm nút "Quay lại" từ form đăng ký
    public void showPersonPanel() {
        bodyPanel.removeAll(); // Xóa nội dung cũ
        
        // Tạo PersonPanel mới, truyền 'this' và username
        PersonPanel person = new PersonPanel(this, this.loggedInUser);
        
        bodyPanel.add(person, BorderLayout.CENTER); // Thêm vào body
        bodyPanel.revalidate(); // Vẽ lại giao diện
        bodyPanel.repaint();
    }

    public void showChangePasswordPanel() {
    bodyPanel.removeAll();
    
    // Truyền 'this' và 'loggedInUser' để panel biết user nào đang đổi pass
    ChangePasswordPanel changePass = new ChangePasswordPanel(this, this.loggedInUser);
    
    bodyPanel.add(changePass, BorderLayout.CENTER);
    bodyPanel.revalidate();
    bodyPanel.repaint();
}
}