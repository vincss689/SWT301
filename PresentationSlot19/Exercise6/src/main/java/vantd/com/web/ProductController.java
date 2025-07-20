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
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
    private List<Product> products = new ArrayList<>();

    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {
        if (!imageFile.isEmpty()) {
            try {
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR, fileName);
                Files.write(filePath, imageFile.getBytes());
                product.setImagePath("/uploads/" + fileName);
                redirectAttributes.addFlashAttribute("msg", "Thêm sản phẩm thành công!");
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("msg", "Lỗi upload file!");
            }
        } else {
            product.setImagePath("");
            redirectAttributes.addFlashAttribute("msg", "Thêm sản phẩm thành công!");
        }
        products.add(product);
        return "redirect:/products";
    }
} 