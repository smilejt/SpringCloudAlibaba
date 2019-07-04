package com.abandon.jt.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @createData: 2019-06-24 16:59
 * @author: LongJunTao
 * @Description:
 */
public class CustomizeResult implements Serializable {
    private static final ObjectMapper MAPPER = new ObjectMapper();	// 定义jackson对象
    private static final long serialVersionUID = 6499807021160313356L;

    private Integer status;	// 响应业务状态

    private String msg;		// 响应消息

    private Object data;	// 响应中的数据

    public CustomizeResult() {
    }

    public CustomizeResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public CustomizeResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static CustomizeResult ok(Object data) {
        return new CustomizeResult(data);
    }

    public static CustomizeResult ok() {
        return new CustomizeResult(null);
    }

    public static CustomizeResult build(Integer status, String msg, Object data) {
        return new CustomizeResult(status, msg, data);
    }

    public static CustomizeResult build(Integer status, String msg) {
        return new CustomizeResult(status, msg, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为ITDragonResult对象
     *
     * @param jsonData json数据
     * @param clazz ITDragonResult中的object类型
     * @return
     */
    public static CustomizeResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, CustomizeResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static CustomizeResult format(String json) {
        try {
            return MAPPER.readValue(json, CustomizeResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static CustomizeResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
