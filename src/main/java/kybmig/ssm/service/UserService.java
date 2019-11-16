package kybmig.ssm.service;


import kybmig.ssm.mapper.TopicMapper;
import kybmig.ssm.mapper.UserMapper;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.model.UserRole;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private UserMapper mapper;

    // Spring 会注入 mapper
    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public UserModel add(String username, String password, String mail,String createdTime, String updatedTime) {
        Long unixTime = System.currentTimeMillis() / 1000L;
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        createdTime = dateString;
        updatedTime = dateString;
        UserModel m = new UserModel();
        m.setUsername(username);
        m.setPassword(password);
        m.setMail(mail);
        m.setAvatar("/avatar.png");
        m.setRole(UserRole.normal);
        m.setCreatedTime(createdTime);
        m.setUpdatedTime(updatedTime);
        mapper.insert(m);
        return m;
    }

    public UserModel update(Integer id, String uesrname, String password) {
        UserModel m = new UserModel();
        m.setId(id);
        m.setUsername(uesrname);
        m.setPassword(password);
        mapper.update(m);
        return m;
    }

    public void deleteById(Integer id) {
        mapper.delete(id);
    }

    public  UserModel findById(Integer id) {
        return mapper.selectOne(id);
    }

    public  UserModel findByUsername(String username) {
        return mapper.selectOneByUsername(username);
    }

    public  List<UserModel> all() {
        return mapper.selectAll();
    }

    public boolean validateLogin(String username, String password) {
        UserModel user = mapper.selectOneByUsername(username);
        // if (user == null) {
        //     return false;
        // } else {
        //     if (user.getPassword().equals(password)) {
        //         return true;
        //     } else {
        //         return false;
        //     }
        // }

        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public UserModel guest() {
        UserModel guest = new UserModel();
        guest.setId(-1);
        guest.setUsername("游客");
        guest.setPassword("");
        guest.setRole(UserRole.guest);
        return guest;
    }

    public UserModel currentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer uid = (Integer) session.getAttribute("user_id");
        Integer userId = (Integer) session.getAttribute("user_id");
        if (uid == null) {
            return this.guest();
        } else {
            UserModel current = mapper.selectOne(uid);
            if (current == null) {
                return this.guest();
            } else {
                return current;
            }
        }
    }

    public  UserModel findAuthorNameWithTopicId(Integer id) {
        return mapper.findAuthorNameWithTopicId(id);
    }
}
