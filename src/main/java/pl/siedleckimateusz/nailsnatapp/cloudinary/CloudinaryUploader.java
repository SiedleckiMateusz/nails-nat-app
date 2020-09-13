package pl.siedleckimateusz.nailsnatapp.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewPhoto;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@Slf4j
@Component
public class CloudinaryUploader {

    private final HttpSession session;

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dnsnosnkv",
            "api_key", "691567434626562",
            "api_secret", "Wi45BLHV_MwIySluk1H9b27UduE"));

    public CloudinaryUploader(HttpSession session) {
        this.session = session;
    }

    public String uploadAndGetUrl(MultipartFile mFile){

        File file = saveFileFrom(mFile);
        HashMap<String,Object> uploadResult;
        String url = null;

        try {
//            uploadResult = (HashMap<String,Object>) cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//            cloudinary.uploader().upload(file,
//                    ObjectUtils.asMap("eaer", Collections.singleton(new Transformation().width(600).height(400).crop("pad"))
//                    ));
            uploadResult = (HashMap<String, Object>) cloudinary.uploader().upload(file,
                    ObjectUtils.asMap("transformation", new Transformation().width(600).height(400).crop("limit")));

            url = (String) uploadResult.get("url");

            boolean delete = file.delete();
            log.info("Usunięto plik po uploadzie? "+(delete?"tak":"nie"));
        } catch (IOException e) {
            System.out.println("Nie udało się wysłać zdjęcia");
            e.printStackTrace();
        }


        return url;
    }


    private File saveFileFrom(MultipartFile mFile){
        String path = session.getServletContext().getRealPath("/");

        String filename = mFile.getOriginalFilename();

        String pathName = path + filename;

        log.info(pathName);

        byte[] bytes;
        try {
            bytes = mFile.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path+"/"+filename));

            bout.write(bytes);
            bout.flush();
            bout.close();
        } catch (IOException e) {
            return null;
        }

        return new File(pathName);

    }


}
