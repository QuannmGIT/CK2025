import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1, 15, 3, 15);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.GRAY); // Màu viền
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}



public class DangNhap {
    // Hàm Main để chạy chương trình
    public static void main(String[] args){
        // Chạy trên luồng giao diện để mượt mà hơn
        SwingUtilities.invokeLater(() -> new DangNhap());
    }
    
    // Hàm tải font từ file
    public static Font loadFont(String path, float size) {
    try {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        return font;
    } catch (Exception e) {
        e.printStackTrace();
        return new Font("Serif", Font.BOLD, (int) size);
    }
}

    public DangNhap() {
        // Thiết lập khung cửa sổ chính
        JFrame DNFrame = new JFrame("Đăng Nhập");
        DNFrame.setSize(1000, 600); // Kích thước cửa sổ

        // Đặt tên cửa sổ
        DNFrame.setTitle("Hanabi");

        // Đặt Icon cửa sổ
        ImageIcon icon = new ImageIcon(getClass().getResource("/ImageFile/HanabiIcon.png"));
        DNFrame.setIconImage(icon.getImage());

        // Đóng ứng dụng khi cửa sổ được đóng
        DNFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // Không cho phép thay đổi kích thước cửa sổ
        DNFrame.setResizable(false);

        // Căn giữa cửa sổ
        DNFrame.setLocationRelativeTo(null);

        // Chia Khung thành 2 phần: Trái và Phải
        DNFrame.setLayout(new BorderLayout());



    // Tạo panel bên trái
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(600,600));

        // thêm ảnh nền vào background
        leftPanel.setBackground(new Color(32, 178, 170));
    
        // Dùng BoxLayout để xếp các thành phần theo chiều dọc (Y_AXIS)
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Tạo khoảng trống phía trên để đẩy nội dung xuống giữa
        leftPanel.add(Box.createVerticalGlue());

