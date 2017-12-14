package com.demo.redis.utility;

import redis.clients.jedis.Jedis;

public class RedisUtility {

	private static final String REMOTE_IP = "10.250.104.30";
	private static final int PORT_NO = 6379;

	// create a new Jedis connection
	public static Jedis getJedisConnection() {
		Jedis jedis = new Jedis();
		return jedis;
	}

	// close Jedis connection
	public static void closeJedisConnection(Jedis jedis) {
		if (jedis.isConnected()) {
			jedis.close();
			jedis.disconnect();
			jedis = null;
		}
	}

}
