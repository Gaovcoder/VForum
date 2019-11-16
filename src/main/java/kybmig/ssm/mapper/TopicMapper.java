package kybmig.ssm.mapper;

import kybmig.ssm.model.TopicModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicMapper {
    void insert(TopicModel todo);

    List<TopicModel> selectAll();

    List<TopicModel> select(Integer userId);

    TopicModel selectOne(Integer id);

    void update(TopicModel todo);

    void delete(Integer id);

    TopicModel selectOneWithComments(Integer id);

    TopicModel selectOneWithCommentsAndUser(Integer id);

    TopicModel selectCommentsWithTopicId(Integer TopicId);

    TopicModel findAuthorNameWithTopicId(Integer id);

    List<TopicModel> selectComOnTopics(Integer id);
}
