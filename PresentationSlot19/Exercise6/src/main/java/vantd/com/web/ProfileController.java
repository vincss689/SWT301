package vantd.com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProfileController {
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
    private Profile profile = new Profile();

    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("profile", profile);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Profile profile,
                                @RequestParam("avatarFile") MultipartFile avatarFile,
                                RedirectAttributes redirectAttributes) {
        this.profile.setName(profile.getName());
        this.profile.setEmail(profile.getEmail());
        this.profile.setPhone(profile.getPhone());
        if (!avatarFile.isEmpty()) {
            try {
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                String fileName = System.currentTimeMillis() + "_" + avatarFile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR, fileName);
                Files.write(filePath, avatarFile.getBytes());
                this.profile.setAvatarPath("/uploads/" + fileName);
                redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công!");
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("msg", "Lỗi upload file!");
            }
        } else {
            redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công!");
        }
        return "redirect:/profile";
    }
} 