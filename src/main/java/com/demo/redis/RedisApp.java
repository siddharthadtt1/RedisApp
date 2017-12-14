package com.demo.redis;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.demo.redis.utility.RedisUtility;

import redis.clients.jedis.Jedis;

public class RedisApp {

	// refer to
	// https://github.com/cuiwenyuan/Redis-Windows-32bit
	// to install redis on linux

	private static final Logger logger = Logger.getLogger(RedisApp.class);

	public static void main(String[] args) {
		logger.info("Attempting to start the redis app ...");

		// get Jedis connection
		Jedis jedis = getJedisConnection();

		if (jedis != null) {
			logger.info("Connection to server successfull ");
			logger.info("Server is running : " + jedis.ping());
		}

		logger.info("Redis String operations example : ");
		// Redis Java String Example
		stringOperations(jedis);

		logger.info("Redis List operations example : ");
		// Redis Java List Example
		listOperations(jedis);

		logger.info("Redis Keys operations example : ");
		keysOperations(jedis);

		closeJedisConnection(jedis);
	}

	private static Jedis getJedisConnection() {
		return RedisUtility.getJedisConnection();
	}

	private static void stringOperations(Jedis jedis) {
		String sampleKey = "tutorial-name";
		String sampleValue = "redis_tutorial";

		// set String
		jedis.set(sampleKey, sampleValue);

		// get String
		logger.info("Received value : " + jedis.get(sampleKey) + " for key : " + sampleKey);
	}

	private static void listOperations(Jedis jedis) {
		String sampleKey = "tutorial-list";

		// store data in list in Redis
		jedis.lpush(sampleKey, "Redis");
		jedis.lpush(sampleKey, "Mongodb");
		jedis.lpush(sampleKey, "Mysql");

		// get stored data & print them
		List<String> list = jedis.lrange(sampleKey, 0, 5);

		list.forEach(d -> logger.info("Data stored in list in Redis " + d));

	}

	private static void keysOperations(Jedis jedis) {
		Set<String> keyList = jedis.keys("*");
		keyList.forEach(d -> logger.info("Key stored in Redis"));
	}

	private static void closeJedisConnection(Jedis jedis) {
		RedisUtility.closeJedisConnection(jedis);
	}

}
