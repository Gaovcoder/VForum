package kybmig.ssm.mapper;

import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicCommentMapper {
    void insert(TopicCommentModel todo);

    List<TopicCommentModel> selectAll();

    TopicCommentModel selectOne(Integer id);

    void update(TopicCommentModel todo);

    void delete(Integer id);

    List<TopicModel> selectcomment(Integer userId);

}
