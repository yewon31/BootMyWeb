package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("topicService") //이름
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public int topicInsert(TopicVO vo) {
        return topicMapper.topicInsert(vo);
    }

    @Override
    public ArrayList<TopicVO> getList(String userId, Criteria cri) {
        return topicMapper.getList(userId, cri);
    }

    @Override
    public int getTotal(String userId, Criteria cri) {
        return topicMapper.getTotal(userId, cri);
    }

    @Override
    public TopicVO getDetail(int prodId) {
        return topicMapper.getDetail(prodId);
    }

    @Override
    public int topicUpdate(TopicVO vo) {
        return topicMapper.topicUpdate(vo);
    }

    @Override
    public void topicDelete(int prodId) {
        topicMapper.topicDelete(prodId);
    }
}
