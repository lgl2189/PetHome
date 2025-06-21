package com.pethome.controller;

import com.github.pagehelper.PageInfo;
import com.pethome.dto.Result;
import com.pethome.entity.mybatis.Article;
import com.pethome.service.ArticleService;
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
 * 存储文章信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        Assert.notNull(articleService, "articleService must not be null");
        this.articleService = articleService;
    }

    @JwtAuthority
    @GetMapping("/list")
    public Result getArticleList(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize) {
        if(pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 10;
        }
        PageInfo<Article> articlePageInfo = articleService.getArticleList(pageNum, pageSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("articles_list", articlePageInfo.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(articlePageInfo));
        return ResultUtil.success_200(resMap, "文章列表获取成功");
    }

    @JwtAuthority
    @GetMapping("/{articleId}")
    public Result getArticle(@PathVariable("articleId") Integer articleId) {
        if (articleId == null) {
            return ResultUtil.fail_401(null, "文章ID不能为空");
        }
        Article article = articleService.getById(articleId);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("article", article);
        return ResultUtil.success_200(resMap, "文章获取成功");
    }

    @JwtAuthority
    @PostMapping("")
    public Result addArticle(@RequestBody Article article) {
        if (article == null) {
            return ResultUtil.fail_401(null, "文章不能为空");
        }
        if(article.getArticleId() != null){
            article.setArticleId(null);
        }
        articleService.save(article);
        return ResultUtil.success_200(null, "文章添加成功");
    }
}
