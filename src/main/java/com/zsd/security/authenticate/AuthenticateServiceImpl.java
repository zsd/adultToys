package com.zsd.security.authenticate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 身份认证服务实现类.
 * @author zhousd
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private static Logger logger = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

//    @Autowired
//    private UserWebService userWebService;
//
//    @Autowired
//    private PositionWebService postWebService;
//
//    @Transactional(readOnly = true)
//    public Post authenticateAfter(String loginName, Long postId) {
//        Post post = null;
//        try {
//            if (StringUtils.isNotBlank(loginName)) {
//                gzrd.wsclient.post.Position position = postWebService.getPositionById(postId);
//                gzrd.wsclient.user.UserInfo userInfo = userWebService.getUserInfoByName(loginName);
//
//                post = this.convertPost(position);
//                Department department = this.convertDepartment(position);
//                Unit unit = this.convertUnit(position);
//                department.setParent(unit);
//                post.setParent(department);
//                User user = this.convertUser(userInfo);
//                post.setUser(user);
//
//
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            throw new ApplicationException(e.getMessage(), e);
//        }
//
//        return post;
//    }

}
