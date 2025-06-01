package vantd.com;

public class AccountService {
    private String username;
    private String password;
    private String email;

    public AccountService() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean registerAccount(String username, String password, String email) {
        if (username == null || username.isEmpty()) return false;
        if (password == null || password.length() <= 6) return false;
        if (!isValidEmail(email)) return false;
        return true;
    }
    /*
        Kiểm tra email hợp lệ với định dạng: phần trước và sau '@' gồm chữ, số, dấu chấm hoặc gạch ngang, kết thúc bằng đuôi ít nhất 2 ký tự.
    */
    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }

    public AccountService(String username, String email, String password) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.isEmpty() ||
                email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Attribute must not be empty");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email is not valid");
        }
        this.username = username;
        this.email = email;
        this.password = password;
    }
    /*
        Mật khẩu mạnh phải có ít nhất 8 ký tự, chứa chữ hoa, chữ thường và số.
    */
    public boolean isStrongPassword(String password) {
        if (password == null) return false;
        return password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}");
    }
}

