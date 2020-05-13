package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** 
* j Tester. 
* 
* @author <Authors name> 
* @since <pre>3月 25, 2020</pre> 
* @version 1.0 
*/ 
public class jTest { 

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }

    @Test
    public void test() throws Exception {
//        Jedis jd = new Jedis("47.99.106.114",6379);
//        jd.auth("sun+moon");
//        jd.set("age", "22");
//        System.out.println(jd.get("age"));
//        jd.close();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxWaitMillis(10);
        JedisPool jd = new JedisPool(config,"47.99.106.114",6379);
        Jedis j = null;
        try {
            j = jd.getResource();
            j.auth("sun+moon");
            j.set("name", "raychen");
            System.out.println(j.get("name"));
        } finally {
            if(j != null){
                j.close();
            }if(jd != null){
                jd.close();
            }
        }
    }


} 
