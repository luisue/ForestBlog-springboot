package cn.it.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author luis
 * @date 2021/9/28 15:50
 */
@Configuration
public class WebSocketStompConfig {
    /**
     * 用于扫描带有@ServerEndpoint的注解成为websocket,外置tomcat无需此配置
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
