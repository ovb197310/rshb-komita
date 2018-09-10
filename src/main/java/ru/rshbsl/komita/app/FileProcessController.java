package ru.rshbsl.komita.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class FileProcessController {

    @Autowired
    public FileProcessController() {
    }

    @GetMapping({"/"})
    public String listUploadedFiles(Model model) throws IOException {
        return "uploadForm";
    }

    @PostMapping(value = "/", consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity<Resource> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                     RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {

        String originalFilename = file.getOriginalFilename();

        Resource result=new InputStreamResource(this.getClass().getResourceAsStream("/1 ФЛ ИП.xml"));

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + URLEncoder.encode(originalFilename,"UTF-8") + ".xml\"").body(result);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}