package github.com.bobgit.study;

import github.com.bobgit.study.task.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RefreshTask14 {
    private static final Logger logger = LoggerFactory.getLogger(RefreshTask14.class);

    @Scheduled(cron = "0 53 14 * * ?") //每天凌晨14点45分0
    public void testServiceUrl8800Gett() {
        logger.info("=======每天凌晨14点10分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存设定set信息:{}", result);
    }
    @Scheduled(cron = "30 53 14 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8800Sett() {
        logger.info("=======每天凌晨14点10分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testGetRedies.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存获取get信息:{}", result);
    }
    @Scheduled(cron = "0 54 14 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8801RedisActiveMQt() {
        logger.info("=======每天凌晨14点11分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8801/test/test.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("get信息:{}", result);
    }






    @Scheduled(cron = "0 0 22 * * ?") //每天凌晨14点45分0
    public void testServiceUrl8800Getb() {
        logger.info("=======每天凌晨22点0分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");//http://192.168.0.199:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存设定set信息:{}", result);
    }
    @Scheduled(cron = "30 0 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8800Setb() {
        logger.info("=======每天凌晨22点0分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testGetRedies.json");//http://192.168.0.199:8800/test/testGetRedies.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存获取get信息:{}", result);
    }
    @Scheduled(cron = "0 1 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8801RedisActiveMQb() {
        logger.info("=======每天凌晨22点0分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8801/test/test.json");//http://192.168.0.199:8801/test/test.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("get信息:{}", result);
    }


    @Scheduled(cron = "0 4 22 * * ?") //每天凌晨14点45分0
    public void testServiceUrl8800Geta() {
        logger.info("=======每天凌晨22点4分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");//http://192.168.0.199:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存设定set信息:{}", result);
    }
    @Scheduled(cron = "30 4 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8800Seta() {
        logger.info("=======每天凌晨22点4分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testGetRedies.json");//http://192.168.0.199:8800/test/testGetRedies.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存获取get信息:{}", result);
    }
    @Scheduled(cron = "0 5 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8801RedisActiveMQa() {
        logger.info("=======每天凌晨22点5分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8801/test/test.json");//http://192.168.0.199:8801/test/test.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("get信息:{}", result);
    }




    @Scheduled(cron = "0 4 22 * * ?") //每天凌晨14点45分0
    public void testServiceUrl8800Getab() {
        logger.info("=======每天凌晨22点4分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");//http://192.168.0.199:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存设定set信息:{}", result);
    }
    @Scheduled(cron = "30 4 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8800Setab() {
        logger.info("=======每天凌晨22点4分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testGetRedies.json");//http://192.168.0.199:8800/test/testGetRedies.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存获取get信息:{}", result);
    }
    @Scheduled(cron = "0 5 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8801RedisActiveMQab() {
        logger.info("=======每天凌晨22点5分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8801/test/test.json");//http://192.168.0.199:8801/test/test.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("get信息:{}", result);
    }



    @Scheduled(cron = "0 10 22 * * ?") //每天凌晨14点45分0
    public void testServiceUrl8800Get() {
        logger.info("=======每天凌晨22点10分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");//http://192.168.0.199:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存设定set信息:{}", result);
    }
    @Scheduled(cron = "30 10 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8800Set() {
        logger.info("=======每天凌晨22点10分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testGetRedies.json");//http://192.168.0.199:8800/test/testGetRedies.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存获取get信息:{}", result);
    }
    @Scheduled(cron = "0 11 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8801RedisActiveMQ() {
        logger.info("=======每天凌晨22点11分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8801/test/test.json");//http://192.168.0.199:8801/test/test.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("get信息:{}", result);
    }



    @Scheduled(cron = "0 15 22 * * ?") //每天凌晨14点45分0
    public void testServiceUrl8800Get2() {
        logger.info("=======每天凌晨22点15分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");//http://192.168.0.199:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存设定set信息:{}", result);
    }
    @Scheduled(cron = "30 15 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8800Set2() {
        logger.info("=======每天凌晨22点15分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testGetRedies.json");//http://192.168.0.199:8800/test/testGetRedies.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存获取get信息:{}", result);
    }
    @Scheduled(cron = "0 16 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8801RedisActiveMQ2() {
        logger.info("=======每天凌晨22点16分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8801/test/test.json");//http://192.168.0.199:8801/test/test.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("get信息:{}", result);
    }





    @Scheduled(cron = "0 20 22 * * ?") //每天凌晨14点45分0
    public void testServiceUrl8800Get3() {
        logger.info("=======每天凌晨22点20分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");//http://192.168.0.199:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存设定set信息:{}", result);
    }
    @Scheduled(cron = "30 20 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8800Set3() {
        logger.info("=======每天凌晨22点20分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testGetRedies.json");//http://192.168.0.199:8800/test/testGetRedies.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存获取get信息:{}", result);
    }
    @Scheduled(cron = "0 21 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8801RedisActiveMQ3() {
        logger.info("=======每天凌晨22点21分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8801/test/test.json");//http://192.168.0.199:8801/test/test.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("get信息:{}", result);
    }




    @Scheduled(cron = "0 24 22 * * ?") //每天凌晨14点45分0
    public void testServiceUrl8800Get4() {
        logger.info("=======每天凌晨22点24分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");//http://192.168.0.199:8800/test/testSetRedies.json?value=bobTaskTestSetRedies");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存设定set信息:{}", result);
    }
    @Scheduled(cron = "30 24 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8800Set4() {
        logger.info("=======每天凌晨22点24分20 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8800/test/testGetRedies.json");//http://192.168.0.199:8800/test/testGetRedies.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("缓存获取get信息:{}", result);
    }
    @Scheduled(cron = "0 25 22 * * ?") //每天凌晨14点45分20
    public void testServiceUrl8801RedisActiveMQ4() {
        logger.info("=======每天凌晨22点25分0 测试服务14是否到位======");
        String result = HttpUtils.get("http://192.168.101.14:8801/test/test.json");//http://192.168.0.199:8801/test/test.json");
        result = "我已经接收到你服务器14的信息是:" + result;
        logger.info("get信息:{}", result);
    }
    
}
