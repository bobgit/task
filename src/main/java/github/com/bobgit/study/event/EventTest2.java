package github.com.bobgit.study.event;

import github.com.bobgit.study.po.CommonUser;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;

//Spring事件机制
/*Spring中的事件监听使用的是观察者模式
        所有事件需要继承ApplicationEvent父类
        所有的监听器需要实现ApplicationListener接口
        事件发布需要通过ApplicationContext中的publisherEvent方法实现
        监听器的注册是ApplicationEventMulticaster提供的，但我们并不需要实现。

观察者模式（Observer）：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。



        */
public class EventTest2 extends PayloadApplicationEvent<CommonUser> {

    private static final long serialVersionUID = -4693086771841511254L;


    /**
     * Create a new PayloadApplicationEvent.
     *
     * @param source  the object on which the event initially occurred (never {@code null})
     * @param payload the payload object (never {@code null})
     */
    public EventTest2(Object source, CommonUser payload) {
        super(source, payload);
    }
}
