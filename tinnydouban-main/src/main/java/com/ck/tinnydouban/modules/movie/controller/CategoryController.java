package com.ck.tinnydouban.modules.movie.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.category.CategoryVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.service.CategoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Resource
    private CategoryService categoryService;


    @GetMapping("/all")
    @ApiOperation("查询全部分类")
    CommonResult<List<CategoryVO>> queryAll() {

        List<CategoryVO> list = categoryService.queryAll();
        return CommonResult.success(list, "查询成功");
    }

    @GetMapping("/movie")
    @ApiOperation("分页查询当前分类下全部电影")
    CommonResult<PageInfo<MovieVO>> queryMoviePageByCategory(@RequestParam("category_id") Long categoryId,
                                                             @RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                                             @RequestParam(name = "count", defaultValue = "20") Integer count) throws ApiException {

        PageInfo<MovieVO> voPage = categoryService.queryMovieByCategory(
                categoryId, offset, count);
        return CommonResult.success(voPage, "查询成功");
    }


}
