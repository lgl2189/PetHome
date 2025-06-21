package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.Article;
import com.pethome.mapper.ArticleMapper;
import com.pethome.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储文章信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public PageInfo<Article> getArticleList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.list());
    }
}
