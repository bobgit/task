package github.com.bobgit.study.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * 监听消息队列    ; 这个就好比是 观察者模式，使用观察者模式，使创建订单和消息通知进行分离，低耦合。
 */
@Service
@EnableAsync //开启异步注解 //一旦开始异步执行，方法的异常将不会抛出，只能在方法内部处理。如需在方法外处理异常
public class EventBusListener {
    private final Logger logger = LoggerFactory.getLogger(EventBusListener.class);

    @EventListener(value = EventTest.class) //监控的事件类型
    public void app1EventListener(EventTest event) {//针对的监听事件，对应的处理是
        logger.info("监听接受1号：{}",event);
    }
    @Order(value = 3)
    @EventListener(value = EventTest.class) //监控的事件类型
    @Async //方法开启异步
    public void app2EventListener(EventTest event) {//针对的监听事件，对应的处理是
        int info = 12/0 ;
        logger.info("监听接受2号：{}",event);
    }
    @Order(value = 2)
    @EventListener(value = EventTest.class) //监控的事件类型
    public void app3EventListener(EventTest event) {//针对的监听事件，对应的处理是
        logger.info("监听接受3号：{}",event);
    }
    @Order(value = 1)//@Order传入的值越小，执行顺序越高
    @EventListener(value = EventTest.class) //监控的事件类型
//    @Async  //异步监听器无法发送返回信息。如果开发者需要知道处理事件的结果的话，只能注入ApplicationEventPublisher来手动发送事件
    public void app4EventListener(EventTest event) {//针对的监听事件，对应的处理是
        int info = 12/0 ;
        logger.info("监听接受4号：{}",event);
    }
}
