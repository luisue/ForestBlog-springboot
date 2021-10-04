package cn.it.websocket;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luis
 * @date 2021/9/28 15:56
 */
@Component
@ServerEndpoint("/connectWebSocket/{userId}")
public class WebSocket {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在线人数
     */
    private static int onlineNumber = 0;
    /**
     * 以用户名为key,websocket为值保存客户端
     */
    private static Map<String, WebSocket> client = new ConcurrentHashMap<String, WebSocket>();
    /**
     * Websocket session 会话
     */
    private Session session;
    private String userId;

    /**
     * 建立连接
     * @param userId
     * @param session
     */
    public void onOpen(@PathParam("userId") String userId, Session session) {
        onlineNumber++;
        this.session = session;
        this.userId = userId;
        logger.info("当前连接的用户:" + session.getId() + " 用户名:" + userId);

        // 进行信息广播
        // messageType:1上线，2下线，3在线名单，4普通消息
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("messageType", 1);
        map1.put("userId", userId);
        sendMessageAll(JSON.toJSONString(map1));

        //保存当前生成的websocket客户端
        client.put(userId, this);
        logger.info("当前在线人数" + client.size());

        //给自己发送消息，广播除自己外的在线用户名单
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("messageType", 3);
        //取出所有在线用户的userId
        Set<String> onlineUsers = client.keySet();
        map2.put("onlineUsers", onlineUsers);
        sendMessageTo(JSON.toJSONString(map2),userId);

        //todo
    }

    /**
     * 向全体用户广播消息
     * @param message
     */
    public void sendMessageAll(String message) {
        for (WebSocket item : client.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    /**
     * 向指定用户发送消息
     * @param message
     * @param touserId
     */
    public void sendMessageTo(String message,String touserId) {
        for (String user : client.keySet()) {
            if (touserId.equals(user)) {
                client.get(user).session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }
}
