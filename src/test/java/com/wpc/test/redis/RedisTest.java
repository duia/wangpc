package com.wpc.test.redis;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import com.wpc.sys.model.User;
import com.wpc.sys.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:config/spring-mybatis.xml",
"classpath:config/spring-redis.xml"
})
public class RedisTest {

	@Autowired
	private UserServiceImpl userService;
	
	@Resource
	private RedisTemplate<String, User> redisTemplate;
	
	@Test
    public void testaddObject(){  
        System.out.println("执行测试");
        
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<User>(User.class);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        
        User user1 = userService.findById(2L);
        User user2 = userService.findById(4L);
        
//        Set<String> set = redisTemplate.keys("*");
//        System.out.println(set.size());
//        for (String string : set) {
//			System.out.println(SerializeUtil.unserialize(string.getBytes()));
//			System.out.println("---------------------");
//		}
        
//        redisTemplate.delete("111111");
        
//        SortQuery<String> query = SortQueryBuilder.sort("UserZSet").noSort().get("id").build();
//        System.out.println(redisTemplate.sort(query));
        
        /**
         * ValueOperations
         */
//        redisTemplate.opsForValue().append("111", "222");
//        redisTemplate.opsForValue().set("111111", user2);
//        System.out.println(redisTemplate.opsForValue().get("111111"));
//        System.out.println(redisTemplate.opsForValue().get("111111"));
//        System.out.println(redisTemplate.opsForValue().size("111111"));
//        redisTemplate.opsForHash().delete("USER", user1.getId());
        
        /**
         * ListOperations
         */
//        redisTemplate.opsForList().leftPush("222222", user1);//
//        System.out.println(redisTemplate.opsForList().leftPop("222222"));
          
        /**
         * SetOperations
         */
//        System.out.println(redisTemplate.opsForSet().add("UserSet", user1, user2));
        
        /**
         * ZSetOperations
         */
//        System.out.println(redisTemplate.opsForZSet().add("UserZSet", user2, 2));
//        Set<TypedTuple<User>> set = redisTemplate.opsForZSet().rangeWithScores("UserZSet", 0L, 1L);
//        Iterator<ZSetOperations.TypedTuple<User>> iterator = set.iterator();
//        while(iterator.hasNext()){
//        	System.out.println(iterator.next().getValue());
//        }
//        System.out.println(redisTemplate.hasKey("UserZSet1"));
//        System.out.println(redisTemplate.opsForZSet().range("UserZSet", 0, -1));
//        System.out.println(redisTemplate.opsForZSet().rangeByScore("UserZSet", Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        
        /**
         * HashOperations
         */
//        redisTemplate.opsForHash().putAll("user:"+user1.getId(), user1);
        
        System.out.println("运行完成");  
    }
}
