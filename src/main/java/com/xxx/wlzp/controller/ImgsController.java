package com.xxx.wlzp.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.xxx.wlzp.common.Result;
import com.xxx.wlzp.entity.Imgs;
import com.xxx.wlzp.mapper.ImgsMapper;
import com.xxx.wlzp.service.ImgsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

@RestController //表示@Controller + @ResponseBody的结合
@RequestMapping("/imge") //表示请求路径
public class ImgsController {
    //9091
    @Value("${server.port}")
    private String port;

    private final static String ip ="http://oovj28pcn7ao.ngrok.xiaomiqiu123.top";

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource //表示自动获取bean里面的资源，也可使用@Autowired 自动装配
    ImgsService imgsService;

    @Resource
    ImgsMapper imgsMapper;

    @PostMapping("/aa") //表示get请求，未写路径，可以直接使用
    public void addImg(@RequestParam String qq, @RequestParam String  img ,@RequestParam String  tzurl , HttpServletResponse response) throws IOException, ServletException {
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + qq;
        String  url;
        url=ip+"/imge/" +fileUuid+".png";

        //对字节数组字符串进行Base64解码并生成图片
        Base64.Decoder decoder = Base64.getDecoder();
        //Base64解码
        byte[] b = decoder.decode(img);

        // 判断路径是否不存在，不存在就创建文件夹
        File fileDir = new File(fileUploadPath);

        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }

        // 生成一个空文件，自定义图片的名字
        File file = new File(fileUploadPath+fileUuid + ".png");

        if (!file.exists()) {
            file.createNewFile();
        }

        //生成jpg图片
        OutputStream out = new FileOutputStream(file.getPath());
        out.write(b);
        out.flush();
        out.close();
        System.out.println(url);
        imgsMapper.addImg(qq,url);
        response.sendRedirect(tzurl);
    }

    //下载
    @GetMapping("/{fileUUID}")
    public void downLoad(@PathVariable String fileUUID , HttpServletResponse response) throws IOException {
        //根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        ServletOutputStream os = response.getOutputStream();
        //设置输出流的格式
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        //读取字节上传流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @GetMapping("/qq")
    public Result findByQQ(@RequestParam(defaultValue = "") String qq){

        if ("".equals(qq)){
            return Result.error();
        }
        List<Imgs> byQQ = imgsMapper.findByQQ(qq);
        return Result.success(byQQ);
    }
}

