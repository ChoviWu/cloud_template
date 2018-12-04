package org.github..rest.controller.user;

import org.github..common.constant.RedisConstants;
import org.github..common.exception.ExceptionMessage;
import org.github..common.model.dto.BasUserDTO;
import org.github..common.util.AssertUtil;
import org.github..common.util.StringUtils;
import org.github..common.util.ZixingCode;
import org.github..redis.RedisRepositoryUser;
import org.github..rest.service.AuthServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author
 * @date 2018/9/13
 * Description :
 */
@RestController
@RequestMapping("api")
public class LoginController {

    @Autowired
    private AuthServiceConsumer authServiceConsumer;
    @Autowired
    RedisRepositoryUser redisRepository;

    @RequestMapping(value = "qrLogin")
    public Object qrLogin(String uuid,String token){

        BasUserDTO userDTO = authServiceConsumer.scanQr(uuid,token);
        return userDTO;
    }

    @RequestMapping(value = "/callback")
    public Object callback(String token,String timestramp,String callback){

        String redisUUID = redisRepository.get(RedisConstants.QR_SCANNER_UUID + token);
        String uuid = redisUUID;
        //空
        if(!StringUtils.isNotEmpty(uuid)){
            uuid = StringUtils.getOrderNo(4)+"-1";
            redisRepository.set(RedisConstants.QR_SCANNER_UUID+token,uuid);
        }
        String state = uuid.substring(uuid.length()-2);
        //未扫码
        AssertUtil.isFalse("-1".equals(state), ExceptionMessage.User.USER_QR_IS_NO_SCAN);
        AssertUtil.isFalse("00".equals(state), ExceptionMessage.User.USER_QR_IS_NO_CONFIRM);
        //成功
        return uuid;
    }

    @RequestMapping(value = "/qrPic")
    public void qrPic(String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid=StringUtils.getOrderNo(4)+"-1";
        String sRootDir = this.getClass().getResource("/").getPath();
        String logoPath = sRootDir+"pic/logo.png";
        String localPath = sRootDir+"pic/";
        String fileName =StringUtils.getTimeStamp()+".jpg";
        ZixingCode.encodeQRCodeImage(uuid,"UTF-8",localPath+fileName, 512, 512, null);
        response.setHeader("content-type","image/png");
        byte[] bytes = new byte[1024*1024];
        int length = 0;
        InputStream in = new FileInputStream(localPath+fileName);
        OutputStream out = response.getOutputStream();
        while((length=in.read(bytes))!=-1){
            out.write(bytes,0,length);
        }
        out.write(bytes);
        out.flush();
        out.close();
        in.close();
//        return localPath+fileName;
    }

    /**
     * 扫描二维码
     * @param token  token
     * @return
     */
    @RequestMapping(value = "/qrScanner")
    public Object qrScanner(String token){

        String redisUUID = redisRepository.get(RedisConstants.QR_SCANNER_UUID + token);
        String uuid = redisUUID;
        //
        if(redisUUID!=null){
            String state = uuid.substring(uuid.length()-2);
            //已经扫码
            AssertUtil.isTrue("-1".equals(state), ExceptionMessage.User.USER_QR_ALREADY_SCAN);
            redisRepository.del(redisUUID);
            redisRepository.set(RedisConstants.QR_SCANNER_UUID + token,state+"00");
        }
        //刷新二维码

        //成功
        return uuid;
    }
}