        // Tạo background hình ảnh 

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new OverlayLayout(imagePanel));

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/ImageFile/CuaHangImage2.png"));
        Image image = originalIcon.getImage();
        Image newImage = image.getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(newImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo trục ngang
        imageLabel.setAlignmentY(Component.CENTER_ALIGNMENT); // Căn giữa theo trục dọc



        // Tạo chữ đè lên hình ảnh


    // Tạo panel chứa chữ 
        JPanel textPanel = new JPanel();

        JLabel textLabel = new JLabel("HANABI CAFE");
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("AmaticSC-Regular", Font.BOLD, 70));
        textLabel.setFont(loadFont("Fonts/AmaticSC-Regular.ttf", 70));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo trục ngang

        JLabel textLabel2 = new JLabel("FIND THE PERFECT COFFEE                        ");
        textLabel2.setForeground(Color.BLACK);   
        textLabel2.setFont(new Font("Dancing Script", Font.BOLD, 25));;
        textLabel2.setFont(loadFont("fonts/DancingScript-Regular.ttf", 25));
        textLabel2.setAlignmentX(Component.CENTER_ALIGNMENT); 

        JLabel textLabel3 = new JLabel("Những bậc thầy về cà phê của chúng tôi đã đúc kết nhiều năm" );
        textLabel3.setForeground(Color.BLACK);   
        textLabel3.setFont(new Font("Dancing Script", Font.BOLD, 20));
        textLabel3.setFont(loadFont("fonts/DancingScript-Regular.ttf", 20));
        textLabel3.setAlignmentX(Component.CENTER_ALIGNMENT); 

        JLabel textLabel4 = new JLabel("kinh nghiệm, nếm thử cà phê của họ trong ba câu hỏi đơn giản");
        textLabel4.setForeground(Color.BLACK);   
        textLabel4.setFont(new Font("Dancing Script", Font.BOLD, 20));
        textLabel4.setFont(loadFont("fonts/DancingScript-Regular.ttf", 20));
        textLabel4.setAlignmentX(Component.CENTER_ALIGNMENT); 

        JLabel textLabel5 = new JLabel("để giúp bạn tìm loại cà phê mà bạn chắc hẳn sẽ thích.           ");
        textLabel5.setForeground(Color.BLACK);   
        textLabel5.setFont(new Font("Dancing Script", Font.BOLD, 20));
        textLabel5.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel5.setFont(loadFont("fonts/DancingScript-Regular.ttf", 20));


        // Sắp xếp vị trí chữ trong textPanel
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(textLabel);
        // khoảng cách giữa 2 dòng chữ
        textPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        textPanel.add(textLabel2);
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textPanel.add(textLabel3);
        textPanel.add(textLabel4);
        textPanel.add(textLabel5);
        textPanel.add(Box.createRigidArea(new Dimension(0, 280))); 
        textPanel.add(Box.createVerticalGlue());
 

        imagePanel.add(textPanel);
        imagePanel.add(imageLabel);

        leftPanel.add(imagePanel);


    // Tạo panel bên phải
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null); // Layout null để dùng setBounds tùy ý


        ImageIcon loginIcon = new ImageIcon(getClass().getResource("/ImageFile/LoginIcon2.png"));
        Image loginImage = loginIcon.getImage();
        Image newLoginImage = loginImage.getScaledInstance(70, 60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledLoginIcon = new ImageIcon(newLoginImage);
        JLabel loginImageLabel = new JLabel(scaledLoginIcon);
        loginImageLabel.setBounds(150, 20, 100, 100);
        rightPanel.add(loginImageLabel);

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(125, 100, 200, 40); 
        rightPanel.add(titleLabel);

        JLabel titleLabel2 = new JLabel("Chào mừng bạn đến với Hanabi Cafe");
        titleLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        titleLabel2.setForeground(Color.BLACK);
        titleLabel2.setBounds(70, 130, 300, 40); 
        rightPanel.add(titleLabel2);

    //Tạo tài khoảng

        // Label Tài Khoản
        JLabel TaikhoangLabel = new JLabel("Tài Khoản:");
        TaikhoangLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        TaikhoangLabel.setBounds(147,180, 150, 30);
        rightPanel.add(TaikhoangLabel);
       
        // Tài khoảng JTextField - ô nhập liệu
        JTextField TaikhoanField = new JTextField();
        TaikhoanField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        TaikhoanField.setBounds(70, 215, 250, 35); 
        TaikhoanField.setBorder(new RoundedBorder(10));
        rightPanel.add(TaikhoanField);        

        // tạo dòng chữ gợi ý trong ô nhập tài khoản
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
                    TaikhoanField.setForeground(Color.GRAY);
                    TaikhoanField.setText("Nhập tài khoản hoặc email");
                }
            }
        });


    // Tạo mật khẩu

        // Mật Khẩu JLabel - chữ hiển thị
        JLabel MatkhauLabel = new JLabel("Mật Khẩu:");
        MatkhauLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        MatkhauLabel.setBounds(147,270, 150, 30);
        rightPanel.add(MatkhauLabel);

        // Mật khẩu JPasswordField - ô nhập liệu
        JPasswordField MatkhauField = new JPasswordField();
        MatkhauField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        MatkhauField.setBounds(70, 305, 250, 35);
        MatkhauField.setBorder(new RoundedBorder(10));
        rightPanel.add(MatkhauField);

        // tạo dòng chữ gợi ý trong ô nhập mật khẩu
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
                    MatkhauField.setForeground(Color.GRAY);
                    MatkhauField.setText("123456789");
                }
            }
        });


    // box tick
        JCheckBox CheckBox = new JCheckBox("Tôi Đồng Tình với điều khoản");
        CheckBox.setFont(new Font("Segoe UI", Font.BOLD, 13));
        CheckBox.setBackground(Color.WHITE);
        CheckBox.setBounds(70, 360, 250, 25);
        rightPanel.add(CheckBox);
    // Ảnh cho box tick
        ImageIcon checkIcon = new ImageIcon(getClass().getResource("/ImageFile/CheckIcon.png"));
        Image checkImage = checkIcon.getImage();
        Image newCheckImage = checkImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledCheckIcon = new ImageIcon(newCheckImage);
        CheckBox.setIcon(scaledCheckIcon);
    // Ảnh khi được chọn
        ImageIcon checkedIcon = new ImageIcon(getClass().getResource("/ImageFile/CheckedIcon.png"));
        Image checkedImage = checkedIcon.getImage();
        Image newCheckedImage = checkedImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledCheckedIcon = new ImageIcon(newCheckedImage);
        CheckBox.setSelectedIcon(scaledCheckedIcon);

    // Nút đăng nhập
        JButton DangNhapBtn = new JButton("Đăng Nhập");
        DangNhapBtn.setBackground(Color.WHITE);
        DangNhapBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        DangNhapBtn.setBounds(70, 400, 100, 40);
        DangNhapBtn.setBorder(new RoundedBorder(20));
        rightPanel.add(DangNhapBtn);
        

    // Nút đăng ký
        JButton DangKyBtn = new JButton("Đăng Ký");
        DangKyBtn.setBackground(Color.WHITE);
        DangKyBtn.setForeground(Color.BLACK);
        DangKyBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        DangKyBtn.setBounds(200, 400, 100, 40);
        DangKyBtn.setBorder(new RoundedBorder(20));
        rightPanel.add(DangKyBtn);

        DangNhapBtn.addActionListener(e -> {
            String user = TaikhoanField.getText().trim();
            String pass = new String(MatkhauField.getPassword()).trim();

            // Xử lý loại bỏ Placeholder
            if (user.equals("Nhập tài khoản hoặc email"))  user = "";
            if (pass.equals("123456789")) pass = "";

            if (user.isEmpty() || pass.isEmpty()) {
                // SỬA LỖI: Dùng DNFrame thay vì this
                JOptionPane.showMessageDialog(DNFrame, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!CheckBox.isSelected()) {
                JOptionPane.showMessageDialog(DNFrame, "Vui lòng đồng ý điều khoản!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kết nối DB
            dbConnect db = new dbConnect();
            try (Connection conn = db.getConnection()) {
                if (conn == null) {
                    JOptionPane.showMessageDialog(DNFrame, "Không thể kết nối Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "SELECT * FROM user WHERE (username = ? OR email = ?) AND password = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, user);
                    ps.setString(2, user);
                    ps.setString(3, pass);


                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String fullName = rs.getString("full_name");
                            JOptionPane.showMessageDialog(DNFrame, "Đăng nhập thành công!\nXin chào " + (fullName != null ? fullName : user));
                            
                            // Mở màn hình chính


                            DNFrame.dispose(); 
                        } else {
                            JOptionPane.showMessageDialog(DNFrame, "Sai username hoặc password!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(DNFrame, "Lỗi kết nối CSDL:\n" + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        

    // Gắn các panel vào khung chính
        DNFrame.add(leftPanel, BorderLayout.WEST);
        DNFrame.add(rightPanel, BorderLayout.CENTER);
        // DNFrame.add(rightPanel, BorderLayout.EAST);

    // Hiển thị cửa sổ
        DNFrame.setVisible(true);

    }
}




// Lưu ý: Tất cả phải đều nhau tạo sự đẹp mắt