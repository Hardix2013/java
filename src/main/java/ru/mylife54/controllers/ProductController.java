package ru.mylife54.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mylife54.models.Product;
import ru.mylife54.models.User;
import ru.mylife54.services.ProductService;
import ru.mylife54.services.StorageService;
import ru.mylife54.services.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllProducts(@AuthenticationPrincipal User auth, ModelMap modelMap) {
        modelMap.addAttribute("auth", auth);
        modelMap.addAttribute("products", productService.getAll());
        modelMap.addAttribute("bucket", auth.getBucket());
        return "shop";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','CREATOR')")
    @GetMapping("/createProduct")
    public String getFormForCreateProduct(@AuthenticationPrincipal User auth, ModelMap modelMap) {
        modelMap.addAttribute("auth", auth);
        modelMap.addAttribute("products", new Product());
        modelMap.addAttribute("bucket", auth.getBucket());
        return "createProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile image, ModelMap modelMap) {
        if (productService.getProduct(product.getName()) == null) {
            product.setImageName(storageService.upload(image));
            productService.saveProduct(product);
        } else {
            modelMap.addAttribute("errors", "Такое имя товара занято");
            modelMap.addAttribute("products", product);
            return "createProduct";
        }

        modelMap.addAttribute("products", product);
        return "redirect:/shop";
    }

    @GetMapping("/showProduct/{id}")
    public String showProduct(@AuthenticationPrincipal User auth, @PathVariable("id") Product product, ModelMap modelMap) {
        modelMap.addAttribute("auth", auth);
        modelMap.addAttribute("products", product);
        modelMap.addAttribute("bucket", auth.getBucket());
        return "showProduct";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','CREATOR')")
    @GetMapping("/editProduct/{id}")
    public String getFormForCreateProduct(@AuthenticationPrincipal User auth, @ModelAttribute("id") Product product, ModelMap modelMap) {
        modelMap.addAttribute("auth", auth);
        modelMap.addAttribute("products", product);
        modelMap.addAttribute("bucket", auth.getBucket());
        return "editProduct";
    }

    @PostMapping("/editProduct/{id}")
    public String editProduct(@AuthenticationPrincipal User auth, @ModelAttribute("id") Product product, @RequestParam(value = "image", required = false) MultipartFile image, ModelMap modelMap) {
        if (!image.getOriginalFilename().isEmpty()) {
            if (!product.getImageName().isEmpty()) {
                storageService.delete(product.getImageName());
            }
            product.setImageName(storageService.upload(image));
        }
        productService.saveProduct(product);
        modelMap.addAttribute("auth", auth);
        return "redirect:/shop/showProduct/{id}";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','CREATOR')")
    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Product product, @AuthenticationPrincipal User auth, ModelMap modelMap) {
        modelMap.addAttribute("auth", auth);
        if (!product.getImageName().isEmpty()) {
            storageService.delete(product.getImageName());
        }
        productService.deleteProduct(product);
        return "redirect:/shop";
    }

    @PostMapping("/addProduct/{id}")
    public String addProduct(@AuthenticationPrincipal User auth, @PathVariable("id") Product product, @RequestParam("count") Integer count, ModelMap modelMap) {
        modelMap.addAttribute("auth", auth);
        Map<Product, Integer> bucket = null;

        if (auth.getBucket()==null) {
            bucket = new HashMap<>();
        } else {
            bucket = auth.getBucket();
        }
        if (bucket.get(product) != null) {
            if ((bucket.get(product)+count)<=0||bucket.get(product)<=0){
                bucket.remove(product);
            }else {
                bucket.put(product, bucket.get(product) + count);
            }
        } else {
            if (count>0){
                bucket.put(product, count);
            }
        }
        auth.setBucket(bucket);
        userService.updateUser(auth);
        modelMap.addAttribute("products", productService.getAll());
        return "shop";
    }




}
