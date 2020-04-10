package github.com.bobgit.study;

import github.com.bobgit.study.event.EventPublisher;
import github.com.bobgit.study.event.EventTest;
import github.com.bobgit.study.event.EventTest2;
import github.com.bobgit.study.jsonResponse.CommonResponse;
import github.com.bobgit.study.po.CommonUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApiModel(value = "公用的请求接口")
@RestController
@RequestMapping("/common")
public class CommonController {
    private final Logger log = LoggerFactory.getLogger(EventPublisher.class);
    @Autowired
    private EventPublisher eventPublisher;
    @Autowired
    private ApplicationContext context;// 直接发布
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;////去,就是给ApplicationContext包裹一层来进行发布事件的

    @ApiOperation(value = "测试eventPublisher信息值情况,将参数从数据库中加载到redis缓存")
    @RequestMapping(value = "/eventPublisher.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResponse<String> eventPublisher(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
        CommonResponse<String> res = new CommonResponse<String>();
//        context.publishEvent(new EventTest("发布刷新redis缓存指令",1L,111L,"13666178888"));
//        applicationEventPublisher.publishEvent(new EventTest("发布刷新redis缓存指令",1L,111L,"13666178888"));
//        RefreshRedisCacheEvent event = new RefreshRedisCacheEvent("****************");
//        this.context.publishEvent(event);

        log.info("触发事件发布机制-----------》》》》");
        this.eventPublisher.publishSelfEven("发布刷新redis缓存指令",1L,111L,"13666178888");
        //this.eventPublisher.sendRefreshRedisCacheEvent(EnumSet.of(RefreshRedisFlag.REFRESH_SYSTEM_PARAMETER),10,TimeUnit.SECONDS);
        //this.eventPublisher.sendRefreshRedisCacheEvent(EnumSet.allOf(RefreshRedisFlag.class));
        res.setData("已经将参数从数据库中加载到redis缓存");
        return res;
    }
    @ApiOperation(value = "测试eventAppPush信息值情况,推送消息")
    @RequestMapping(value = "/eventAppPush.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResponse<String> eventAppPush(HttpServletRequest request, HttpServletResponse response) {
        CommonResponse<String> res = new CommonResponse<String>();
//        RefreshRedisCacheEvent event = new RefreshRedisCacheEvent("****************");
//        this.context.publishEvent(event);
        log.info("触发事件发布机制-----------》》》》");
        EventTest event = new EventTest("app push");
        //this.context.publishEvent(event);
//        applicationEventPublisher.publishEvent(event);
//        this.eventPublisher.publishEven(event);

        CommonUser payload = new CommonUser();
        payload.setTypeId(7L);
        payload.setUserId(8L);
        EventTest2 eventTest2 = new EventTest2("事件处理",payload);
        applicationEventPublisher.publishEvent(eventTest2);
        res.setData("已经推送消息");
        return res;
    }
}
