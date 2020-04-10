package github.com.bobgit.study.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final Logger logger = LoggerFactory.getLogger(EventPublisher.class);
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private ApplicationContext context;

    /**
     * 发送更新redis缓存的队列消息
     */
    public void publishSelfEven(Object source, Long typeId,Long userId,String phoneNumber) {
        EventTest event = new EventTest(source,typeId,userId,phoneNumber);
        logger.info("具体针对事件{},群发事件，任何监控者均应捕获处置", event);
        this.context.publishEvent(event); // 这个事件对应监控处理，都在待命中，切面是执行时才切开看，事件监控是群发的
    }

    public void publishEven(ApplicationEvent applicationEvent) {
        logger.info("目前针对事件内容{}触发广播，任何监控者均应捕获处置  事件类是:{}", applicationEvent, applicationEvent.getClass().getTypeName());
        this.context.publishEvent(applicationEvent);
    }
}
