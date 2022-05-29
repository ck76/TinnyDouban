package com.ck.tinnydouban.modules.movie.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.movie.service.MovieUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("movie/user")
public class MovieUserController {

    @Resource
    private MovieUserService movieUserService;


    @GetMapping("/wanna/{movie_id}")
    @ApiOperation("想看某个电影")
    CommonResult<Void> wannaSee(@PathVariable("movie_id") Long movieId) throws ApiException {
        movieUserService.wannaSeeMovie(movieId);
        return CommonResult.success("添加想看成功");
    }

    @GetMapping("/wanna/cancel/{movie_id}")
    @ApiOperation("取消想看某个电影")
    CommonResult<Void> cancelWannaSee(@PathVariable("movie_id") Long movieId) throws ApiException {
        movieUserService.cancelWannaSeeMovie(movieId);
        return CommonResult.success("取消想看成功");
    }

    @GetMapping("/seen/{movie_id}")
    @ApiOperation("看过某个电影")
    CommonResult<Void> haveSeen(@PathVariable("movie_id") Long movieId) throws ApiException {
        movieUserService.haveSeenMovie(movieId);
        return CommonResult.success("添加看过成功");
    }


    @GetMapping("/rating")
    @ApiOperation("给电影打分")
    CommonResult<Void> rating(@RequestParam("movie_id") Long movieId,
                              @RequestParam("score") Double score) throws ApiException {

        ApiException.when(score < 1.0 || score > 5.0, "打分范围为1 ~ 5");
        movieUserService.ratingMovie(movieId, score);
        return CommonResult.success("打分成功");
    }

    @GetMapping("/wanna/all")
    CommonResult<PageInfo<MovieVO>> queryAllWannaSeeMovies(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                           @RequestParam(value = "count", defaultValue = "20") Integer count) throws ApiException {
        PageInfo<MovieVO> pageInfo = movieUserService.queryAllWannaSee(offset, count);
        return CommonResult.success(pageInfo, "查询成功");
    }


    @GetMapping("/seen/all")
    CommonResult<PageInfo<MovieVO>> queryAllHaveSeenMovies(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                           @RequestParam(value = "count", defaultValue = "20") Integer count) throws ApiException {
        PageInfo<MovieVO> pageInfo = movieUserService.queryAllHaveSee(offset, count);
        return CommonResult.success(pageInfo, "查询成功");
    }


}
