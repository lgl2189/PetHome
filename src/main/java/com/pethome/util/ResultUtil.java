package com.pethome.util;

import com.pethome.entity.web.Result;

/**
 * 该工具类用于创建不同状态的 Result 对象，方便在项目中统一处理响应结果。
 * Result 对象包含数据、状态码和消息，用于封装接口返回的信息。
 *
 * @author ：李冠良
 * @description： 该工具类提供了一系列静态方法，用于创建不同状态的 Result 对象。
 * 状态码遵循 HTTP 状态码的约定，如 200 表示成功，400 - 404 表示不同类型的失败。
 * @date ：2024/10/27 下午2:15
 */
public class ResultUtil {

    /**
     * 创建一个状态码为 200 的成功 Result 对象，包含指定的数据和消息。
     *
     * @param data    要封装在 Result 对象中的数据
     * @param message 要返回的消息
     * @return 一个状态码为 200 的成功 Result 对象
     */
    public static Result success_200(Object data, String message) {
        return create(data, "200", message);
    }

    /**
     * 创建一个状态码为 200 的成功 Result 对象，包含指定的数据，消息默认为 "成功"。
     *
     * @param data 要封装在 Result 对象中的数据
     * @return 一个状态码为 200 的成功 Result 对象
     */
    public static Result success_200(Object data) {
        return success_200(data, "成功");
    }

    /**
     * 创建一个状态码为 400 的失败 Result 对象，包含指定的数据和消息。
     * 注意：原代码中消息固定为 "失败"，这里应使用传入的 message 参数。
     *
     * @param data    要封装在 Result 对象中的数据
     * @param message 要返回的消息
     * @return 一个状态码为 400 的失败 Result 对象
     */
    public static Result fail_400(Object data, String message) {
        return create(data, "400", message);
    }

    /**
     * 创建一个状态码为 400 的失败 Result 对象，包含指定的数据，消息默认为 "失败"。
     *
     * @param data 要封装在 Result 对象中的数据
     * @return 一个状态码为 400 的失败 Result 对象
     */
    public static Result fail_400(Object data) {
        return fail_400(data, "失败");
    }

    /**
     * 创建一个状态码为 401 的失败 Result 对象，包含指定的数据和消息。
     * 401 表示参数为空。
     *
     * @param data    要封装在 Result 对象中的数据
     * @param message 要返回的消息
     * @return 一个状态码为 401 的失败 Result 对象
     */
    public static Result fail_401(Object data, String message) {
        return create(data, "401", message);
    }

    /**
     * 创建一个状态码为 401 的失败 Result 对象，包含指定的数据，消息默认为 "参数为空"。
     *
     * @param data 要封装在 Result 对象中的数据
     * @return 一个状态码为 401 的失败 Result 对象
     */
    public static Result fail_401(Object data) {
        return fail_401(data, "参数为空");
    }

    /**
     * 创建一个状态码为 402 的失败 Result 对象，包含指定的数据和消息。
     * 402 表示参数错误。
     *
     * @param data    要封装在 Result 对象中的数据
     * @param message 要返回的消息
     * @return 一个状态码为 402 的失败 Result 对象
     */
    public static Result fail_402(Object data, String message) {
        return create(data, "402", message);
    }

    /**
     * 创建一个状态码为 402 的失败 Result 对象，包含指定的数据，消息默认为 "参数错误"。
     *
     * @param data 要封装在 Result 对象中的数据
     * @return 一个状态码为 402 的失败 Result 对象
     */
    public static Result fail_402(Object data) {
        return fail_402(data, "参数错误");
    }

    /**
     * 创建一个状态码为 403 的失败 Result 对象，包含指定的数据和消息。
     * 403 表示权限不足禁止访问
     *
     * @param data    要封装在 Result 对象中的数据
     * @param message 要返回的消息
     * @return 一个状态码为 403 的失败 Result 对象
     */
    public static Result fail_403(Object data, String message) {
        return create(data, "403", message);
    }

    /**
     * 创建一个状态码为 403 的失败 Result 对象，包含指定的数据，消息默认为 "权限不足"。
     *
     * @param data 要封装在 Result 对象中的数据
     * @return 一个状态码为 403 的失败 Result 对象
     */
    public static Result fail_403(Object data) {
        return fail_403(data, "权限不足");
    }

    /**
     * 创建一个状态码为 404 的失败 Result 对象，包含指定的数据和消息。
     * 404 表示资源不存在。
     *
     * @param data    要封装在 Result 对象中的数据
     * @param message 要返回的消息
     * @return 一个状态码为 404 的失败 Result 对象
     */
    public static Result fail_404(Object data, String message) {
        return create(data, "404", message);
    }

    /**
     * 创建一个状态码为 404 的失败 Result 对象，包含指定的数据，消息默认为 "资源不存在"。
     *
     * @param data 要封装在 Result 对象中的数据
     * @return 一个状态码为 404 的失败 Result 对象
     */
    public static Result fail_404(Object data) {
        return fail_404(data, "资源不存在");
    }

    /**
     * 创建一个状态码为 500 的失败 Result 对象，包含指定的数据和消息。
     * 500 表示系统错误。
     *
     * @param data 要封装在 Result 对象中的数据
     * @param message 要返回的消息
     * @return 一个状态码为 500 的失败 Result 对象
     */
    public static Result fail_500(Object data, String message) {
        return create(data, "500", message);
    }

    /**
     * 创建一个状态码为 500 的失败 Result 对象，包含指定的数据，消息默认为 "系统错误"。
     *
     * @param data 要封装在 Result 对象中的数据
     * @return 一个状态码为 500 的失败 Result 对象
     */
    public static Result fail_500(Object data) {
        return fail_500(data, "系统错误");
    }

    /**
     * 根据指定的数据、状态码和消息创建一个 Result 对象。
     *
     * @param data    要封装在 Result 对象中的数据
     * @param status  状态码
     * @param message 要返回的消息
     * @return 一个包含指定数据、状态码和消息的 Result 对象
     */
    public static Result create(Object data, String status, String message) {
        return new Result(data, status, message);
    }
}