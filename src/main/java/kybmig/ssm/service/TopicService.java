package kybmig.ssm.service;


import kybmig.ssm.mapper.TodoMapper;
import kybmig.ssm.mapper.TopicMapper;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TopicService {
    private TopicMapper mapper;

    // Spring 会注入 mapper
    public TopicService(TopicMapper mapper) {
        this.mapper = mapper;
    }

    public TopicModel add(Integer userId, String title, String content, String createdTime, String updatedTime) {
        Long unixTime = System.currentTimeMillis() / 1000L;
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        createdTime = dateString;
        updatedTime = dateString;
        TopicModel m = new TopicModel();
        m.setTitle(title);
        m.setContent(content);
        m.setUserId(userId);
        m.setCreatedTime(createdTime);
        m.setUpdatedTime(updatedTime);
        mapper.insert(m);
        return m;
    }


    public TopicModel update(Integer id, String title, String content, String updatedTime) {
        TopicModel m = new TopicModel();
        Long unixTime = System.currentTimeMillis() / 1000L;
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        updatedTime = dateString;//待debug
        m.setId(id);
        m.setContent(content);
        m.setTitle(title);
        m.setUpdatedTime(updatedTime);
        mapper.update(m);
        return m;
    }


    public void deleteById(Integer id) {
        mapper.delete(id);
    }

    public TopicModel findById(Integer id) {
        return mapper.selectOne(id);
    }

    public  TopicModel findByTopicId(Integer TopicId) {
        return mapper.selectOne(TopicId);
    }

    public  List<TopicModel> all() {
        return mapper.selectAll();
    }

    public  List<TopicModel> findByIdList(Integer userId) {
        return mapper.select(userId);
    }

    public  List<TopicModel> findComOnTopicsById(Integer userId) {
        return mapper.selectComOnTopics(userId);
    }


    public  TopicModel findAuthorNameWithTopicId(Integer id) { return mapper.selectOne(id);
    }

    public TopicModel findByIdWithCommeents(Integer id) {
        // 自己发多次请求组装数据
        // TopicModel topic = mapper.selectOne(id);
        // List<TopicCommentModel> comments = commentMapper.selectAllByTopicId(topic.getId());
        // topic.setCommentList(new ArrayList<>(comments));

        TopicModel topic = mapper.selectOneWithComments(id);
        return topic;
    }

    public TopicModel findByIdWithCommeentsAndUser(Integer id) {
        TopicModel topic = mapper.selectOneWithCommentsAndUser(id);
        return topic;}

    public TopicModel findByUserIdWithCommeents(Integer userid) {
        TopicModel topic = mapper.selectCommentsWithTopicId(userid);
        return topic;}

}
