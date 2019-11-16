package kybmig.ssm.service;


import kybmig.ssm.mapper.TopicCommentMapper;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TopicCommentService {
    private TopicCommentMapper mapper;

    // Spring 会注入 mapper
    public TopicCommentService(TopicCommentMapper mapper) {
        this.mapper = mapper;
    }

    public TopicCommentModel add(Integer userId, String content,Integer topicId, String createdTime, String updatedTime) {
        Long unixTime = System.currentTimeMillis() / 1000L;
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        createdTime = dateString;//待debug
        updatedTime = dateString;//待debug
        TopicCommentModel m = new TopicCommentModel();
        m.setUserId(userId);
        m.setContent(content);
        m.setTopicId(topicId);
        m.setCreatedTime(createdTime);
        m.setUpdatedTime(updatedTime);
        mapper.insert(m);
        return m;
    }


    public TopicCommentModel update(Integer id, String content, String updatedTime) {
        TopicCommentModel m = new TopicCommentModel();
        Long unixTime = System.currentTimeMillis() / 1000L;
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        updatedTime = dateString;
        m.setId(id);
        m.setContent(content);
        m.setUpdatedTime(updatedTime);
        mapper.update(m);
        return m;
    }


    public void deleteById(Integer id) {
        mapper.delete(id);
    }


    public  TopicCommentModel findById(Integer id) {
        return mapper.selectOne(id);
    }

    public  List<TopicCommentModel> all() {
        return mapper.selectAll();
    }

    public  List<TopicModel> findCommentByuserId(Integer userId) {
        return mapper.selectcomment(userId);
    }

}