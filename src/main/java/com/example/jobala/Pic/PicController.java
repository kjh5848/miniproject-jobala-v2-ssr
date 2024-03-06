package com.example.jobala.Pic;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Controller
public class PicController {

    private final String upload = "./upload/";

    @GetMapping("/upload")
    public void upload(@PathVariable String filename, HttpServletResponse response) {
        Path file = Paths.get(upload, filename);
        try (FileInputStream fis = new FileInputStream(file.toFile());
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int b;
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}