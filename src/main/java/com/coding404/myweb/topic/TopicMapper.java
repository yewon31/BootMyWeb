package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface TopicMapper {
    public int topicInsert(TopicVO vo);//등록

    public ArrayList<TopicVO> getList(@Param("userId") String userId, @Param("cri") Criteria cri); //목록 (매개변수가 2개 이상이면, 이름을 붙여줘야함)
    public int getTotal(@Param("userId") String userId, @Param("cri") Criteria cri); //전체게시글 수

    public TopicVO getDetail(int prodId); //상세내역
    public int topicUpdate(TopicVO vo); //수정
    public void topicDelete(int prodId); //삭제
}
