package pl.siedleckimateusz.nailsnatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.siedleckimateusz.nailsnatapp.cloudinary.CloudinaryUploader;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoType;
import pl.siedleckimateusz.nailsnatapp.entity.model.PhotoForm;
import pl.siedleckimateusz.nailsnatapp.service.PhotoCategoryService;
import pl.siedleckimateusz.nailsnatapp.service.PhotoService;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    private final CloudinaryUploader cloudinaryUploader;
    private final PhotoCategoryService photoCategoryService;
    private final PhotoService photoService;

    public PhotoController(CloudinaryUploader cloudinaryUploader, PhotoCategoryService photoCategoryService, PhotoService photoService) {
        this.cloudinaryUploader = cloudinaryUploader;
        this.photoCategoryService = photoCategoryService;
        this.photoService = photoService;
    }


    @GetMapping
    public String showAll() {
        return "image/index";
    }

    @PostMapping
    public String savePhoto(@ModelAttribute("photo") PhotoForm photo){

        String url = cloudinaryUploader.uploadAndGetUrl(photo.getFile());
        photo.setUrl(url);
        photoService.save(photo);

        return "redirect:/photo/add";
    }

    @GetMapping("/add")
    public String formPhoto(Model model) {
        model.addAttribute("photoTypes", PhotoType.values());
        model.addAttribute("photo",new PhotoForm());
        model.addAttribute("allPhotoCategories",photoCategoryService.findAll());

        return "image/form";
    }
}
