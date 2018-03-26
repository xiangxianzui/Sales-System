package com.netease.controller;

import com.google.common.base.Strings;
import com.netease.service.enums.UploadMsg;
import com.netease.util.Constant;
import com.netease.util.ModelConstant;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by wanghao on 2/10/18.
 */
@Controller
public class FileController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
    produces = "application/text; charset=utf-8")
    @ResponseBody
    public String upload(@RequestParam(value = "attach", required = true) MultipartFile attach,
                         HttpServletRequest req){
        String retMsg;
        if(attach == null || attach.isEmpty()){
            retMsg = UploadMsg.FAIL_NULL_FILE.EXTVALUE;
            return retMsg;
        }
        if(attach.getSize() > Constant.IMG_MAX_SIZE){
            retMsg = UploadMsg.FAIL_EXCEED_MAX_SIZE.EXTVALUE;
            return retMsg;
        }
        String contentType = attach.getContentType();
        if(Strings.isNullOrEmpty(contentType) || !contentType.startsWith("image")){
            retMsg = UploadMsg.FAIL_UNEXPECTED_TYPE.EXTVALUE;
            return retMsg;
        }
        String fileName = attach.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String realpath = req.getServletContext().getRealPath("/")+"img";
        //System.out.println(realpath);
        long curTime = System.currentTimeMillis();
        String newFileName = curTime + "." + suffix;
        File file = new File(realpath+"/"+newFileName);
        try {
            FileUtils.copyInputStreamToFile(attach.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        retMsg = req.getContextPath()+"/img/"+newFileName;
        //System.out.println(req.getContextPath()+"/img/"+newFileName);
        return retMsg;
    }
}
