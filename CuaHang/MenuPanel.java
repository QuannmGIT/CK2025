import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    
    public MenuPanel() {
        this.setPreferredSize(new Dimension(200, 600)); // Chiều rộng cố định 200
        this.setBackground(new Color(230, 230, 230)); // Màu xám nhạt
        this.setLayout(null); // Layout tự do để chỉnh vị trí giống hình vẽ
        
        // Viền phải ngăn cách
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        // Tiêu đề Danh mục
        JLabel lblTitle = new JLabel("Danh mục");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(20, 30, 150, 40);
        this.add(lblTitle);

        // Nút Mặt Hàng
        JButton btnProducts = new JButton("Mặt hàng");
        btnProducts.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnProducts.setBackground(Color.WHITE);
        btnProducts.setBounds(10, 100, 180, 40);
        btnProducts.setFocusPainted(false);
        this.add(btnProducts);


        JButton btnAccount = new JButton("Tài khoản");
        btnAccount.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAccount.setBackground(Color.WHITE);
        btnAccount.setBounds(10, 160, 180, 40);
        btnAccount.setFocusPainted(false);
        btnAccount.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 
        this.add(btnAccount);


        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setBounds(10, 500, 180, 40);
        btnLogout.setFocusPainted(false);
        this.add(btnLogout);
        
        btnLogout.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            w.dispose();

        });
    }
}
