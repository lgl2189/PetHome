package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.Article;

/**
 * <p>
 * 存储文章信息 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface ArticleService extends IService<Article> {
    PageInfo<Article> getArticleList(int pageNum, int pageSize);
}
