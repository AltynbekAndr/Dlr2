package com.springapp.mvc.controllers;

import com.springapp.mvc.service.MyServiceClass;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping
public class OutputPhoto {
    @Autowired
    MyServiceClass myServiceClass;
    @ResponseBody
    @RequestMapping(value = "photo_company", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo_company(@RequestParam("companyId")String companyId,@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+companyId+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
    @ResponseBody
    @RequestMapping(value = "photo_shops", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo_shops(@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = "C:"+File.separator+"path"+File.separator+"shops"+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
    @ResponseBody
    @RequestMapping(value = "photo_courier", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo_courier(@RequestParam("companyId")String companyId,@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+companyId+File.separator+"couriers"+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
    @ResponseBody
    @RequestMapping(value = "photo_products", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo_products(@RequestParam("companyId")String companyId,@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+companyId+File.separator+"products"+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
    @ResponseBody
    @RequestMapping(value = "small_photo_products", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] small_photo_products(@RequestParam("companyId")String companyId,@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+companyId+File.separator+"products"+File.separator+"small"+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
    @ResponseBody
    @RequestMapping(value = "small_photo_courier", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] small_photo_courier(@RequestParam("companyId")String companyId,@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+companyId+File.separator+"couriers"+File.separator+"small"+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
    @ResponseBody
    @RequestMapping(value = "small_photo_shops", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] small_photo_shops(@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = "C:"+File.separator+"path"+File.separator+"shops"+File.separator+"small"+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
    @ResponseBody
    @RequestMapping(value = "small_photo_company", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] small_photo_company(@RequestParam("companyId")String companyId,@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+companyId+File.separator+"small"+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
}
