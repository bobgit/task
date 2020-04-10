package github.com.bobgit.study.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//可以使核心业务与子业务进行解耦，也方便后期的业务的扩展。如新用户注册之后，需要发放优惠券，此时可以在保存用户之后，发布一个新用户的注册成功事件，通过监听该事件来实现发放优惠券的功能。后期新增一个对新用户进行xxx功能，此时可以新写一个监听注册成功事件的监听器，来处理新的业务逻辑，而不需要修改之前的注册逻辑。

@Component
public class EventTestListener2 implements ApplicationListener<EventTest2> {
    private final Logger logger = LoggerFactory.getLogger(EventTestListener2.class);
    @Override
    public void onApplicationEvent(EventTest2 eventTest) {
        logger.info("事件监听接受0000号：{}",eventTest);
    }
}
