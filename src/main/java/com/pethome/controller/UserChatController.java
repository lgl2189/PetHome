package com.pethome.controller;

import com.github.pagehelper.PageInfo;
import com.pethome.dto.Message;
import com.pethome.dto.MessageList;
import com.pethome.dto.Result;
import com.pethome.entity.mybatis.MessageRecord;
import com.pethome.service.MessageRecordService;
import com.pethome.util.DatabasePageUtil;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 存储用户间交流记录 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/chat")
public class UserChatController {

    private final MessageRecordService messageRecordService;

    @Autowired
    public UserChatController(MessageRecordService messageRecordService) {
        Assert.notNull(messageRecordService, "messageRecordService must not be null");
        this.messageRecordService = messageRecordService;
    }

    @JwtAuthority
    @GetMapping("/user/{userId}/recent-chat-list")
    public Result getRecentChatUserList(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        if (userId == null) return ResultUtil.fail_401(null, "用户id不能为空");
        if(pageNum == null) pageNum = 0;
        if(pageSize == null) pageSize = 0;
        PageInfo<Message> recentChatUserPageInfo = messageRecordService.getRecentChatUserList(userId, pageNum, pageSize);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("recent_chat_list", recentChatUserPageInfo.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(recentChatUserPageInfo));
        return ResultUtil.success_200(resMap, "获取最近聊天列表成功");
    }

    @JwtAuthority
    @GetMapping("/message/list/{userId1}/{userId2}")
    public Result getUserMessageList(@PathVariable Integer userId1, @PathVariable Integer userId2) {
        if(userId1 == null || userId2 == null){
            return ResultUtil.fail_401(null, "发送者和接收者id不能为空");
        }
        MessageList messageList = messageRecordService.getRecentChatRecordList(userId1, userId2);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("message_list_info", messageList);
        return ResultUtil.success_200(resMap, "获取消息列表成功");
    }

    @JwtAuthority
    @PostMapping("/message")
    public Result sendMessage(@RequestBody MessageRecord messageRecord) {
        if(messageRecord == null || messageRecord.getSenderId() == null || messageRecord.getReceiverId() == null
                || messageRecord.getMessageContent() == null || messageRecord.getMessageDatetime() == null ){
            return ResultUtil.fail_401(null, "消息不能为空");
        }
        boolean isSaved = messageRecordService.save(messageRecord);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("message", messageRecord);
        if(!isSaved){
            return ResultUtil.fail_500(null, "消息保存失败");
        }
        return ResultUtil.success_200(resMap, "消息发送成功");
    }
}
