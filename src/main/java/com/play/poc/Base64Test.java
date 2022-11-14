package com.play.poc;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Base64Test {

    public static void main(String[] args) {
        File myPic = new File("src/main/resources/images/emmanuel-ogbinaka-passport-photograph.jpg");
        String base64 = imageToBase64X(myPic);

        System.out.println(base64);
    }

    public static String imageToBase64(File file) {
        byte[] fileContent;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(file.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return new String(Base64.getUrlEncoder().encode(fileContent), StandardCharsets.UTF_8);
//        return Base64.getEncoder().encodeToString(fileContent, Base64.URL_SAFE);
    }

    @SneakyThrows
    public static String imageToBase64X(File file) {
        BufferedImage bufferedImage = ImageIO.read(file);
        String imageString = "";
        String ext = FilenameUtils.getExtension(file.getName());

        try ( ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            ImageIO.write(bufferedImage, ext, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = DatatypeConverter.printBase64Binary(imageBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;

    }

    public static File convertMultiPartToFile(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
