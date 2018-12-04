package org.github..api.web;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ERROR处理控制器
 */
@Controller
public class CustomErrorController extends BasicErrorController {

    private static final String ERROR_PATH = "/error";

    public CustomErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @ResponseBody
    @RequestMapping(ERROR_PATH)
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = this.getStatus(request);
        Map<String, Object> message = new HashMap<>();
        message.put("code", status.value());
        message.put("msg", body.get("error"));
        message.put("data", body.get("message"));
        return new ResponseEntity<>(message, status);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
