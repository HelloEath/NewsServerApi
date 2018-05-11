package com.glut.news.controller;

import com.glut.news.service.IUserService;
import com.glut.news.vo.UserInfo;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService iUserService;
    Map<String, String> map;

    /*
     * 用户登录 x=1:用户存在且密码正确 x=0:用户存在，密码有误 x=2:用户不存在
     */
    @RequestMapping("/login")
    public @ResponseBody String login(@RequestBody String requestbody,HttpSession hSession) {
       Gson gson=new Gson();

        UserInfo userInfo2 = gson.fromJson(requestbody, UserInfo.class);

        UserInfo x = iUserService.loginService(userInfo2);
        Map<String, Object> map = new HashMap<String, Object>();

        if (x != null) {
            if (x.getUser_Password().equals(userInfo2.getUser_Password())) {

                hSession.setAttribute("User", x);
                map.put("stus", "1");
                map.put("user", x);

            } else {
                map.put("stus", "0");
            }

        } else {
            map.put("stus", "2");
        }
        return gson.toJson(map);

    }

    /*
     * 注册用户 返回值：1：用户注册成功 0：该用户已注册
     */
    @RequestMapping("/register")
    public @ResponseBody
    String register(@RequestBody String requestbody, UserInfo u, HttpSession hSession) {
        Gson gson = new Gson();
        UserInfo userInfo2 = gson.fromJson(requestbody, UserInfo.class);
        int x = iUserService.registerService(userInfo2, null);
        userInfo2.setUser_NickName(null);
        UserInfo userInfo = iUserService.selectUserService(userInfo2);
        Map<String, Object> map = new HashMap<String, Object>();

        if (x == 1) {
            map.put("stus", "1");
            map.put("user", userInfo);
            hSession.setAttribute("User", userInfo);

        } else {
            map.put("stus", "0");
            map.put("user", userInfo);
        }
        return gson.toJson(map);
    }

    /* 用户更新资料 */
    @RequestMapping("/updateUser")
    public @ResponseBody String updateUser(@RequestBody String requestbody, HttpSession hSession,HttpServletRequest request,HttpServletResponse resp) {

        Gson gson = new Gson();
        UserInfo userInfo2 = gson.fromJson(requestbody, UserInfo.class);
        UserInfo userInfo3 = (UserInfo) hSession.getAttribute("User");
        if(userInfo2.getUser_NickName() != null) {
            userInfo3.setUser_NickName(userInfo2.getUser_NickName());
        }
        if (userInfo2.getUser_Password() != null) {
            userInfo3.setUser_Password(userInfo2.getUser_Password());
        }
        if(userInfo2.getUser_PhoneNum() != null) {
            userInfo3.setUser_PhoneNum(userInfo2.getUser_PhoneNum());
        }

        if (userInfo2.getUser_Sex() != null) {
            userInfo3.setUser_Sex(userInfo2.getUser_Sex());
        }
        if (userInfo2.getUser_Birthday() != null) {
            userInfo3.setUser_Birthday(userInfo2.getUser_Birthday());
        }
        if (userInfo2.getUser_District() != null) {
            userInfo3.setUser_District(userInfo2.getUser_District());
        }

        if (userInfo2.getUser_Describe() != null) {
            userInfo3.setUser_Describe(userInfo2.getUser_Describe());
        }
        if(userInfo2.getUser_Picture() != null) {
            String url =request.getSession().getServletContext().getRealPath("/upload/");
            //String url = request.getRealPath("/upload");
            OutputStream os=null;
            String saveUrl=null;
            String y="D:/";
            Long nowTime=System.currentTimeMillis();
            String ImgPath=url+userInfo2.getUser_Id()+"_"+nowTime+"_logo.jpg";   //文件存放目录
            String newimagepath=userInfo2.getUser_Picture().replaceAll("data:image/jpeg;base64,", ""); //将编码中的data:image/jpeg;base64,替换
            BASE64Decoder decoder = new BASE64Decoder();
            saveUrl="http://192.168.191.1:8085/NewsServerApi/upload/"+userInfo2.getUser_Id()+"_"+nowTime+"_logo.jpg";
            try {
                byte[] b=decoder.decodeBuffer(newimagepath);  //对base64编码解码
                os=new FileOutputStream(ImgPath);  //图片上传保存路径
                os.write(b);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    if(os!=null)
                        os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(saveUrl);
            userInfo3.setUser_Picture(saveUrl);
        }

        Map<String, Object> map = new HashMap<String, Object>();

        int x = iUserService.updateUserService(userInfo3);
        if (x == 1) {
            map.put("stus", "1");
            map.put("user", userInfo3);
            hSession.setAttribute("User", userInfo3);
        } else {
            map.put("stus", "0");

        }
        //resp.addHeader("Content-type", "text/html;charset=UTF-8");
        return gson.toJson(map);
    }

    @RequestMapping("/deleteUser")
    public int deleteUser(UserInfo u) {
        return 1;
    }

    @RequestMapping("/alterUserLogo")
    public @ResponseBody
    void laterUserLogo(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String message = "";
        try {
            DiskFileItemFactory dff = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(dff);
            List<FileItem> items = sfu.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    //普通表单
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    System.out.println("name=" + fieldName + ", value=" + fieldValue);
                } else {// 获取上传字段
                    // 更改文件名为唯一的
                    String filename = item.getName();
                    if (filename != null) {
                        filename = "gh." + FilenameUtils.getExtension(filename);
                    }
                    // 生成存储路径
                    String storeDirectory = request.getServletContext().getRealPath("/files/images");
                    File file = new File(storeDirectory);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String path = genericPath(filename, storeDirectory);
                    // 处理文件的上传
                    try {
                        item.write(new File(storeDirectory + path, filename));

                        String filePath = "/files/images" + path + "/" + filename;
                        System.out.println("filePath=" + filePath);
                        message = filePath;
                    } catch (Exception e) {
                        message = "上传图片失败";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "上传图片失败";
        } finally {
            try {
                httpServletResponse.getWriter().write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //计算文件的存放目录
    private String genericPath(String filename, String storeDirectory) {
        int hashCode = filename.hashCode();
        int dir1 = hashCode & 0xf;
        int dir2 = (hashCode & 0xf0) >> 4;

        String dir = "/" + dir1 + "/" + dir2;

        File file = new File(storeDirectory, dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }


    @RequestMapping("/logOut")
    public @ResponseBody
    Map<String, String> logOut(@RequestBody String requestbody, HttpSession hSession) {
        Gson gson = new Gson();
        UserInfo userInfo = (UserInfo) hSession.getAttribute("User");
        if (userInfo != null) {
            userInfo = null;
            hSession.setAttribute("User", "");
        }
        UserInfo userInfo2 = gson.fromJson(requestbody, UserInfo.class);
        Map<String, String> map = new HashMap<String, String>();
        if (userInfo == null) {
            map.put("stus", "1");

        } else {
            map.put("stus", "0");

        }
        return map;
    }

}
