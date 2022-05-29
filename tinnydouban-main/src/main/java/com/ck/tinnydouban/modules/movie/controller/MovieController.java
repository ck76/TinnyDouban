package com.ck.tinnydouban.modules.movie.controller;


import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieParam;
import com.ck.tinnydouban.modules.movie.service.MovieService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/movie")
public class MovieController {


    @Resource
    MovieService movieService;


    @GetMapping("/feed")
    @ApiOperation("feed页面推荐接口")
    CommonResult<PageInfo<MovieVO>> feed() {
        // TODO: 2021/1/7 推荐系统接口
        return CommonResult.success();
    }


    @GetMapping("/interest/{movie_id}")
    CommonResult<PageInfo<MovieVO>> maybeInterest(@PathVariable("movie_id") Long movieId) {
        // TODO: 2021/1/7 可能感兴趣的电影
        return CommonResult.success();
    }


    @PostMapping("/add")
    @ApiOperation("插入电影实体")
    CommonResult<Void> addMovie(@RequestBody MovieParam vo) throws ApiException {
        movieService.insert(vo);
        return CommonResult.success("插入成功");
    }


    @GetMapping("/info/{id}")
    @ApiOperation("根据id查询电影")
    CommonResult<MovieVO> queryMovie(@PathVariable("id") Long id) throws ApiException {
        MovieVO movieVO = movieService.queryById(id, true);
        return CommonResult.success(movieVO);
    }

    @GetMapping("/info/douban/{db_id}")
    @ApiOperation("根据豆瓣id查询电影")
    CommonResult<MovieVO> queryDoubanMovie(@PathVariable("db_id") Long dbId) throws ApiException {
        MovieVO movieVO = movieService.queryByDoubanId(dbId, true);
        return CommonResult.success(movieVO);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除指定电影信息")
    CommonResult<Void> deleteMovie(@PathVariable("id") Long id) throws ApiException {
        movieService.deleteById(id);
        return CommonResult.success("删除成功");
    }


    @DeleteMapping("/deleteAll")
    @ApiOperation("删除全部电影信息")
    CommonResult<Void> deleteAllMovie() {
        movieService.deleteAll();
        return CommonResult.success("删除成功");
    }


    @PostMapping("/update")
    CommonResult<Void> updateMovie(@RequestBody MovieParam qvo) throws ApiException {
        ApiException.when(movieService.update(qvo) == 0, "更新失败");
        return CommonResult.success("更新成功");
    }

    @GetMapping("/query/year")
    CommonResult<PageInfo<MovieVO>> queryMoviesByYear(@RequestParam("year") Integer year,
                                                      @RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                                      @RequestParam(name = "count", defaultValue = "20") Integer count) throws ApiException {
        PageInfo<MovieVO> voPage = movieService.queryMovieByYear(year, offset, count);
        return CommonResult.success(voPage);
    }


    @GetMapping("/feedMock")
    @ApiOperation("只查询有封面的，mock数据")
    CommonResult<PageInfo<MovieVO>> feedMock(@RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                             @RequestParam(name = "count", defaultValue = "20") Integer count) throws ApiException {
        PageInfo<MovieVO> voPage = movieService.queryMovieWithCover(offset, count);
        return CommonResult.success(voPage);

    }


}
