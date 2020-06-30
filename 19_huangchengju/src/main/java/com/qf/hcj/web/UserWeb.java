package com.qf.hcj.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.hcj.entity.UserEntity;
import com.qf.hcj.service.IUserService;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class UserWeb {
    @Autowired
    IUserService iUserService;

    @RequestMapping("/regist.do")

    public String picRegist(MultipartFile headImg, ModelMap map, HttpServletRequest req, UserEntity ue) throws IOException {

        InputStream is = headImg.getInputStream();
        String path = req.getSession().getServletContext().getRealPath("upload");

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = headImg.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newName = UUID.randomUUID().toString().replace("-", "") + suffix;
        FileOutputStream os = new FileOutputStream(path + File.separator + newName);

        FileCopyUtils.copy(is, os);

        String savePath = "upload" + "/"+ newName;

        ue.setImage(savePath);
        boolean b = iUserService.regist(ue);
        if (b) {
            return "login";
        } else {
            return "regist";
        }

    }

    @RequestMapping("queryname.do")
    @ResponseBody
    public String querynameServlet(String username) {
        System.out.println(username);
        UserEntity ue = iUserService.queryname(username);
        if (ue == null) {
            return "{\"result\":\"0000\"}";
        } else {
            return "{\"result\":\"0001\"}";
        }

    }

    @RequestMapping("queryemail.do")
    @ResponseBody
    public String queryemailServlet(String email) {

        UserEntity ue = iUserService.queryemail(email);
        if (ue == null) {
            return "{\"result\":\"0000\"}";
        } else {
            return "{\"result\":\"0001\"}";
        }

    }

    @RequestMapping("login.do")
    public String loginServlet(String username, String password, HttpServletRequest req) {
        System.out.println(username);
        UserEntity ue = iUserService.login(username, password);
        req.getSession().setAttribute("ue", ue);
        System.out.println(ue);
        if (ue == null) {
            String msg = "用户名或密码错误";
            req.getSession().setAttribute("msg", msg);
            return "regist";
        } else if (ue.getIsAdmin().equals("是")) {

            return "index_admin";
        } else if (ue.getIsAdmin().equals("否")) {
            req.getSession().setAttribute("ue", ue);
            return "index_user";
        } else {
            return "regist";
        }
    }

    @RequestMapping("page.do")
    public String pageServlet(HttpServletRequest req, ModelMap map) {
        String currentPage = req.getParameter("currentPage");
        String size = req.getParameter("size");
        if (currentPage == null) {
            currentPage = "1";
        } else {
            currentPage = currentPage;
        }
        if (size == null) {
            size = "4";
        } else {
            size = size;
        }
        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(size));
        List<UserEntity> list = iUserService.findUser();
        map.put("userlist", list);
        PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(list);
        int prePage = pageInfo.getPrePage();
        map.put("prePage", prePage);
        int nextPage = pageInfo.getNextPage();
        map.put("nextPage", nextPage);
        int[] nums = pageInfo.getNavigatepageNums();
        List<Integer> pagelist = new ArrayList<Integer>();
        for (Integer page : nums) {
            pagelist.add(page);
        }
        req.getSession().setAttribute("pagelist", pagelist);
        return "member";
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String deleteUser(int id, HttpServletRequest request) {

        boolean b = iUserService.delete(id);
        if (b) {
            return "{\"result\":\"0000\"}";
        } else {
            return "{\"result\":\"0001\"}";
        }


    }

    @RequestMapping("findone.do")
    @ResponseBody

    public String FindoneUser(int id, HttpServletRequest request) {

        UserEntity userEntity = iUserService.queryId(id);

        request.getSession().setAttribute("userEntity", userEntity);
        return "{\"result\":\"0000\"}";


    }

    @RequestMapping("update.do")

    public String updateUser(UserEntity ue, HttpServletRequest request) {

        boolean b = iUserService.update(ue);
        if (b) {
            return "redirect:page.do";

        } else {
            return "update";
        }
    }

    @RequestMapping("download.do")

    public void imgDownLoad(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String filename1 = fileName.substring(fileName.lastIndexOf("/") + 1);

        String path = req.getSession().getServletContext().getRealPath("upload");

        FileInputStream fi = new FileInputStream(path + File.separator + filename1);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;fileName=" + filename1);
        IOUtils.copy(fi, resp.getOutputStream());

    }
}
