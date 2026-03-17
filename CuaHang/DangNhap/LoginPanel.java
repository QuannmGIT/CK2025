import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame; 

    public LoginPanel(MainFrame frame) {
        this.mainFrame = frame;
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        ImageIcon loginIcon = new ImageIcon(getClass().getResource("/ImageFile/LoginIcon.png"));
        Image loginImage = loginIcon.getImage();
        Image newLoginImage = loginImage.getScaledInstance(70, 60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledLoginIcon = new ImageIcon(newLoginImage);
        JLabel loginImageLabel = new JLabel(scaledLoginIcon);
        loginImageLabel.setBounds(150, 20, 100, 100);
        this.add(loginImageLabel);

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(125, 100, 200, 40); 
        this.add(titleLabel);

        JLabel titleLabel2 = new JLabel("Chào mừng bạn đến với Hanabi Cafe");
        titleLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        titleLabel2.setForeground(Color.BLACK);
        titleLabel2.setBounds(70, 130, 300, 40); 
        this.add(titleLabel2);


        //Taikhoan
        JLabel TaikhoangLabel = new JLabel("Tài Khoản:");
        TaikhoangLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        TaikhoangLabel.setBounds(147,180, 150, 30);
        this.add(TaikhoangLabel);
       
    
        JTextField TaikhoanField = new JTextField("Nhập tài khoản hoặc email");
        TaikhoanField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        TaikhoanField.setBounds(70, 215, 250, 35);
        TaikhoanField.setForeground(Color.GRAY); 
        TaikhoanField.setBorder(new RoundedBorder(10));
        this.add(TaikhoanField);        


        TaikhoanField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (TaikhoanField.getText().equals("Nhập tài khoản hoặc email")) {
                    TaikhoanField.setText("");
                    TaikhoanField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (TaikhoanField.getText().isEmpty()) {
                    TaikhoanField.setText("Nhập tài khoản hoặc email");
                    TaikhoanField.setForeground(Color.GRAY);
                }
            }
        });


        //Matkhau
        JLabel MatkhauLabel = new JLabel("Mật Khẩu:");
        MatkhauLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        MatkhauLabel.setBounds(147,270, 150, 30);
        this.add(MatkhauLabel);


        JPasswordField MatkhauField = new JPasswordField("123456789");
        MatkhauField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        MatkhauField.setBounds(70, 305, 250, 35);
        MatkhauField.setForeground(Color.GRAY);
        MatkhauField.setBorder(new RoundedBorder(10));
        this.add(MatkhauField);


        MatkhauField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String password = new String(MatkhauField.getPassword());
                if (password.equals("123456789")) {
                    MatkhauField.setText("");
                    MatkhauField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String password = new String(MatkhauField.getPassword());
                if (password.isEmpty()) {
                    MatkhauField.setText("123456789");
                    MatkhauField.setForeground(Color.GRAY);
                }
            }
        });

        //Checkbox
        JCheckBox CheckBox = new JCheckBox("Tôi Đồng Ý với điều khoản");
        CheckBox.setFocusable(false);
        CheckBox.setFont(new Font("HelveticaNeue", Font.BOLD, 13));        
        CheckBox.setBackground(Color.WHITE);
        CheckBox.setBounds(70, 360, 250, 25);
        this.add(CheckBox);


        ImageIcon checkIcon = new ImageIcon(getClass().getResource("/ImageFile/CheckIcon.png"));
        Image checkImage = checkIcon.getImage();
        Image newCheckImage = checkImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledCheckIcon = new ImageIcon(newCheckImage);
        CheckBox.setIcon(scaledCheckIcon);

        
        ImageIcon checkedIcon = new ImageIcon(getClass().getResource("/ImageFile/CheckedIcon.png"));
        Image checkedImage = checkedIcon.getImage();
        Image newCheckedImage = checkedImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledCheckedIcon = new ImageIcon(newCheckedImage);
        CheckBox.setSelectedIcon(scaledCheckedIcon);


        //Nut Dang Nhap
        JButton DangNhapBtn = new JButton("Đăng Nhập");
        DangNhapBtn.setFocusable(false);
        DangNhapBtn.setBackground(Color.WHITE);
        DangNhapBtn.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
        DangNhapBtn.setBounds(70, 400, 250, 40);
        DangNhapBtn.setBorder(new RoundedBorder(20));
        this.add(DangNhapBtn);
        

        DangNhapBtn.addActionListener(e -> {
            String user = TaikhoanField.getText().trim();
            String pass = new String(MatkhauField.getPassword()).trim();


            if (user.equals("Nhập tài khoản hoặc email"))  user = "";
            if (pass.equals("123456789")) pass = "";

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!CheckBox.isSelected()) {
                JOptionPane.showMessageDialog(this, "Vui lòng đồng ý điều khoản!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kết nối DB
            dbConnect db = new dbConnect();
            try (Connection conn = db.getConnection()) {
                if (conn == null) {
                    JOptionPane.showMessageDialog(this, "Không thể kết nối Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, user);
                    ps.setString(2, user);
                    ps.setString(3, pass);


                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String userDB = rs.getString("username"); 
                            new ManageFrame(userDB).setVisible(true);
                            this.mainFrame.dispose(); 
                        } else {
                            JOptionPane.showMessageDialog(this, "Sai username hoặc password!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối CSDL:\n" + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

}
}