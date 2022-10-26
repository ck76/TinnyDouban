[TOC]

## Introduction

- Movie management system (similar to [IMDB](https://www.imdb.com/)) developed in the Javalanguage for practice.
- Administrators can create movie items through the system
- Users can mark movies they want to see, watch, and rate



## Technology used

- Java，SpringBoot
- MySQL，Mybatis，lombok



## API Description

### admin

| URI | Description |
| --- | ------------ |
| /admin/user/addRole | 给用户关联一个角色 |
| /admin/user/delete/{id} | 删除用户 |
| /admin/user/lock/{id} | 账号锁定 |
| /admin/user/unlock/{id} | 解除账号锁定 |



### message

| URI                    | Description            |
| ---------------------- | ---------------------- |
| /message/type          | 获取全部消息类型       |
| /message/read/{msg_id} | 读取消息               |
| /message/system        | 创建系统消息           |
| /message/list          | 获取某个类型的全部消息 |



### movie

| URI                                | Description                |
| ---------------------------------- | -------------------------- |
| /category/all                      | 查询全部分类               |
| /category/movie                    | 分页查询当前分类下全部电影 |
| /filmmaker/info/{id}               | 获取指定演员的信息         |
| /language/all                      | 获取全部语言               |
| /language/movie                    | 分页查询当前语言下全部电影 |
| /movie/feed                        | feed页面推荐接口           |
| /movie/add                         | 插入电影实体               |
| /movieinfo/{id}                    | 根据id查询电影             |
| /movieinfo/douban/{db_id}          | 根据豆瓣id查询电影         |
| /movie/delete/{id}                 | 删除指定电影信息           |
| /movie/deleteAll                   | 删除全部电影信息           |
| /movie/update                      | 更新电影信息               |
| /movie/feedMock                    | 只查询有封面的，mock数据   |
| movie/user/wanna/{movie_id}        | 想看某个电影               |
| movie/user/wanna/cancel/{movie_id} | 取消想看某个电影           |
| movie/user/seen/{movie_id}         | 看过某个电影               |
| movie/user/rating                  | 给电影打分                 |
| movie/user/wanna/all               | 想看的列表                 |
| movie/user/seen/all                | 看过的列表                 |
| /region/all                        | 获取全部地区               |
| /region/movie                      | 分页查询地区分类下全部电影 |



### search

| URI           | Description  |
| ------------- | ------------ |
| /search/all   | 全局聚合搜索 |
| /search/movie | 电影搜索     |
| /search/post  | 帖子搜索     |
| /search/user  | 用户搜索     |



### social

| URI                      | Description                        |
| ------------------------ | ---------------------------------- |
| /bbs/comment             | 查询全部分类                       |
| /bbs/comment             | 分页查询当前分类下全部电影         |
| /bbs/comment             | 获取指定演员的信息                 |
| /follow/{user_id}        | 关注用户                           |
| /follow/cancel/{user_id} | 取消关注                           |
| /follow/following        | 获取当前用户关注人的列表           |
| /follow/followed         | 获取当前用户关注人的列表           |
| /bbs/like                | 点赞成功                           |
| /bbs/unlike              | 取消点赞成功                       |
| /bbs/post/create         | 发表帖子                           |
| /bbs/post/update         | 新帖子内容                         |
| /bbs/post/delete/{id}    | 删除帖子                           |
| /bbs/post/topic/list     | 获取一个主题下的全部帖子           |
| /bbs/post/movie/list     | 获取一个电影下的全部帖子           |
| /bbs/reply/create        | 添加回复                           |
| /bbs/reply/list          | 获取评论下的回复列表               |
| /bbs/reply/delete/{id}   | 删除某条评论                       |
| /bbs/topic/create        | 话题创建成功                       |
| /bbs/topic/update        | 更新话题                           |
| /bbs/topic/delete/{id}   | 删除主题                           |
| /bbs/topic/commons       | 获取全部常规话题, 按照帖子数量排序 |





```java
.
├── README.md
├── pom.xml
├── tinnydouban-common
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── com
│   │               └── ck
│   │                   └── tinnydouban
│   │                       └── api
│   ├── target
│   │   ├── classes
│   │   │   └── com
│   │   │       └── ck
│   │   │           └── tinnydouban
│   │   │               └── api
│   │   │                   ├── CommonResult.class
│   │   │                   ├── IResultCode.class
│   │   │                   └── ResultCode.class
│   │   └── generated-sources
│   │       └── annotations
│   └── tinnydouban-common.iml
├── tinnydouban-main
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── ck
│   │   │   │           └── tinnydouban
│   │   │   │               ├── TinnyDoubanApplication.java
│   │   │   │               ├── base
│   │   │   │               ├── batch
│   │   │   │               ├── config
│   │   │   │               ├── dao
│   │   │   │               ├── entity
│   │   │   │               ├── exception
│   │   │   │               ├── modules
│   │   │   │               └── utils
│   │   │   └── resources
│   │   │       ├── application.yml
│   │   │       ├── mappers
│   │   │       │   ├── AppConfigMapper.xml
│   │   │       │   ├── CategoryMapper.xml
│   │   │       │   ├── CommentMapper.xml
│   │   │       │   ├── FilmmakerMapper.xml
│   │   │       │   ├── FilmmakerProfessionMapper.xml
│   │   │       │   ├── LanguageMapper.xml
│   │   │       │   ├── LikeMapper.xml
│   │   │       │   ├── MessageMapper.xml
│   │   │       │   ├── MovieCategoryMapper.xml
│   │   │       │   ├── MovieFilmmakerMapper.xml
│   │   │       │   ├── MovieLanguageMapper.xml
│   │   │       │   ├── MovieMapper.xml
│   │   │       │   ├── MovieRegionMapper.xml
│   │   │       │   ├── MovieUserMapper.xml
│   │   │       │   ├── PostMapper.xml
│   │   │       │   ├── ProfessionMapper.xml
│   │   │       │   ├── RegionMapper.xml
│   │   │       │   ├── ReplyMapper.xml
│   │   │       │   ├── RoleMapper.xml
│   │   │       │   ├── TopicMapper.xml
│   │   │       │   ├── UserFollowMapper.xml
│   │   │       │   ├── UserMapper.xml
│   │   │       │   └── UserRoleMapper.xml
│   │   │       └── sql
│   │   │           ├── apply.sql
│   │   │           └── init_schema.sql
│   │   └── test
│   │       └── java
│   │           └── com
│   │               ├── ck
│   │               │   └── tinnydouban
│   │               │       └── controller
│   │               └── fhypayaso
│   │                   └── tittytainment
│   │                       └── controller
│   ├── target
│   │   ├── classes
│   │   │   ├── application.yml
│   │   │   ├── com
│   │   │   │   └── ck
│   │   │   │       └── tinnydouban
│   │   │   │           ├── TinnyDoubanApplication.class
│   │   │   │           ├── base
│   │   │   │           │   ├── BasePVO.class
│   │   │   │           │   └── BaseQVO.class
│   │   │   │           ├── batch
│   │   │   │           │   ├── controller
│   │   │   │           │   ├── filmmaker
│   │   │   │           │   ├── helper
│   │   │   │           │   ├── movie
│   │   │   │           │   └── service
│   │   │   │           ├── config
│   │   │   │           │   ├── RedisConfig.class
│   │   │   │           │   ├── RestClientConfig.class
│   │   │   │           │   └── SwaggerConfig.class
│   │   │   │           ├── dao
│   │   │   │           │   ├── AppConfigMapper.class
│   │   │   │           │   ├── CategoryMapper.class
│   │   │   │           │   ├── CommentMapper.class
│   │   │   │           │   ├── FilmmakerMapper.class
│   │   │   │           │   ├── FilmmakerProfessionMapper.class
│   │   │   │           │   ├── LanguageMapper.class
│   │   │   │           │   ├── LikeMapper.class
│   │   │   │           │   ├── MessageMapper.class
│   │   │   │           │   ├── MovieCategoryMapper.class
│   │   │   │           │   ├── MovieFilmmakerMapper.class
│   │   │   │           │   ├── MovieLanguageMapper.class
│   │   │   │           │   ├── MovieMapper.class
│   │   │   │           │   ├── MovieRegionMapper.class
│   │   │   │           │   ├── MovieUserMapper.class
│   │   │   │           │   ├── PostMapper.class
│   │   │   │           │   ├── ProfessionMapper.class
│   │   │   │           │   ├── RegionMapper.class
│   │   │   │           │   ├── ReplyMapper.class
│   │   │   │           │   ├── RoleMapper.class
│   │   │   │           │   ├── TopicMapper.class
│   │   │   │           │   ├── UserFollowMapper.class
│   │   │   │           │   ├── UserMapper.class
│   │   │   │           │   └── UserRoleMapper.class
│   │   │   │           ├── entity
│   │   │   │           │   ├── AppConfig.class
│   │   │   │           │   ├── Category.class
│   │   │   │           │   ├── Comment.class
│   │   │   │           │   ├── Filmmaker.class
│   │   │   │           │   ├── FilmmakerProfession.class
│   │   │   │           │   ├── Language.class
│   │   │   │           │   ├── Like.class
│   │   │   │           │   ├── Message.class
│   │   │   │           │   ├── Movie.class
│   │   │   │           │   ├── MovieCategory.class
│   │   │   │           │   ├── MovieFilmmaker.class
│   │   │   │           │   ├── MovieLanguage.class
│   │   │   │           │   ├── MovieRegion.class
│   │   │   │           │   ├── MovieUser.class
│   │   │   │           │   ├── Post.class
│   │   │   │           │   ├── Profession.class
│   │   │   │           │   ├── Region.class
│   │   │   │           │   ├── Reply.class
│   │   │   │           │   ├── Role.class
│   │   │   │           │   ├── Topic.class
│   │   │   │           │   ├── User.class
│   │   │   │           │   ├── UserFollow.class
│   │   │   │           │   └── UserRole.class
│   │   │   │           ├── exception
│   │   │   │           │   ├── AccountLockedException.class
│   │   │   │           │   ├── ApiException.class
│   │   │   │           │   ├── GlobalExceptionInterceptor.class
│   │   │   │           │   └── TokenErrorException.class
│   │   │   │           ├── modules
│   │   │   │           │   ├── admin
│   │   │   │           │   ├── app
│   │   │   │           │   ├── message
│   │   │   │           │   ├── movie
│   │   │   │           │   ├── search
│   │   │   │           │   ├── security
│   │   │   │           │   └── social
│   │   │   │           └── utils
│   │   │   │               └── DateUtil.class
│   │   │   ├── mappers
│   │   │   │   ├── AppConfigMapper.xml
│   │   │   │   ├── CategoryMapper.xml
│   │   │   │   ├── CommentMapper.xml
│   │   │   │   ├── FilmmakerMapper.xml
│   │   │   │   ├── FilmmakerProfessionMapper.xml
│   │   │   │   ├── LanguageMapper.xml
│   │   │   │   ├── LikeMapper.xml
│   │   │   │   ├── MessageMapper.xml
│   │   │   │   ├── MovieCategoryMapper.xml
│   │   │   │   ├── MovieFilmmakerMapper.xml
│   │   │   │   ├── MovieLanguageMapper.xml
│   │   │   │   ├── MovieMapper.xml
│   │   │   │   ├── MovieRegionMapper.xml
│   │   │   │   ├── MovieUserMapper.xml
│   │   │   │   ├── PostMapper.xml
│   │   │   │   ├── ProfessionMapper.xml
│   │   │   │   ├── RegionMapper.xml
│   │   │   │   ├── ReplyMapper.xml
│   │   │   │   ├── RoleMapper.xml
│   │   │   │   ├── TopicMapper.xml
│   │   │   │   ├── UserFollowMapper.xml
│   │   │   │   ├── UserMapper.xml
│   │   │   │   └── UserRoleMapper.xml
│   │   │   └── sql
│   │   │       ├── apply.sql
│   │   │       └── init_schema.sql
│   │   ├── generated-sources
│   │   │   └── annotations
│   │   ├── generated-test-sources
│   │   │   └── test-annotations
│   │   └── test-classes
│   │       └── com
│   │           └── ck
│   │               └── tinnydouban
│   │                   └── controller
│   │                       ├── AdminTest.class
│   │                       ├── BatchTest.class
│   │                       ├── SearchRepositoryTest.class
│   │                       └── UserControllerTest.class
│   └── tinnydouban-main.iml
├── tinnydouban-mybatis
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── ck
│   │       │           └── tinnydouban
│   │       │               ├── MybatisApplication.java
│   │       │               ├── dao
│   │       │               └── pojo
│   │       └── resources
│   │           └── mybatis-generator
│   │               ├── generatorConfig.xml
│   │               └── mybatisGeneratorinit.properties
│   ├── target
│   │   ├── classes
│   │   │   ├── com
│   │   │   │   └── ck
│   │   │   │       └── tinnydouban
│   │   │   │           ├── MybatisApplication.class
│   │   │   │           ├── dao
│   │   │   │           │   ├── AppConfigMapper.class
│   │   │   │           │   ├── CategoryMapper.class
│   │   │   │           │   ├── CommentMapper.class
│   │   │   │           │   ├── FilmmakerMapper.class
│   │   │   │           │   ├── FilmmakerProfessionMapper.class
│   │   │   │           │   ├── LanguageMapper.class
│   │   │   │           │   ├── LikeMapper.class
│   │   │   │           │   ├── MessageMapper.class
│   │   │   │           │   ├── MovieCategoryMapper.class
│   │   │   │           │   ├── MovieFilmmakerMapper.class
│   │   │   │           │   ├── MovieLanguageMapper.class
│   │   │   │           │   ├── MovieMapper.class
│   │   │   │           │   ├── MovieRegionMapper.class
│   │   │   │           │   ├── MovieUserMapper.class
│   │   │   │           │   ├── PostMapper.class
│   │   │   │           │   ├── ProfessionMapper.class
│   │   │   │           │   ├── RegionMapper.class
│   │   │   │           │   ├── ReplyMapper.class
│   │   │   │           │   ├── RoleMapper.class
│   │   │   │           │   ├── TopicMapper.class
│   │   │   │           │   ├── UserFollowMapper.class
│   │   │   │           │   ├── UserMapper.class
│   │   │   │           │   └── UserRoleMapper.class
│   │   │   │           └── pojo
│   │   │   │               └── entity
│   │   │   └── mybatis-generator
│   │   │       ├── generatorConfig.xml
│   │   │       └── mybatisGeneratorinit.properties
│   │   └── generated-sources
│   │       └── annotations
│   └── tinnydouban-mybatis.iml
└── tinnydouban.iml

101 directories, 155 files

```

