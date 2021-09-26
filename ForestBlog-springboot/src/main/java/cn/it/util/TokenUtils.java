package cn.it.util;

import cn.it.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author luis
 * @date 2021/9/19 15:56
 */
public class TokenUtils {
    /**
     * 生成token
     * @param user
     * @return
     */
    public static String getToken(User user) {
        String token="";
        //实体类中userId为Integer类型，此处转换为String类型
        token= JWT.create().withAudience(user.getUserId().toString())
                .sign(Algorithm.HMAC256(user.getUserPass()));
        return token;
    }
}
