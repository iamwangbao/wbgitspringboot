package com.wangbao.springbootrocketmqservice;

import com.wangbao.springbootrocketmqservice.Module.RocketMqConsumer;
import com.wangbao.springbootrocketmqservice.pojo.User;
import com.wangbao.springbootrocketmqservice.service.impl.RocketMqProducer;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.SendCallback;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.web.bind.annotation.RequestBody;


@SpringBootTest
class SpringbootRocketmqServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @SneakyThrows
    @Test
    void connettest(){
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("test-group");
        // Specify name server addresses.
        producer.setNamesrvAddr("127.0.0.1:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest11" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();

    }

    @SneakyThrows
    @Test
    void settopictest() {
        //分组名haoke这个可以任意设置
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        //设置nameserver的地址
        producer.setNamesrvAddr("127.0.0.1:9876");

        //启动生产者
        producer.start();

        //producer.setCreateTopicKey("User");

        /**
         * 创建topic，参数分别是：borker的名称，topic的名称，queue的数量
         * broker要和虚拟机broker.conf配置文件中brokername的名字一致
         * newTopic的名字随便起，queueNum8的意思是新建的消息队列数为8个
         */
        producer.createTopic("TBW102","User",8);
        System.out.println("topic创建成功！");
        //producer.shutdown();

    }

    @SneakyThrows
    @Test
    void getSyncProducer() {
        //分组名haoke这个可以任意设置
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        //设置nameserver的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setSendMsgTimeout(8000);

        //启动生产者
        producer.start();

        //发送消息
        String msg = "我的第一个消息!";
        Message message = new Message("my-topic", "mytag", msg.getBytes("UTF-8"));
        SendResult sendResult = producer.send(message);
        System.out.println("消息id: " + sendResult.getMsgId());
        System.out.println("消息队列: " + sendResult.getMessageQueue());
        System.out.println("消息offset值: " + sendResult.getQueueOffset());
        System.out.println(sendResult);
        producer.shutdown();

    }

    @SneakyThrows
    @Test
    void getAsyncProducer() {
        //分组名haoke这个可以任意设置
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        //设置nameserver的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setSendMsgTimeout(8000);
        //启动生产者
        producer.start();

        // 发送消息
        String msg = "我的第一个异步发送消息!";
        Message message = new Message("my-topic", msg.getBytes("UTF-8"));
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功了!" + sendResult);
                System.out.println("消息id：" + sendResult.getMsgId());
                System.out.println("消息队列：" + sendResult.getMessageQueue());
                System.out.println("消息offset值：" + sendResult.getQueueOffset());
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("消息发送失败!" + e);
            }
        });

//        producer.shutdown();

    }

    @SneakyThrows
    @Test
    public void setInformation() {
        String json = "{\n" +
                "    \"userName\": \"只狼\",\n" +
                "    \"password\": \"12345678\",\n" +
                "    \"nickName\": \"狼\",\n" +
                "    \"sex\": \"男\",\n" +
                "    \"phone\": \"123456789\"\n" +
                "}";
        RocketMqConsumer rocketMqConsumer = new RocketMqConsumer();
        User user = new User();
        //转换成为JSONObject对象
        JSONObject jsonObj = new JSONObject(json);
        //将json对象的值赋给user对象
        user.setUserName(jsonObj.getString("userName"));
        user.setPassword(jsonObj.getString("password"));
        user.setNickName(jsonObj.getString("nickName"));
        user.setSex(jsonObj.getString("sex"));
        user.setPhone(jsonObj.getInt("phone"));
        RocketMqProducer rocketMqProducer = new RocketMqProducer();
        for (int i = 0; i < 1000; i++) {
            rocketMqProducer.send("User", user);
            rocketMqConsumer.onMessage("User");

        }
    }
}
