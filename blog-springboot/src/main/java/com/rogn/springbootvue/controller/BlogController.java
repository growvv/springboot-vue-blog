package com.rogn.springbootvue.controller;


//import cn.hutool.db.Page;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rogn.springbootvue.common.lang.Result;
import com.rogn.springbootvue.entity.Blog;
import com.rogn.springbootvue.service.BlogService;
import com.rogn.springbootvue.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2022-04-18
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);   //  不知道为啥这个分页没有生效
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageData);
    }

    @GetMapping("blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除");

        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        Blog tmp = null;
        if(blog.getId() != null) {
            tmp = blogService.getById(blog.getId());

            System.out.println(ShiroUtil.getProfile().getId());
            Assert.isTrue(tmp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有编辑权限");
        } else {
            tmp = new Blog();
            tmp.setUserId(ShiroUtil.getProfile().getId());
            tmp.setCreated(LocalDateTime.now());
            tmp.setStatus(0);
            System.out.println("tmp: " + tmp);
        }

        BeanUtils.copyProperties(blog, tmp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(tmp);

        return Result.succ(null);
    }

}
