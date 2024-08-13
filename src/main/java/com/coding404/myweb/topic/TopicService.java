package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;

import java.util.ArrayList;

public interface TopicService {
    public int topicInsert(TopicVO vo); //등록

    public ArrayList<TopicVO> getList(String userId, Criteria cri); //목록
    public int getTotal(String userId, Criteria cri); //전체게시글 수

    public TopicVO getDetail(int prodId); //상세내역
    public int topicUpdate(TopicVO vo); //수정
    public void topicDelete(int prodId); //삭제
}
