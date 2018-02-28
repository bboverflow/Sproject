package sproject.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: rayman
 * @Description: 用来返回的json封装
 * @Date: Create in 2018/2/26 17:59
 * @Modified By:
 */
@Getter
@Setter
public class JsonData {
    private boolean ret;
    private String msg;
    private Object data;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    public static JsonData success(){
        return new JsonData(true);
    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("ret", this.ret);
        map.put("msg", this.msg);
        map.put("data", this.data);
        return map;
    }
}
