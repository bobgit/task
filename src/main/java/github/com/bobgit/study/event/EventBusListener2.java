package github.com.bobgit.study.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

//@Service
public class EventBusListener2 {
    private final Logger logger = LoggerFactory.getLogger(EventBusListener2.class);


    @EventListener(value = EventTest2.class) //监控的事件类型
    public void app1EventListener(EventTest2 event) {//针对的监听事件，对应的处理是
        logger.info("监听@@2@@接受1号：{}",event);
    }
    @Order(value = 13)
    @EventListener(value = EventTest2.class) //监控的事件类型
    public void app2EventListener(EventTest2 event) {//针对的监听事件，对应的处理是
//        int info = 12/0 ;
        logger.info("监听@@2@@接受2号：{}",event);
    }
    @Order(value = 12)
    @EventListener(value = EventTest2.class) //监控的事件类型
    public void app3EventListener(EventTest2 event) {//针对的监听事件，对应的处理是
        logger.info("监听@@2@@接受3号：{}",event);
    }
    @Order(value = 11)//@Order传入的值越小，执行顺序越高
    @EventListener(value = EventTest2.class) //监控的事件类型
//    @Async  //异步监听器无法发送返回信息。如果开发者需要知道处理事件的结果的话，只能注入ApplicationEventPublisher来手动发送事件
    public void app4EventListener(EventTest2 event) {//针对的监听事件，对应的处理是
//        int info = 12/0 ;
        logger.info("监听@@2@@接受4号：{}",event);
    }
}
