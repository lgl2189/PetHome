package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.service.FileRecordService;
import com.pethome.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-05-16
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private final FileRecordService fileRecordService;

    @Autowired
    public FileController(FileRecordService fileRecordService) {
        Assert.notNull(fileRecordService, "fileRecordService must not be null");
        this.fileRecordService = fileRecordService;
    }
    @PostMapping("/upload/single")
    public Result uploadSingle(@RequestParam("file") MultipartFile file) throws IOException {
        fileRecordService.saveFileRecord(file);
        return ResultUtil.success_200(null,"文件上传成功");
    }

    @PostMapping("/upload/multiple")
    public Result uploadMultiple(@RequestParam("file") MultipartFile[] fileArray) throws IOException {
        fileRecordService.saveFileRecordGroup(fileArray);
        return ResultUtil.success_200(null,"文件组上传成功");
    }
}
