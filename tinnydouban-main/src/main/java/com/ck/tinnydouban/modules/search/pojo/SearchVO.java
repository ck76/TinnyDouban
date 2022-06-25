package com.ck.tinnydouban.modules.search.pojo;

import com.ck.tinnydouban.modules.movie.dto.movie.MovieVO;
import com.ck.tinnydouban.modules.security.dto.vo.UserVO;
import com.ck.tinnydouban.modules.social.dto.post.PostVO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchVO {

    List<MovieVO> movieList;

    List<PostVO> postList;

    List<UserVO> userList;

}
