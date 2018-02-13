package com.netease.controller;

import com.netease.util.Constant;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by wanghao on 2/10/18.
 */
@Controller
public class FileController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "file/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req){
        if(!file.isEmpty()){
            try {
                String realpath = req.getServletContext().getRealPath("/resources/upload");
                System.out.println(realpath);
                System.out.println(req.getSession().getServletContext().getRealPath("/resources/upload"));
                File uploadDir = new File(realpath);
                if(!uploadDir.exists()){
                    uploadDir.createNewFile();
                }

                String fileRealName = file.getOriginalFilename();
                String postfix;
                int pointIdx = fileRealName.indexOf('.');
                if(pointIdx < 0){//文件名没有后缀
                    postfix = "";
                }
                else{
                    postfix = fileRealName.substring(pointIdx);
                }

                UUID uuid = UUID.randomUUID();
                String savedFileName = realpath + "/" + uuid.toString().replace("-","") + postfix;
                System.out.println(savedFileName);
                System.out.println(Constant.uploadDir);
                File savedFile = new File(savedFileName);
                FileUtils.copyInputStreamToFile(file.getInputStream(), savedFile);
            } catch (IOException e){
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return "file/add";
    }
}
