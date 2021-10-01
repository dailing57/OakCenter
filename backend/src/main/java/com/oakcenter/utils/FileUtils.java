package com.oakcenter.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {

    public String upload(MultipartFile file,String prefix) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        String rootFilePath = System.getProperty("user.dir") + "/files/" + prefix + originalFilename;  // 获取上传的路径
        cn.hutool.core.io.FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        return rootFilePath;  // 返回结果 url
    }

    public void getFile(String path, HttpServletResponse response) {
        path = System.getProperty("user.dir") + "/files/" + path;
        System.out.println(path);
        File file = new File(path);
        if(!file.exists()){
            System.out.println("not exists");
            return;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename="+path);

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }
    public Boolean deleteFile(String path){
        path = System.getProperty("user.dir") + "/files/" + path;
        System.out.println(path);
        return FileSystemUtils.deleteRecursively(new File(path));
    }
    public ResponseEntity<InputStreamResource> downloadFile(String path)
            throws IOException {
        String filePath = System.getProperty("user.dir") + "/files/" + path;
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
