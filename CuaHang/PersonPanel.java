import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonPanel extends JPanel {
    private JLabel lblFullName, lblEmail, lblUsername;
    private JLabel lblTotalOrder, lblPoints;
    
    // 1. Khai báo nút này ở đây để các hàm khác có thể điều khiển nó
    private JButton btnCreateAcc; 
    
    private String currentUsername;
    private ManageFrame manageFrame;

    public PersonPanel(ManageFrame frame, String username) {
        this.manageFrame = frame;
        this.currentUsername = username;

        this.setLayout(null);
        this.setBackground(Color.WHITE);


        JLabel lblTitle = new JLabel("Thông tin tài khoản");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setBounds(40, 20, 300, 40);
        this.add(lblTitle);


        JPanel profilePanel = new JPanel(null);
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setBounds(40, 80, 550, 350);
        profilePanel.setBorder(new LineBorder(Color.BLACK, 1));
        this.add(profilePanel);


        JLabel lblAvatar = new JLabel();
        lblAvatar.setBorder(new LineBorder(Color.GRAY));
        lblAvatar.setBounds(30, 30, 100, 100);
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon(getClass().getResource("/ImageFile/PersonIcon.png"));
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        lblAvatar.setIcon(new ImageIcon(img));

        profilePanel.add(lblAvatar);


        lblFullName = new JLabel("Đang tải...");
        lblFullName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblFullName.setBounds(150, 30, 350, 30);
        profilePanel.add(lblFullName);

        lblEmail = new JLabel("...");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(150, 70, 350, 25);
        profilePanel.add(lblEmail);

        lblUsername = new JLabel("...");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblUsername.setForeground(new Color(32, 178, 170));
        lblUsername.setBounds(150, 100, 350, 25);
        profilePanel.add(lblUsername);

        // Đường kẻ
        JSeparator sep = new JSeparator();
        sep.setBounds(0, 160, 550, 10);
        sep.setForeground(Color.BLACK);
        profilePanel.add(sep);

        // Box Thống kê
        JPanel boxOrder = createStatsBox("Tổng đơn hàng", "0");
        boxOrder.setBounds(30, 190, 200, 100);
        lblTotalOrder = (JLabel) boxOrder.getComponent(1);
        profilePanel.add(boxOrder);

        JPanel Point = createStatsBox("Điểm", "0");
        Point.setBounds(280, 190, 200, 100);
        lblPoints = (JLabel) Point.getComponent(1);
        profilePanel.add(Point);

        // Nút Đổi mật khẩu 
        JButton btnChangePass = new JButton("Đổi mật khẩu");
        btnChangePass.setBackground(Color.WHITE);
        btnChangePass.setBounds(620, 80, 150, 50);
        btnChangePass.setFocusable(false);
        this.add(btnChangePass);

        // Nút Tạo tài khoản 
        btnCreateAcc = new JButton("Tạo tài khoản");
        btnCreateAcc.setBackground(Color.WHITE);
        btnCreateAcc.setBounds(620, 150, 150, 50);
        btnCreateAcc.setFocusable(false);
        btnCreateAcc.setVisible(false); 
        
        this.add(btnCreateAcc);

        btnChangePass.addActionListener(e -> {
            this.manageFrame.showChangePasswordPanel();
        });
        btnCreateAcc.addActionListener(e -> {
            this.manageFrame.showSignInPanel(); 
        });

        // Gọi hàm load dữ liệu (Check quyền Admin ở trong hàm này)
        loadDataFromDatabase();
    }

    private JPanel createStatsBox(String title, String value) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        JLabel t = new JLabel(title, SwingConstants.CENTER);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JLabel v = new JLabel(value, SwingConstants.CENTER);
        v.setFont(new Font("Segoe UI", Font.BOLD, 28));
        panel.add(t); panel.add(v);
        return panel;
    }


    private void loadDataFromDatabase() {
        dbConnect db = new dbConnect();
        try (Connection conn = db.getConnection()) {
            if (conn == null) return;

            String sql = "SELECT u.full_name, u.email, u.username, u.role, " + // Thêm u.role
                         "IFNULL(a.Sum_order, 0) as Sum_order, " +
                         "IFNULL(a.Point, 0) as Point " +
                         "FROM users u " +
                         "LEFT JOIN average a ON u.user_id = a.user_id " +
                         "WHERE u.username = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, this.currentUsername);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Hiển thị thông tin
                        lblFullName.setText(rs.getString("full_name"));
                        lblEmail.setText(rs.getString("email"));
                        lblUsername.setText(rs.getString("username"));
                        lblTotalOrder.setText(String.valueOf(rs.getInt("Sum_order")));
                        lblPoints.setText(String.valueOf(rs.getInt("Point")));

                        // Phân quyền Admin
                        String role = rs.getString("role");
                        
                        if (role != null && role.equalsIgnoreCase("admin")) {
                            btnCreateAcc.setVisible(true);
                        } else {
                            btnCreateAcc.setVisible(false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblFullName.setText("Lỗi kết nối");
        }
    }
}