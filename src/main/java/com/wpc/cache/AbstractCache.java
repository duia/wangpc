/**
 * 项目名：duia-web-admin <br>
 * 包名： com.duia.tiku.common.cache <br>
 * 文件名：AbstractCache.java <br>
 * 版本信息：TODO <br>
 * 作者：赵增斌 E-mail：zhaozengbin@gmail.com QQ:4415599 weibo:http://weibo.com/zhaozengbin<br>
 * 日期：2017年3月27日-下午2:25:16<br>
 * Copyright (c) 2017 赵增斌-版权所有<br>
 *
 */
package com.wpc.cache;

import com.wpc.util.base.ObjectUtils;
import com.wpc.util.io.SerializeUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisListCommands.Position;
import org.springframework.data.redis.connection.RedisZSetCommands.Tuple;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.util.*;

/**
 *
 * 类名称：AbstractCache <br>
 * 类描述：缓存抽象类 <br>
 * 创建人：赵增斌 <br>
 * 修改人：赵增斌 <br>
 * 修改时间：2017年3月27日 下午2:25:16 <br>
 * 修改备注：TODO <br>
 *
 */
public abstract class AbstractCache {

	/** KEY连接符 */
	public static String SYMBOL_CONNECTOR = ".";

	/**
	 * 方法：ping <br>
	 * 描述：ping缓存服务器 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:45:39 <br>
	 *
	 * @return
	 */
	public boolean ping() {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				String result = connection.ping();
				if (!ObjectUtils.isEmpty(result) && "PONG".equalsIgnoreCase(result)) {
					return true;
				}
				return false;
			}
		});
	}
	// *******************************************Keys*******************************************//

	/**
	 * 方法：renameNX <br>
	 * 描述： 更改key,仅当新key不存在时才执行 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:45:39 <br>
	 *
	 * @param oldkey
	 * @param newkey
	 * @return
	 */
	public boolean renameNX(String oldkey, String newkey) {
		return renameNX(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey));
	}

	/**
	 * 方法：renameNX <br>
	 * 描述： 更改key,仅当新key不存在时才执行 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:45:39 <br>
	 *
	 * @param oldkey
	 * @param newkey
	 * @return
	 */
	public boolean renameNX(final byte[] oldkey, final byte[] newkey) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.renameNX(oldkey, newkey);
			}
		});
	}

	/**
	 * 方法：expire <br>
	 * 描述：设置key的过期时间，以秒为单位 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:47:27 <br>
	 *
	 * @param key
	 * @param seconds
	 * @return
	 */
	public boolean expire(String key, long seconds) {
		return expire(SafeEncoder.encode(key), seconds);
	}

	/**
	 * 方法：expire <br>
	 * 描述：设置key的过期时间，以秒为单位 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:47:27 <br>
	 *
	 * @param key
	 * @param seconds
	 * @return
	 */
	public boolean expire(final byte[] key, final long seconds) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.expire(key, seconds);
			}
		});

	}

	/**
	 * 方法：expireAt <br>
	 * 描述：设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:49:18 <br>
	 *
	 * @param key
	 * @param date
	 * @return
	 */
	public boolean expireAt(String key, Long date) {
		return expireAt(SafeEncoder.encode(key), date);
	}

	/**
	 * 方法：expireAt <br>
	 * 描述：设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:49:18 <br>
	 *
	 * @param key
	 * @param date
	 * @return
	 */
	public boolean expireAt(final byte[] key, final Long date) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.expireAt(key, date);
			}
		});
	}

	/**
	 * 方法：ttl <br>
	 * 描述： 查询key的过期时间 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:49:47 <br>
	 *
	 * @param key
	 * @return
	 */
	public long ttl(String key) {
		return ttl(SafeEncoder.encode(key));
	}

	/**
	 * 方法：ttl <br>
	 * 描述： 查询key的过期时间 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:49:47 <br>
	 *
	 * @param key
	 * @return
	 */
	public long ttl(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.ttl(key);
			}
		});
	}

	/**
	 * 方法：persist <br>
	 * 描述：取消对key过期时间的设置 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:50:39 <br>
	 *
	 * @param key
	 * @return
	 */
	public boolean persist(String key) {
		return persist(SafeEncoder.encode(key));
	}

	/**
	 * 方法：persist <br>
	 * 描述：取消对key过期时间的设置 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:50:39 <br>
	 *
	 * @param key
	 * @return
	 */
	public boolean persist(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.persist(key);
			}
		});
	}

	/**
	 * 方法：del <br>
	 * 描述：删除keys对应的记录,可以是多个key <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:51:22 <br>
	 *
	 * @param keys
	 */
	public boolean del(String... keys) {
		return del(SafeEncoder.encodeMany(keys));
	}

	/**
	 * 方法：del <br>
	 * 描述：删除keys对应的记录,可以是多个key <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:51:22 <br>
	 *
	 * @param keys
	 */
	public boolean del(final byte[]... keys) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				Long result = connection.del(keys);
				if (result == 1) {
					return true;
				} else {
					return false;
				}
			}
		});
	}

	/**
	 * 方法：exists <br>
	 * 描述：判断key是否存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:16:22 <br>
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return exists(SafeEncoder.encode(key));
	}

	/**
	 * 方法：exists <br>
	 * 描述：判断key是否存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:16:22 <br>
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(key);
			}
		});
	}

	/**
	 * 方法：sort <br>
	 * 描述：对List,Set,SortSet进行排序或limit <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:21:51 <br>
	 *
	 * @param key
	 * @param parame 定义排序类型或limit的起止位置.
	 * @return List<String> 全部或部分记录
	 */
	public List<String> sort(String key, SortParameters parame) {
		return ObjectUtils.byteConvertToString(sort(SafeEncoder.encode(key), parame));
	}

	/**
	 * 方法：sortObj <br>
	 * 描述：对List,Set,SortSet进行排序或limit <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:21:51 <br>
	 *
	 * @param clazz
	 * @param key
	 * @param parame 定义排序类型或limit的起止位置.
	 * @return List<String> 全部或部分记录
	 */
	public <T> List<T> sortObj(Class<T> clazz, String key, final SortParameters parame) {
		return ObjectUtils.byteConvertToObject(clazz, sort(SafeEncoder.encode(key), parame));
	}

	/**
	 * 方法：sort <br>
	 * 描述：对List,Set,SortSet进行排序或limit <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:21:51 <br>
	 *
	 * @param key
	 * @param parame 定义排序类型或limit的起止位置.
	 * @return List<String> 全部或部分记录
	 */
	public List<byte[]> sort(final byte[] key, final SortParameters parame) {
		return getRedisTemplate().execute(new RedisCallback<List<byte[]>>() {
			public List<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sort(key, parame);
			}
		});
	}

	/**
	 * 方法：type <br>
	 * 描述：返回指定key存储的类型 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:23:52 <br>
	 *
	 *
	 * @param key
	 * @return String string|list|set|zset|hash
	 */
	public String type(final String key) {
		return getRedisTemplate().execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				DataType dataType = connection.type(SafeEncoder.encode(key));
				if (ObjectUtils.isNotEmpty(dataType)) {
					return dataType.name();
				} else {
					return null;
				}
			}
		});

	}

	/**
	 * 方法：keys <br>
	 * 描述：查找所有匹配给定的模式的键 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:24:54 <br>
	 *
	 * @param pattern key的表达式,*表示多个，？表示一个
	 */
	public Set<String> keys(String pattern) {
		return new HashSet<String>(
				ObjectUtils.byteConvertToString(new ArrayList<byte[]>(keys(SafeEncoder.encode(pattern)))));
	}

	/**
	 * 方法：keys <br>
	 * 描述：查找所有匹配给定的模式的键 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:24:54 <br>
	 *
	 * @param pattern
	 *            key的表达式,*表示多个，？表示一个
	 */
	public Set<byte[]> keys(final byte[] pattern) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.keys(pattern);
			}
		});
	}

	// *******************************************Sets*******************************************//

	/**
	 * 方法：sAdd <br>
	 * 描述：向Set添加一条记录，如果value已存在返回0,否则返回1 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:26:55 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sAdd(String key, String value) {
		return sAdd(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：sAdd <br>
	 * 描述：向Set添加一条记录，如果value已存在返回0,否则返回1 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:26:55 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sAdd(String key, Object value) {
		return sAdd(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：sAdd <br>
	 * 描述：向Set添加一条记录，如果value已存在返回0,否则返回1 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:31:00 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sAdd(byte[] key, byte[] value) {
		return sAdd(key, value, -1);
	}

	/**
	 * 方法：sAdd <br>
	 * 描述：向Set添加一条记录，如果value已存在返回0,否则返回1 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:26:55 <br>
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public boolean sAdd(String key, String value, long seconds) {
		return sAdd(SafeEncoder.encode(key), SafeEncoder.encode(value), seconds);
	}

	/**
	 * 方法：sAdd <br>
	 * 描述：向Set添加一条记录，如果value已存在返回0,否则返回1 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:26:55 <br>
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public boolean sAdd(String key, Object value, long seconds) {
		return sAdd(SafeEncoder.encode(key), SerializeUtils.serialize(value), seconds);
	}

	/**
	 * 方法：sAdd <br>
	 * 描述：向Set添加一条记录，如果value已存在返回0,否则返回1 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:31:00 <br>
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public boolean sAdd(final byte[] key, final byte[] value, final long seconds) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				long result = connection.sAdd(key, value);
				if (seconds > 0) {
					connection.expire(key, seconds);
				}
				if (result == 1) {
					return true;
				} else {
					return false;
				}
			}
		});
	}

	/**
	 * 方法：sCard <br>
	 * 描述：获取给定key中元素个数 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:34:42 <br>
	 *
	 * @param key
	 * @return 元素个数
	 */
	public long sCard(String key) {
		return sCard(SafeEncoder.encode(key));
	}

	/**
	 * 方法：sCard <br>
	 * 描述：获取给定key中元素个数 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:34:42 <br>
	 *
	 * @param key
	 * @return 元素个数
	 */
	public long sCard(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sCard(key);
			}
		});
	}

	/**
	 * 方法：sDiff <br>
	 * 描述：返回从第一组和所有的给定集合之间的差异的成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:36:27 <br>
	 *
	 * @param keys
	 * @return 差异的成员集合
	 */
	public Set<String> sDiff(String... keys) {
		return new HashSet<String>(
				ObjectUtils.byteConvertToString(new ArrayList<byte[]>(sDiff(SafeEncoder.encodeMany(keys)))));
	}

	/**
	 * 方法：sDiffObj <br>
	 * 描述：返回从第一组和所有的给定集合之间的差异的成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:36:27 <br>
	 *
	 * @param clazz
	 * @param keys
	 * @return 差异的成员集合
	 */
	public <T> Set<T> sDiffObj(Class<T> clazz, String... keys) {
		return new HashSet<T>(
				ObjectUtils.byteConvertToObject(clazz, new ArrayList<byte[]>(sDiff(SafeEncoder.encodeMany(keys)))));
	}

	/**
	 * 方法：sDiff <br>
	 * 描述：返回从第一组和所有的给定集合之间的差异的成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:36:27 <br>
	 *
	 * @param keys
	 * @return 差异的成员集合
	 */
	public Set<byte[]> sDiff(final byte[]... keys) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sDiff(keys);
			}
		});
	}

	/**
	 * 方法：sDiffStore <br>
	 * 描述：这个命令等于sdiff,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:37:48 <br>
	 *
	 * @param newkey 新结果集的key
	 * @param keys 比较的集合
	 * @return 新集合中的记录数
	 */
	public long sDiffStore(String newkey, String... keys) {
		return sDiffStore(SafeEncoder.encode(newkey), SafeEncoder.encodeMany(keys));

	}

	/**
	 * 方法：sDiffStore <br>
	 * 描述：这个命令等于sdiff,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:37:48 <br>
	 *
	 * @param newkey 新结果集的key
	 * @param keys 比较的集合
	 * @return 新集合中的记录数
	 */
	public long sDiffStore(final byte[] newkey, final byte[]... keys) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sDiffStore(newkey, keys);
			}
		});

	}

	/**
	 * 方法：sInter <br>
	 * 描述：返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:39:53 <br>
	 *
	 *
	 * @param keys
	 * @return 交集成员的集合
	 */
	public Set<String> sInter(String... keys) {
		return new HashSet<String>(
				ObjectUtils.byteConvertToString(new ArrayList<byte[]>(sInter(SafeEncoder.encodeMany(keys)))));
	}

	/**
	 * 方法：sInterObj <br>
	 * 描述：返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:39:53 <br>
	 *
	 * @param clazz
	 * @param keys
	 * @return 交集成员的集合
	 */
	public <T> Set<T> sInterObj(Class<T> clazz, String... keys) {
		return new HashSet<T>(
				ObjectUtils.byteConvertToObject(clazz, new ArrayList<byte[]>(sInter(SafeEncoder.encodeMany(keys)))));
	}

	/**
	 * 方法：sInter <br>
	 * 描述：返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:39:53 <br>
	 *
	 *
	 * @param keys
	 * @return 交集成员的集合
	 */
	public Set<byte[]> sInter(final byte[]... keys) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sInter(keys);
			}
		});

	}

	/**
	 * 方法：sInterStore <br>
	 * 描述：这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:40:37 <br>
	 *
	 * @param newkey 新结果集的key
	 * @param keys 比较的集合
	 * @return 新集合中的记录数
	 */
	public long sInterStore(String newkey, String... keys) {
		return sInterStore(SafeEncoder.encode(newkey), SafeEncoder.encodeMany(keys));
	}

	/**
	 * 方法：sInterStore <br>
	 * 描述：这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:40:37 <br>
	 *
	 * @param String
	 *            newkey 新结果集的key
	 * @param String
	 *            ... keys 比较的集合
	 * @return 新集合中的记录数
	 */
	public long sInterStore(final byte[] newkey, final byte[]... keys) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sInterStore(newkey, keys);
			}
		});
	}

	/**
	 * 方法：sIsMember <br>
	 * 描述：确定一个给定的值是否存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:42:32 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value 要判断的值
	 * @return 存在返回1，不存在返回0
	 */
	public boolean sIsMember(String key, String value) {
		return sIsMember(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：sIsMember <br>
	 * 描述：确定一个给定的值是否存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:42:32 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value 要判断的值
	 * @return 存在返回1，不存在返回0
	 */
	public boolean sIsMember(String key, Object value) {
		return sIsMember(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：sIsMember <br>
	 * 描述：确定一个给定的值是否存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:42:32 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value 要判断的值
	 * @return 存在返回1，不存在返回0
	 */
	public boolean sIsMember(final byte[] key, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sIsMember(key, value);
			}
		});
	}

	/**
	 * 方法：sMembers <br>
	 * 描述：返回集合中的所有成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:44:07 <br>
	 *
	 * @param key
	 * @return
	 */
	public Set<String> sMembers(String key) {
		return new HashSet<String>(
				ObjectUtils.byteConvertToString(new ArrayList<byte[]>(sMembers(SafeEncoder.encode(key)))));
	}

	/**
	 * 方法：sMembersObj <br>
	 * 描述：返回集合中的所有成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:44:07 <br>
	 *
	 * @param Class
	 *            clazz
	 * @param key
	 * @return
	 */
	public <T> Set<T> sMembersObj(Class<T> clazz, String key) {
		return new HashSet<T>(
				ObjectUtils.byteConvertToObject(clazz, new ArrayList<byte[]>(sMembers(SafeEncoder.encode(key)))));
	}

	/**
	 * 方法：sMembers <br>
	 * 描述：返回集合中的所有成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:44:38 <br>
	 *
	 * @param key
	 * @return
	 */
	public Set<byte[]> sMembers(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sMembers(key);
			}
		});
	}

	/**
	 * 方法：sMove <br>
	 * 描述： 将成员从源集合移出放入目标集合 <br/>
	 * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0<br/>
	 * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:46:27 <br>
	 *
	 * @param String
	 *            srckey 源集合
	 * @param String
	 *            dstkey 目标集合
	 * @param String
	 *            value 源集合中的成员
	 * @return 状态码，1成功，0失败
	 */
	public boolean sMove(String srckey, String dstkey, String value) {
		return sMove(SafeEncoder.encode(srckey), SafeEncoder.encode(dstkey), SafeEncoder.encode(value));
	}

	/**
	 * 方法：sMove <br>
	 * 描述： 将成员从源集合移出放入目标集合 <br/>
	 * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0<br/>
	 * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:46:27 <br>
	 *
	 * @param String
	 *            srckey 源集合
	 * @param String
	 *            dstkey 目标集合
	 * @param String
	 *            value 源集合中的成员
	 * @return 状态码，1成功，0失败
	 */
	public boolean sMove(String srckey, String dstkey, Object value) {
		return sMove(SafeEncoder.encode(srckey), SafeEncoder.encode(dstkey), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：sMove <br>
	 * 描述： 将成员从源集合移出放入目标集合 <br/>
	 * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0<br/>
	 * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:46:27 <br>
	 *
	 * @param String
	 *            srckey 源集合
	 * @param String
	 *            dstkey 目标集合
	 * @param String
	 *            value 源集合中的成员
	 * @return 状态码，1成功，0失败
	 */
	public boolean sMove(final byte[] srckey, final byte[] dstkey, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sMove(srckey, dstkey, value);
			}
		});
	}

	/**
	 * 方法：sPop <br>
	 * 描述：从集合中删除成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:49:52 <br>
	 *
	 * @param String
	 *            key
	 * @return 被删除的成员
	 */
	public String sPop(String key) {
		return SafeEncoder.encode(sPop(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：sPop <br>
	 * 描述：从集合中删除成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:49:52 <br>
	 *
	 * @param String
	 *            key
	 * @return 被删除的成员
	 */
	public byte[] sPop(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sPop(key);
			}
		});
	}

	/**
	 * 方法：sRem <br>
	 * 描述：从集合中删除指定成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:49:23 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value 要删除的成员
	 * @return 状态码，成功返回1，成员不存在返回0
	 */
	public long sRem(String key, String value) {
		return sRem(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：sRem <br>
	 * 描述：从集合中删除指定成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:49:23 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value 要删除的成员
	 * @return 状态码，成功返回1，成员不存在返回0
	 */
	public long sRem(String key, Object value) {
		return sRem(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：sRem <br>
	 * 描述：从集合中删除指定成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:49:23 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value 要删除的成员
	 * @return 状态码，成功返回1，成员不存在返回0
	 */
	public long sRem(final byte[] key, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sRem(key, value);
			}
		});
	}

	/**
	 * 方法：sUnion <br>
	 * 描述：合并多个集合并返回合并后的结果，合并后的结果集合并不保存<br/>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:51:14 <br>
	 *
	 * @param String
	 *            ... keys
	 * @return 合并后的结果集合
	 */
	public Set<String> sUnion(String... keys) {
		return new HashSet<String>(
				ObjectUtils.byteConvertToString(new ArrayList<byte[]>(sUnion(SafeEncoder.encodeMany(keys)))));
	}

	/**
	 * 方法：sUnion <br>
	 * 描述：合并多个集合并返回合并后的结果，合并后的结果集合并不保存<br/>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:51:14 <br>
	 *
	 * @param Class
	 *            clazz
	 * @param String
	 *            ... keys
	 * @return 合并后的结果集合
	 */
	public <T> Set<T> sUnionObj(Class<T> clazz, String... keys) {
		return new HashSet<T>(
				ObjectUtils.byteConvertToObject(clazz, new ArrayList<byte[]>(sUnion(SafeEncoder.encodeMany(keys)))));
	}

	/**
	 * 方法：sUnion <br>
	 * 描述：合并多个集合并返回合并后的结果，合并后的结果集合并不保存<br/>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:51:14 <br>
	 *
	 * @param String
	 *            ... keys
	 * @return 合并后的结果集合
	 */
	public Set<byte[]> sUnion(final byte[]... keys) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sUnion(keys);
			}
		});
	}

	/**
	 * 方法：sUnionStore <br>
	 * 描述：合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:52:02 <br>
	 *
	 * @param String
	 *            newkey 新集合的key
	 * @param String
	 *            ... keys 要合并的集合
	 * @return
	 */
	public long sUnionStore(String newkey, String... keys) {
		return sUnionStore(SafeEncoder.encode(newkey), SafeEncoder.encodeMany(keys));
	}

	/**
	 * 方法：sUnionStore <br>
	 * 描述：合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:52:02 <br>
	 *
	 * @param String
	 *            newkey 新集合的key
	 * @param String
	 *            ... keys 要合并的集合
	 * @return
	 */
	public long sUnionStore(final byte[] newkey, final byte[]... keys) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.sUnionStore(newkey, keys);
			}
		});
	}
	// *******************************************SortSet*******************************************//

	/**
	 * 方法：zAdd <br>
	 * 描述：向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:53:30 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 权重
	 * @param String
	 *            value 要加入的值，
	 * @return 状态码 1成功，0已存在value的值
	 */
	public boolean zAdd(String key, final Double score, String value) {
		return zAdd(SafeEncoder.encode(key), score, SafeEncoder.encode(value));
	}

	/**
	 * 方法：zAdd <br>
	 * 描述：向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:53:30 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 权重
	 * @param String
	 *            value 要加入的值，
	 * @return 状态码 1成功，0已存在value的值
	 */
	public boolean zAdd(String key, final Double score, Object value) {
		return zAdd(SafeEncoder.encode(key), score, SerializeUtils.serialize(value));
	}

	/**
	 * 方法：zAdd <br>
	 * 描述：向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:53:30 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 权重
	 * @param String
	 *            value 要加入的值，
	 *
	 * @return 状态码 1成功，0已存在value的值
	 */
	public boolean zAdd(final byte[] key, final Double score, final byte[] value) {
		return zAdd(key, score, value, -1);
	}

	/**
	 * 方法：zAdd <br>
	 * 描述：向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:53:30 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 权重
	 * @param String
	 *            value 要加入的值，
	 * @return 状态码 1成功，0已存在value的值
	 */
	public boolean zAdd(String key, final Double score, String value, long seconds) {
		return zAdd(SafeEncoder.encode(key), score, SafeEncoder.encode(value), seconds);
	}

	/**
	 * 方法：zAdd <br>
	 * 描述：向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:53:30 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 权重
	 * @param String
	 *            value 要加入的值，
	 * @return 状态码 1成功，0已存在value的值
	 */
	public boolean zAdd(String key, final Double score, Object value, long seconds) {
		return zAdd(SafeEncoder.encode(key), score, SerializeUtils.serialize(value), seconds);
	}

	/**
	 * 方法：zAdd <br>
	 * 描述：向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:53:30 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 权重
	 * @param String
	 *            value 要加入的值，
	 *
	 * @param long
	 *            value 时间
	 * @return 状态码 1成功，0已存在value的值
	 */
	public boolean zAdd(final byte[] key, final Double score, final byte[] value, final long seconds) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				boolean result = connection.zAdd(key, score, value);
				if (seconds > 0) {
					connection.expire(key, seconds);
				}
				return result;
			}
		});
	}

	/**
	 * 方法：zAdd <br>
	 * 描述：向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:58:07 <br>
	 *
	 * @param key
	 * @param tuples
	 * @return
	 */
	public long zAdd(String key, final Set<Tuple> tuples) {
		return zAdd(SafeEncoder.encode(key), tuples);
	}

	/**
	 * 方法：zAdd <br>
	 * 描述：向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午3:58:07 <br>
	 *
	 * @param key
	 * @param tuples
	 * @return
	 */
	public long zAdd(final byte[] key, final Set<Tuple> tuples) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zAdd(key, tuples);
			}
		});
	}

	/**
	 * 方法：zCard <br>
	 * 描述：获取集合中元素的数量 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:00:16 <br>
	 *
	 * @param String
	 *            key
	 * @return 如果返回0则集合不存在
	 */
	public long zCard(String key) {
		return zCard(SafeEncoder.encode(key));
	}

	/**
	 * 方法：zCard <br>
	 * 描述：获取集合中元素的数量 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:00:16 <br>
	 *
	 * @param String
	 *            key
	 * @return 如果返回0则集合不存在
	 */
	public long zCard(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zCard(key);
			}
		});
	}

	/**
	 * 方法：zCount <br>
	 * 描述：获取指定权重区间内集合的数量 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:01:39 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 最小排序位置
	 * @param double
	 *            max 最大排序位置
	 */
	public long zCount(String key, double min, double max) {
		return zCount(SafeEncoder.encode(key), min, max);
	}

	/**
	 * 方法：zCount <br>
	 * 描述：获取指定权重区间内集合的数量 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:01:39 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 最小排序位置
	 * @param double
	 *            max 最大排序位置
	 */
	public long zCount(final byte[] key, final double min, final double max) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zCount(key, min, max);
			}
		});
	}

	/**
	 * 方法：zLength <br>
	 * 描述： 获得set的长度 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:04:05 <br>
	 *
	 * @param key
	 * @return
	 */
	public long zLength(String key) {
		long len = 0;
		Set<String> set = zRange(key, 0, -1);
		len = set.size();
		return len;
	}

	/**
	 * 方法：zLength <br>
	 * 描述： 获得set的长度 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:04:05 <br>
	 *
	 * @param key
	 * @return
	 */
	public long zLength(final byte[] key) {
		long len = 0;
		Set<byte[]> set = zRange(key, 0, -1);
		len = set.size();
		return len;
	}

	/**
	 * 方法：zIncrBy <br>
	 * 描述：权重增加给定值，如果给定的value已存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:04:51 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 要增的权重
	 * @param String
	 *            value 要插入的值
	 * @return 增后的权重
	 */
	public double zIncrBy(String key, final Double score, String value) {
		return zIncrBy(SafeEncoder.encode(key), score, SafeEncoder.encode(value));
	}

	/**
	 * 方法：zIncrBy <br>
	 * 描述：权重增加给定值，如果给定的value已存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:04:51 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 要增的权重
	 * @param String
	 *            value 要插入的值
	 * @return 增后的权重
	 */
	public double zIncrBy(String key, final Double score, Object value) {
		return zIncrBy(SafeEncoder.encode(key), score, SerializeUtils.serialize(value));
	}

	/**
	 * 方法：zIncrBy <br>
	 * 描述：权重增加给定值，如果给定的value已存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:04:51 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 要增的权重
	 * @param String
	 *            value 要插入的值
	 * @return 增后的权重
	 */
	public double zIncrBy(final byte[] key, final Double score, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Double>() {
			public Double doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zIncrBy(key, score, value);
			}
		});
	}

	/**
	 * 方法：zRange <br>
	 * 描述：返回指定位置的集合元素,0为第一个元素，-1为最后一个元素 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:08:48 <br>
	 *
	 * @param String
	 *            key
	 * @param int
	 *            start 开始位置(包含)
	 * @param int
	 *            end 结束位置(包含)
	 */
	public Set<String> zRange(String key, int start, int end) {
		return new HashSet<String>(
				ObjectUtils.byteConvertToString(new ArrayList<byte[]>(zRange(SafeEncoder.encode(key), start, end))));
	}

	/**
	 * 方法：zRange <br>
	 * 描述：返回指定位置的集合元素,0为第一个元素，-1为最后一个元素 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:08:48 <br>
	 *
	 * @param String
	 *            key
	 * @param int
	 *            start 开始位置(包含)
	 * @param int
	 *            end 结束位置(包含)
	 */
	public <T> Set<T> zRangeObj(Class<T> clazz, String key, int start, int end) {
		return new HashSet<T>(ObjectUtils.byteConvertToObject(clazz,
				new ArrayList<byte[]>(zRange(SafeEncoder.encode(key), start, end))));
	}

	/**
	 * 方法：zRange <br>
	 * 描述：返回指定位置的集合元素,0为第一个元素，-1为最后一个元素 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:08:48 <br>
	 *
	 * @param String
	 *            key
	 * @param int
	 *            start 开始位置(包含)
	 * @param int
	 *            end 结束位置(包含)
	 */
	public Set<byte[]> zRange(final byte[] key, final int start, final int end) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zRange(key, start, end);
			}
		});
	}

	/**
	 * 方法：zRangeByScore <br>
	 * 描述：返回指定权重区间的元素集合 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:10:20 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 上限权重
	 * @param double
	 *            max 下限权重
	 * @return
	 */
	public Set<String> zRangeByScore(String key, final Double min, final Double max) {
		return new HashSet<String>(ObjectUtils
				.byteConvertToString(new ArrayList<byte[]>(zRangeByScore(SafeEncoder.encode(key), min, max))));
	}

	/**
	 * 方法：zRangeByScoreObj <br>
	 * 描述：返回指定权重区间的元素集合 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:10:20 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 上限权重
	 * @param double
	 *            max 下限权重
	 * @return
	 */
	public <T> Set<T> zRangeByScoreObj(Class<T> clazz, String key, final Double min, final Double max) {
		return new HashSet<T>(ObjectUtils.byteConvertToObject(clazz,
				new ArrayList<byte[]>(zRangeByScore(SafeEncoder.encode(key), min, max))));
	}

	/**
	 * 方法：zRangeByScore <br>
	 * 描述：返回指定权重区间的元素集合 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:10:20 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 上限权重
	 * @param double
	 *            max 下限权重
	 * @return
	 */
	public Set<byte[]> zRangeByScore(final byte[] key, final Double min, final Double max) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zRangeByScore(key, min, max);
			}
		});
	}

	/**
	 * 方法：zRank <br>
	 * 描述：获取指定值在集合中的位置，集合排序从低到高 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:11:06 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 位置
	 */
	public long zRank(String key, String value) {
		return zRank(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：zRank <br>
	 * 描述：获取指定值在集合中的位置，集合排序从低到高 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:11:06 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 位置
	 */
	public long zRank(String key, Object value) {
		return zRank(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：zRank <br>
	 * 描述：获取指定值在集合中的位置，集合排序从低到高 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:11:06 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 位置
	 */
	public long zRank(final byte[] key, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zRank(key, value);
			}
		});
	}

	/**
	 * 方法：zRevRank <br>
	 * 描述：获取指定值在集合中的位置，集合排序从高到低 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月28日 下午12:23:11 <br>
	 *
	 * @param key
	 * @param value
	 * @return long 位置
	 */
	public long zRevRank(String key, String value) {
		return zRevRank(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：zRevRank <br>
	 * 描述：获取指定值在集合中的位置，集合排序从高到低 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月28日 下午12:23:23 <br>
	 *
	 * @param key
	 * @param value
	 * @return long 位置
	 */
	public long zRevRank(String key, Object value) {
		return zRevRank(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：zRevRank <br>
	 * 描述：获取指定值在集合中的位置，集合排序从高到低 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月28日 下午12:23:40 <br>
	 *
	 * @param key
	 * @param value
	 * @return long 位置
	 */
	public long zRevRank(final byte[] key, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zRevRank(key, value);
			}
		});
	}

	/**
	 * 方法：zRem <br>
	 * 描述：从集合中删除成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:15:53 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public long zRem(String key, String value) {
		return zRem(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：zRem <br>
	 * 描述：从集合中删除成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:15:53 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public long zRem(String key, Object value) {
		return zRem(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：zRem <br>
	 * 描述：从集合中删除成员 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:15:53 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public long zRem(final byte[] key, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zRem(key, value);
			}
		});
	}

	/**
	 * 方法：zRem <br>
	 * 描述：删除 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:17:38 <br>
	 *
	 * @param key
	 * @return
	 */
	public long zRem(String key) {
		return zRem(SafeEncoder.encode(key));
	}

	/**
	 * 方法：zRem <br>
	 * 描述：删除 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:17:38 <br>
	 *
	 * @param key
	 * @return
	 */
	public long zRem(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zRem(key);
			}
		});
	}

	/**
	 * 方法：zRemRangeByScore <br>
	 * 描述：删除给定权重区间的元素 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:19:54 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 下限权重(包含)
	 * @param double
	 *            max 上限权重(包含)
	 * @return 删除的数量
	 * @return
	 */
	public long zRemRangeByScore(String key, final Double min, final Double max) {
		return zRemRangeByScore(SafeEncoder.encode(key), min, min);
	}

	/**
	 * 方法：zRemRangeByScore <br>
	 * 描述：删除给定权重区间的元素 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:19:54 <br>
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 下限权重(包含)
	 * @param double
	 *            max 上限权重(包含)
	 * @return 删除的数量
	 * @return
	 */
	public long zRemRangeByScore(final byte[] key, final Double min, final Double max) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zRemRangeByScore(key, min, min);
			}
		});
	}

	/**
	 * 方法：zRevRange <br>
	 * 描述：获取给定区间的元素，原始按照权重由高到低排序 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:20:55 <br>
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zRevRange(String key, int start, int end) {
		return new HashSet<String>(
				ObjectUtils.byteConvertToString(new ArrayList<byte[]>(zRevRange(SafeEncoder.encode(key), start, end))));

	}

	/**
	 * 方法：zRevRange <br>
	 * 描述：获取给定区间的元素，原始按照权重由高到低排序 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:20:55 <br>
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public <T> Set<T> zRevRangeObj(Class<T> clazz, String key, int start, int end) {
		return new HashSet<T>(ObjectUtils.byteConvertToObject(clazz,
				new ArrayList<byte[]>(zRevRange(SafeEncoder.encode(key), start, end))));

	}

	/**
	 * 方法：zRevRange <br>
	 * 描述：获取给定区间的元素，原始按照权重由高到低排序 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:20:55 <br>
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<byte[]> zRevRange(final byte[] key, final int start, final int end) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zRevRange(key, start, end);
			}
		});
	}

	/**
	 * 方法：zScore <br>
	 * 描述： 获取给定值在集合中的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:21:19 <br>
	 *
	 * @param String
	 *            key
	 * @param memeber
	 * @return double 权重
	 */
	public double zScore(String key, String value) {
		return zScore(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：zScore <br>
	 * 描述： 获取给定值在集合中的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:21:19 <br>
	 *
	 * @param String
	 *            key
	 * @param memeber
	 * @return double 权重
	 */
	public double zScore(String key, Object value) {
		return zScore(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：zScore <br>
	 * 描述： 获取给定值在集合中的权重 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:21:19 <br>
	 *
	 * @param String
	 *            key
	 * @param memeber
	 * @return double 权重
	 */
	public double zScore(final byte[] key, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Double>() {
			public Double doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.zScore(key, value);
			}
		});
	}
	// *******************************************Hash*******************************************//

	/**
	 * 方法：hDel <br>
	 * 描述：从hash中删除指定的存储 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:23:16 <br>
	 *
	 * @param key
	 * @param fieid
	 * @return
	 */
	public boolean hDel(String key, String fieid) {
		return hDel(SafeEncoder.encode(key), SafeEncoder.encode(fieid));
	}

	/**
	 * 方法：hDel <br>
	 * 描述：从hash中删除指定的存储 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:23:16 <br>
	 *
	 * @param key
	 * @param fieid
	 * @return
	 */
	public boolean hDel(final byte[] key, final byte[] fieid) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				long result = connection.hDel(key, fieid);
				if (result == 1) {
					return true;
				} else {
					return false;
				}
			}
		});
	}

	/**
	 * 方法：hDel <br>
	 * 描述：删除key <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:24:00 <br>
	 *
	 * @param key
	 * @return
	 */
	public boolean hDel(String key) {
		return hDel(SafeEncoder.encode(key));
	}

	/**
	 * 方法：hDel <br>
	 * 描述：删除key <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:24:00 <br>
	 *
	 * @param key
	 * @return
	 */
	public boolean hDel(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				long result = connection.hDel(key);
				if (result == 1) {
					return true;
				} else {
					return false;
				}
			}
		});
	}

	/**
	 * 方法：hExists <br>
	 * 描述：测试hash中指定的存储是否存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:25:03 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return
	 */
	public boolean hExists(String key, String fieid) {
		return hExists(SafeEncoder.encode(key), SafeEncoder.encode(fieid));
	}

	/**
	 * 方法：hExists <br>
	 * 描述：测试hash中指定的存储是否存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:25:03 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return
	 */
	public boolean hExists(String key, Object fieid) {
		return hExists(SafeEncoder.encode(key), SerializeUtils.serialize(fieid));
	}

	/**
	 * 方法：hExists <br>
	 * 描述：测试hash中指定的存储是否存在 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:25:03 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return
	 */
	public boolean hExists(final byte[] key, final byte[] fieid) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hExists(key, fieid);
			}
		});
	}

	/**
	 * 方法：hGet <br>
	 * 描述： 返回hash中指定存储位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:25:56 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 存储对应的值
	 */
	public String hGet(String key, String fieid) {
		return SafeEncoder.encode(hGet(SafeEncoder.encode(key), SafeEncoder.encode(fieid)));
	}

	/**
	 * 方法：hGetObj <br>
	 * 描述： 返回hash中指定存储位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:25:56 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 存储对应的值
	 */
	public Object hGetObj(String key, String fieid) {
		return SerializeUtils.deserialize(hGet(SafeEncoder.encode(key), SafeEncoder.encode(fieid)));
	}

	/**
	 * 方法：hGet <br>
	 * 描述：返回hash中指定存储位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:28:57 <br>
	 *
	 * @param key
	 * @param fieid
	 * @return
	 */
	public byte[] hGet(final byte[] key, final byte[] fieid) {
		return getRedisTemplate().execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hGet(key, fieid);
			}
		});
	}

	/**
	 * 方法：hGetAll <br>
	 * 描述：以Map的形式返回hash中的存储和值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:30:22 <br>
	 *
	 * @param key
	 * @return
	 */
	public Map<String, String> hGetAll(String key) {
		return ObjectUtils.byteConvertToString(hGetAll(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：hGetAllObj <br>
	 * 描述：以Map的形式返回hash中的存储和值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:30:22 <br>
	 *
	 * @param key
	 * @return
	 */
	public <T> Map<String, T> hGetAllObj(Class<T> clazz, String key) {
		return ObjectUtils.byteConvertToObject(clazz, hGetAll(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：hGetAll <br>
	 * 描述：以Map的形式返回hash中的存储和值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:30:22 <br>
	 *
	 * @param key
	 * @return
	 */
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Map<byte[], byte[]>>() {
			public Map<byte[], byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hGetAll(key);
			}
		});
	}

	/**
	 * 方法：hSet <br>
	 * 描述：添加一个对应关系 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:31:15 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @return
	 */
	public boolean hSet(String key, String fieid, String value) {
		return hSet(SafeEncoder.encode(key), SafeEncoder.encode(fieid), SafeEncoder.encode(value));
	}

	/**
	 * 方法：hSet <br>
	 * 描述：添加一个对应关系 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:31:15 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @return
	 */
	public boolean hSet(String key, String fieid, Object value) {
		return hSet(SafeEncoder.encode(key), SafeEncoder.encode(fieid), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：hSet <br>
	 * 描述：添加一个对应关系 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:31:15 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @return
	 */
	public boolean hSet(final byte[] key, final byte[] fieid, final byte[] value) {
		return hSet(key, fieid, value, -1);
	}

	/**
	 * 方法：hSet <br>
	 * 描述：添加一个对应关系 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:31:15 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @param seconds
	 * @return
	 */
	public boolean hSet(String key, String fieid, String value, long seconds) {
		return hSet(SafeEncoder.encode(key), SafeEncoder.encode(fieid), SafeEncoder.encode(value), seconds);
	}

	/**
	 * 方法：hSet <br>
	 * 描述：添加一个对应关系 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:31:15 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @param seconds
	 * @return
	 */
	public boolean hSet(String key, String fieid, Object value, long seconds) {
		return hSet(SafeEncoder.encode(key), SafeEncoder.encode(fieid), SerializeUtils.serialize(value), seconds);
	}

	/**
	 * 方法：hSet <br>
	 * 描述：添加一个对应关系 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:31:15 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @return
	 */
	public boolean hSet(final byte[] key, final byte[] fieid, final byte[] value, final long seconds) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				boolean result = connection.hSet(key, fieid, value);
				if (seconds > 0) {
					connection.expire(key, seconds);
				}
				return result;
			}
		});
	}

	/**
	 * 方法：hsetnx <br>
	 * 描述：添加对应关系，只有在fieid不存在时才执行 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:32:50 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @return
	 */
	public boolean hSetNX(String key, String fieid, String value) {
		return hSetNX(SafeEncoder.encode(key), SafeEncoder.encode(fieid), SafeEncoder.encode(value));
	}

	/**
	 * 方法：hsetnx <br>
	 * 描述：添加对应关系，只有在fieid不存在时才执行 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:32:50 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @return
	 */
	public boolean hSetNX(String key, String fieid, Object value) {
		return hSetNX(SafeEncoder.encode(key), SafeEncoder.encode(fieid), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：hsetnx <br>
	 * 描述：添加对应关系，只有在fieid不存在时才执行 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:32:50 <br>
	 *
	 * @param key
	 * @param fieid
	 * @param value
	 * @return
	 */
	public boolean hSetNX(final byte[] key, final byte[] fieid, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hSetNX(key, fieid, value);
			}
		});
	}

	/**
	 * 方法：hVals <br>
	 * 描述： 获取hash中value的集合 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:34:43 <br>
	 *
	 * @param key
	 * @return
	 */
	public List<String> hVals(String key) {
		return ObjectUtils.byteConvertToString(new ArrayList<byte[]>(hVals(SafeEncoder.encode(key))));
	}

	/**
	 * 方法：hValsObj <br>
	 * 描述： 获取hash中value的集合 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:34:43 <br>
	 *
	 * @param key
	 * @return
	 */
	public <T> List<T> hValsObj(Class<T> clazz, String key) {
		return ObjectUtils.byteConvertToObject(clazz, new ArrayList<byte[]>(hVals(SafeEncoder.encode(key))));
	}

	/**
	 * 方法：hVals <br>
	 * 描述： 获取hash中value的集合 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:34:43 <br>
	 *
	 * @param key
	 * @return
	 */
	public List<byte[]> hVals(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<List<byte[]>>() {
			public List<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hVals(key);
			}
		});
	}

	/**
	 * 方法：hIncrBy <br>
	 * 描述：在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:35:57 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储位置
	 * @param String
	 *            long value 要增加的值,可以是负数
	 * @return 增加指定数字后，存储位置的值
	 */
	public long hIncrBy(String key, String fieid, final Long value) {
		return hIncrBy(SafeEncoder.encode(key), SafeEncoder.encode(fieid), value);
	}

	/**
	 * 方法：hIncrBy <br>
	 * 描述：在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:35:57 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储位置
	 * @param String
	 *            long value 要增加的值,可以是负数
	 * @return 增加指定数字后，存储位置的值
	 */
	public long hIncrBy(final byte[] key, final byte[] fieid, final Long value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hIncrBy(key, fieid, value);
			}
		});
	}

	/**
	 * 方法：hKeys <br>
	 * 描述：返回指定hash中的所有存储名字,类似Map中的keySet方法 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:37:01 <br>
	 *
	 * @param String
	 *            key
	 * @return Set<String> 存储名称的集合
	 */
	public Set<String> hKeys(String key) {
		return new HashSet<String>(
				ObjectUtils.byteConvertToString(new ArrayList<byte[]>(hKeys(SafeEncoder.encode(key)))));

	}

	/**
	 * 方法：hKeys <br>
	 * 描述：返回指定hash中的所有存储名字,类似Map中的keySet方法 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:37:01 <br>
	 *
	 * @param String
	 *            key
	 * @return Set<String> 存储名称的集合
	 */
	public Set<byte[]> hKeys(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hKeys(key);
			}
		});
	}

	/**
	 * 方法：hLen <br>
	 * 描述：获取hash中存储的个数，类似Map中size方法 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:37:49 <br>
	 *
	 * @param String
	 *            key
	 * @return long 存储的个数
	 */
	public long hLen(String key) {
		return hLen(SafeEncoder.encode(key));
	}

	/**
	 * 方法：hLen <br>
	 * 描述：获取hash中存储的个数，类似Map中size方法 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:37:49 <br>
	 *
	 * @param String
	 *            key
	 * @return long 存储的个数
	 */
	public long hLen(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hLen(key);
			}
		});
	}

	/**
	 * 方法：hMGet <br>
	 * 描述：根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:39:03 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            ... fieids 存储位置
	 * @return
	 */
	public List<String> hMGet(String key, String... fieids) {
		return ObjectUtils.byteConvertToString(hMGet(SafeEncoder.encode(key), SafeEncoder.encodeMany(fieids)));
	}

	/**
	 * 方法：hMGet <br>
	 * 描述：根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:40:17 <br>
	 *
	 * @param key
	 * @param fieids
	 * @return
	 */
	public List<byte[]> hMGet(final byte[] key, final byte[]... fieids) {
		return getRedisTemplate().execute(new RedisCallback<List<byte[]>>() {
			public List<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hMGet(key, fieids);
			}
		});
	}

	/**
	 * 方法：hMSet <br>
	 * 描述：添加对应关系，如果对应关系已存在，则覆盖 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:42:34 <br>
	 *
	 * @param key
	 * @param map
	 * @return
	 */
	public <T> Boolean hMSet(Class<T> clazz, String key, final Map<String, String> map) {
		return hMSet(SafeEncoder.encode(key), ObjectUtils.stringConvertToByte(map));
	}

	/**
	 * 方法：hMSetObj <br>
	 * 描述：添加对应关系，如果对应关系已存在，则覆盖 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:42:34 <br>
	 *
	 * @param key
	 * @param map
	 * @return
	 */
	public <T> Boolean hMSetObj(Class<T> clazz, String key, final Map<String, T> map) {
		return hMSet(SafeEncoder.encode(key), ObjectUtils.objectConvertToByte(clazz, map));
	}

	/**
	 * 方法：hMSet <br>
	 * 描述：添加对应关系，如果对应关系已存在，则覆盖 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:42:34 <br>
	 *
	 * @param key
	 * @param map
	 * @return
	 */
	public Boolean hMSet(final byte[] key, final Map<byte[], byte[]> map) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hMSet(key, map);
				return true;
			}
		});
	}

	// *******************************************Strings*******************************************//
	/**
	 * 方法：get <br>
	 * 描述：根据key获取记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:43:38 <br>
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return SafeEncoder.encode(get(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：getObj <br>
	 * 描述：根据key获取记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:43:38 <br>
	 *
	 * @param key
	 * @return
	 */
	public Object getObj(String key) {
		return SerializeUtils.deserialize(get(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：get <br>
	 * 描述：根据key获取记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:44:03 <br>
	 *
	 * @param key
	 * @return
	 */
	public byte[] get(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key);
			}
		});
	}

	/**
	 * 方法：setEx <br>
	 * 描述：添加有过期时间的记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:45:58 <br>
	 *
	 * @param String
	 *            key
	 * @param int
	 *            seconds 过期时间，以秒为单位
	 * @return
	 */
	public boolean setEx(String key, int seconds, String value) {
		return setEx(SafeEncoder.encode(key), seconds, SafeEncoder.encode(value));
	}

	/**
	 * 方法：setEx <br>
	 * 描述：添加有过期时间的记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:45:58 <br>
	 *
	 * @param String
	 *            key
	 * @param int
	 *            seconds 过期时间，以秒为单位
	 * @return
	 */
	public boolean setEx(String key, int seconds, Object value) {
		return setEx(SafeEncoder.encode(key), seconds, SerializeUtils.serialize(value));
	}

	/**
	 * 方法：setEx <br>
	 * 描述：TODO <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:46:35 <br>
	 *
	 * @param key
	 * @param seconds
	 *            过期时间，以秒为单位
	 * @param value
	 * @return
	 */
	public boolean setEx(final byte[] key, final int seconds, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(key, seconds, value);
				return true;
			}
		});
	}

	/**
	 * 方法：setNX <br>
	 * 描述： 添加一条记录，仅当给定的key不存在时才插入 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:48:01 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setNX(String key, String value) {
		return setNX(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：setNX <br>
	 * 描述： 添加一条记录，仅当给定的key不存在时才插入 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:48:01 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setNX(String key, Object value) {
		return setNX(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：setNX <br>
	 * 描述： 添加一条记录，仅当给定的key不存在时才插入 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:48:01 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setNX(final byte[] key, final byte[] value) {
		return setNX(key, value, -1);
	}

	/**
	 * 方法：setNX <br>
	 * 描述： 添加一条记录，仅当给定的key不存在时才插入 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:48:01 <br>
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public boolean setNX(String key, String value, long seconds) {
		return setNX(SafeEncoder.encode(key), SafeEncoder.encode(value), seconds);
	}

	/**
	 * 方法：setNX <br>
	 * 描述： 添加一条记录，仅当给定的key不存在时才插入 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:48:01 <br>
	 *
	 * @param key
	 * @param value
	 *
	 * @param seconds
	 *
	 * @return
	 */
	public boolean setNX(String key, Object value, long seconds) {
		return setNX(SafeEncoder.encode(key), SerializeUtils.serialize(value), seconds);
	}

	/**
	 * 方法：setNX <br>
	 * 描述： 添加一条记录，仅当给定的key不存在时才插入 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:48:01 <br>
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 *
	 * @return
	 */
	public boolean setNX(final byte[] key, final byte[] value, final long seconds) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				boolean reulst = connection.setNX(key, value);
				if (seconds > 0) {
					connection.expire(key, seconds);
				}
				return reulst;
			}
		});
	}

	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 状态码
	 */
	public boolean set(String key, String value) {
		return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 状态码
	 */
	public boolean set(String key, Object value) {
		return set(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：set <br>
	 * 描述：添加记录,如果记录已存在将覆盖原有的value <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:50:21 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final byte[] key, final byte[] value) {
		return set(key, value, -1);
	}

	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @param long
	 *            seconds
	 * @return 状态码
	 */
	public boolean set(String key, String value, long seconds) {
		return set(SafeEncoder.encode(key), SafeEncoder.encode(value), seconds);
	}

	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @param long
	 *            seconds
	 * @return 状态码
	 */
	public boolean set(String key, Object value, long seconds) {
		return set(SafeEncoder.encode(key), SerializeUtils.serialize(value), seconds);
	}

	/**
	 * 方法：set <br>
	 * 描述：添加记录,如果记录已存在将覆盖原有的value <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:50:21 <br>
	 *
	 * @param key
	 * @param value
	 * @param long
	 *            seconds
	 * @return
	 */
	public boolean set(final byte[] key, final byte[] value, final long seconds) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(key, value);
				if (seconds > 0) {
					connection.expire(key, seconds);
				}
				return true;
			}
		});
	}

	/**
	 * 方法：setRange <br>
	 * 描述：从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
	 * 例:String str1="123456789";<br/>
	 * 对str1操作后setRange(key,4,0000)，str1="123400009"; <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:51:48 <br>
	 *
	 * @param key
	 * @param value
	 * @param offset
	 * @return
	 */
	public Boolean setRange(String key, String value, long offset) {
		return setRange(SafeEncoder.encode(key), SafeEncoder.encode(value), offset);
	}

	/**
	 * 方法：setRange <br>
	 * 描述：从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
	 * 例:String str1="123456789";<br/>
	 * 对str1操作后setRange(key,4,0000)，str1="123400009"; <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:51:48 <br>
	 *
	 * @param key
	 * @param value
	 * @param offset
	 * @return
	 */
	public Boolean setRange(String key, Object value, long offset) {
		return setRange(SafeEncoder.encode(key), SerializeUtils.serialize(value), offset);
	}

	/**
	 * 方法：setRange <br>
	 * 描述：从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
	 * 例:String str1="123456789";<br/>
	 * 对str1操作后setRange(key,4,0000)，str1="123400009"; <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:51:48 <br>
	 *
	 * @param key
	 * @param value
	 * @param offset
	 * @return
	 */
	public Boolean setRange(final byte[] key, final byte[] value, final long offset) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setRange(key, value, offset);
				return true;
			}
		});
	}

	/**
	 * 方法：append <br>
	 * 描述：在指定的key中追加value <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:52:21 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 追加后value的长度
	 */
	public long append(String key, String value) {
		return append(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	/**
	 * 方法：append <br>
	 * 描述：在指定的key中追加value <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:52:21 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 追加后value的长度
	 */
	public long append(String key, Object value) {
		return append(SafeEncoder.encode(key), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：append <br>
	 * 描述：在指定的key中追加value <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:52:21 <br>
	 *
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 追加后value的长度
	 */
	public long append(final byte[] key, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.append(key, value);
			}
		});
	}

	/**
	 * 方法：decrBy <br>
	 * 描述：将key对应的value减去指定的值，只有value可以转为数字时该方法才可用 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:55:44 <br>
	 *
	 * @param key
	 * @param value
	 *            减指定值后的值
	 * @return
	 */
	public long decrBy(String key, long value) {
		return decrBy(SafeEncoder.encode(key), value);
	}

	/**
	 * 方法：decrBy <br>
	 * 描述：将key对应的value减去指定的值，只有value可以转为数字时该方法才可用 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:55:44 <br>
	 *
	 * @param key
	 * @param value
	 *            减指定值后的值
	 * @return
	 */
	public long decrBy(final byte[] key, final long value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.decrBy(key, value);
			}
		});
	}

	/**
	 * 方法：incrBy <br>
	 * 描述：可以作为获取唯一id的方法 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用<br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:57:13 <br>
	 *
	 * @param String
	 *            key
	 * @param long
	 *            number 要减去的值
	 * @return long 相加后的值
	 */
	public long incrBy(String key, long value) {
		return incrBy(SafeEncoder.encode(key), value);
	}

	/**
	 * 方法：incrBy <br>
	 * 描述：可以作为获取唯一id的方法 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用<br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:57:13 <br>
	 *
	 * @param String
	 *            key
	 * @param long
	 *            number 要减去的值
	 * @return long 相加后的值
	 */
	public long incrBy(final byte[] key, final long value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.incrBy(key, value);
			}
		});
	}

	/**
	 * 方法：getRange <br>
	 * 描述：对指定key对应的value进行截取 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:59:35 <br>
	 *
	 * @param String
	 *            key
	 * @param long
	 *            startOffset 开始位置(包含)
	 * @param long
	 *            endOffset 结束位置(包含)
	 * @return String 截取的值
	 */
	public String getRange(String key, long startOffset, long endOffset) {
		return SafeEncoder.encode(getRange(SafeEncoder.encode(key), startOffset, endOffset));
	}

	/**
	 * 方法：getRange <br>
	 * 描述：对指定key对应的value进行截取 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午4:59:35 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param long
	 *            startOffset 开始位置(包含)
	 * @param long
	 *            endOffset 结束位置(包含)
	 * @return String 截取的值
	 */
	public final byte[] getRange(final byte[] key, final long startOffset, final long endOffset) {
		return getRedisTemplate().execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.getRange(key, startOffset, endOffset);
			}
		});
	}

	/**
	 * 方法：getSet <br>
	 * 描述： 获取并设置指定key对应的value<br/>
	 * 如果key存在返回之前的value,否则返回null <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:00:54 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public String getSet(String key, String value) {
		return SafeEncoder.encode(getSet(SafeEncoder.encode(key), SafeEncoder.encode(value)));
	}

	/**
	 * 方法：getSet <br>
	 * 描述： 获取并设置指定key对应的value<br/>
	 * 如果key存在返回之前的value,否则返回null <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:00:54 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Object getSet(String key, Object value) {
		return SerializeUtils.deserialize(getSet(SafeEncoder.encode(key), SerializeUtils.serialize(value)));
	}

	/**
	 * 方法：getSet <br>
	 * 描述： 获取并设置指定key对应的value<br/>
	 * 如果key存在返回之前的value,否则返回null <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:00:54 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] getSet(final byte[] key, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.getSet(key, value);
			}
		});
	}

	/**
	 * 方法：mGet <br>
	 * 描述：批量获取记录,如果指定的key不存在返回List的对应位置将是null <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:02:19 <br>
	 *
	 * @param keys
	 * @return
	 */
	public List<String> mGet(String... keys) {
		return ObjectUtils.byteConvertToString(mGet(SafeEncoder.encodeMany(keys)));
	}

	/**
	 * 方法：mGet <br>
	 * 描述：批量获取记录,如果指定的key不存在返回List的对应位置将是null <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:02:19 <br>
	 *
	 * @param keys
	 * @return
	 */
	public <T> List<T> mGetObject(Class<T> clazz, String... keys) {
		return ObjectUtils.byteConvertToObject(clazz, mGet(SafeEncoder.encodeMany(keys)));
	}

	/**
	 * 方法：mGet <br>
	 * 描述：批量获取记录,如果指定的key不存在返回List的对应位置将是null <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:02:19 <br>
	 *
	 * @param keys
	 * @return
	 */
	public List<byte[]> mGet(final byte[]... keys) {
		return getRedisTemplate().execute(new RedisCallback<List<byte[]>>() {
			public List<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.mGet(keys);
			}
		});
	}

	/**
	 * 方法：mset <br>
	 * 描述： 批量存储记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:03:22 <br>
	 *
	 * @param tuple
	 * @return
	 */
	public Boolean mSet(final Map<byte[], byte[]> tuple) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.mSet(tuple);
				return true;
			}
		});
	}

	/**
	 * 方法：strLen <br>
	 * 描述： 获取key对应的值的长度 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:04:18 <br>
	 *
	 * @param String
	 *            key
	 * @return value值得长度
	 */
	public long strLen(String key) {
		return strLen(SafeEncoder.encode(key));
	}

	/**
	 * 方法：strLen <br>
	 * 描述： 获取key对应的值的长度 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:04:18 <br>
	 *
	 * @param String
	 *            key
	 * @return value值得长度
	 */
	public long strLen(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.strLen(key);
			}
		});
	}

	// *******************************************Lists*******************************************//
	/**
	 * 方法：lLen <br>
	 * 描述：List长度<br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:10:54 <br>
	 *
	 * @param String
	 *            key
	 * @return 长度
	 */
	public long lLen(String key) {
		return lLen(SafeEncoder.encode(key));
	}

	/**
	 * 方法：lLen <br>
	 * 描述：List长度 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:05:37 <br>
	 *
	 * @param key
	 * @return
	 */
	public long lLen(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.lLen(key);
			}
		});
	}

	/**
	 * 方法：lSet <br>
	 * 描述： 覆盖操作,将覆盖List中指定位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:08:06 <br>
	 *
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean lSet(String key, int index, String value) {
		return lSet(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
	}

	/**
	 * 方法：lSet <br>
	 * 描述： 覆盖操作,将覆盖List中指定位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:08:06 <br>
	 *
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean lSet(String key, int index, Object value) {
		return lSet(SafeEncoder.encode(key), index, SerializeUtils.serialize(value));
	}

	/**
	 * 方法：lSet <br>
	 * 描述：覆盖操作,将覆盖List中指定位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:06:32 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            index 位置
	 * @param byte[]
	 *            value 值
	 */
	public boolean lSet(final byte[] key, final int index, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.lSet(key, index, value);
				return true;
			}
		});
	}

	/**
	 * 方法：lInsert <br>
	 * 描述： 在value的相对位置插入记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:12:11 <br>
	 *
	 * @param key
	 * @param Position
	 *            前面插入或后面插入
	 * @param String
	 *            pivot 相对位置的内容
	 * @param String
	 *            value 插入的内容
	 * @return 记录总数
	 */
	public long lInsert(String key, Position where, String pivot, String value) {
		return lInsert(SafeEncoder.encode(key), where, SafeEncoder.encode(pivot), SafeEncoder.encode(value));
	}

	/**
	 * 方法：lInsert <br>
	 * 描述： 在value的相对位置插入记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:12:11 <br>
	 *
	 * @param key
	 * @param Position
	 *            前面插入或后面插入
	 * @param String
	 *            pivot 相对位置的内容
	 * @param String
	 *            value 插入的内容
	 * @return 记录总数
	 */
	public long lInsert(String key, Position where, String pivot, Object value) {
		return lInsert(SafeEncoder.encode(key), where, SafeEncoder.encode(pivot), SerializeUtils.serialize(value));
	}

	/**
	 * 方法：lInsert <br>
	 * 描述：TODO <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:08:54 <br>
	 *
	 * @param String
	 *            key
	 * @param Position
	 *            前面插入或后面插入
	 * @param byte[]
	 *            pivot 相对位置的内容
	 * @param byte[]
	 *            value 插入的内容
	 * @return 记录总数
	 * @return
	 */
	public long lInsert(final byte[] key, final Position where, final byte[] pivot, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.lInsert(key, where, pivot, value);
			}
		});
	}

	/**
	 * 方法：lIndex <br>
	 * 描述：获取List中指定位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:12:58 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            index 位置
	 * @return 值
	 */

	public String lIndex(String key, int index) {
		return SafeEncoder.encode(lIndex(SafeEncoder.encode(key), index));
	}

	/**
	 * 方法：lIndexObj <br>
	 * 描述：获取List中指定位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:12:58 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            index 位置
	 * @return 值
	 */

	public Object lIndexObj(String key, int index) {
		return SerializeUtils.deserialize(lIndex(SafeEncoder.encode(key), index));
	}

	/**
	 * 方法：lIndex <br>
	 * 描述：获取List中指定位置的值 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:12:58 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            index 位置
	 * @return 值
	 */
	public byte[] lIndex(final byte[] key, final int index) {
		return getRedisTemplate().execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.lIndex(key, index);
			}
		});
	}

	/**
	 * 方法：lPop <br>
	 * 描述：将List中的第一条记录移出List <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:14:50 <br>
	 *
	 * @param String
	 *            key
	 * @return 移出的记录
	 */
	public String lPop(String key) {
		return SafeEncoder.encode(lPop(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：lPop <br>
	 * 描述：将List中的第一条记录移出List <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:14:50 <br>
	 *
	 * @param String
	 *            key
	 * @return 移出的记录
	 */
	public Object lPopObj(String key) {
		return SerializeUtils.deserialize(lPop(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：lPop <br>
	 * 描述： 将List中的第一条记录移出List <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:14:30 <br>
	 *
	 * @param byte[]
	 *            key
	 * @return 移出的记录
	 */
	public byte[] lPop(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.lPop(key);
			}
		});
	}

	/**
	 * 方法：lPop <br>
	 * 描述：将List中最后第一条记录移出List <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:14:50 <br>
	 *
	 * @param String
	 *            key
	 * @return 移出的记录
	 */
	public String rPop(String key) {
		return SafeEncoder.encode(rPop(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：lPop <br>
	 * 描述：将List中最后第一条记录移出List <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:14:50 <br>
	 *
	 * @param String
	 *            key
	 * @return 移出的记录
	 */
	public Object rPopObj(String key) {
		return SerializeUtils.deserialize(rPop(SafeEncoder.encode(key)));
	}

	/**
	 * 方法：lPop <br>
	 * 描述： 将List中最后第一条记录移出List <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:14:30 <br>
	 *
	 * @param byte[]
	 *            key
	 * @return 移出的记录
	 */
	public byte[] rPop(final byte[] key) {
		return getRedisTemplate().execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.rPop(key);
			}
		});
	}

	/**
	 * 方法：lPush <br>
	 * 描述：向List中追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:16:41 <br>
	 *
	 * @param key
	 * @param value
	 * @return 记录总数
	 */
	public long lPush(String key, String... value) {
		return lPush(SafeEncoder.encode(key), SafeEncoder.encodeMany(value));
	}

	/**
	 * 方法：lPush <br>
	 * 描述：向List中追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:16:41 <br>
	 *
	 * @param key
	 * @param value
	 * @return 记录总数
	 */
	@SuppressWarnings("unchecked")
	public <T> long lPush(Class<T> clazz, String key, T... value) {
		return lPush(SafeEncoder.encode(key),
				ObjectUtils.objectConvertToByte(Arrays.asList(value)).toArray(new byte[0][]));
	}

	/**
	 * 方法：lPush <br>
	 * 描述：向List中追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:16:41 <br>
	 *
	 * @param key
	 * @param value
	 * @return 记录总数
	 */
	public long lPush(final byte[] key, final byte[]... value) {
		return lPush(key, -1, value);
	}

	/**
	 * 方法：lPush <br>
	 * 描述：向List中追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:16:41 <br>
	 *
	 * @param key
	 * @param seconds
	 * @param value
	 * @return 记录总数
	 */
	public long lPush(String key, final long seconds, String... value) {
		return lPush(SafeEncoder.encode(key), seconds, SafeEncoder.encodeMany(value));
	}

	/**
	 * 方法：lPush <br>
	 * 描述：向List中追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:16:41 <br>
	 *
	 * @param clazz
	 * @param key
	 * @param seconds
	 * @param value
	 * @return 记录总数
	 */
	@SuppressWarnings("unchecked")
	public <T> long lPush(Class<T> clazz, String key, final long seconds, T... value) {
		return lPush(SafeEncoder.encode(key), seconds,
				ObjectUtils.objectConvertToByte(Arrays.asList(value)).toArray(new byte[0][]));
	}

	/**
	 * 方法：lPush <br>
	 * 描述：向List中追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:16:41 <br>
	 *
	 * @param key
	 * @param seconds
	 * @param value
	 * @return 记录总数
	 */
	public long lPush(final byte[] key, final long seconds, final byte[]... value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				long result = connection.lPush(key, value);
				if (seconds > 0) {
					connection.expire(key, seconds);
				}
				return result;
			}
		});
	}

	/**
	 * 方法：rPush <br>
	 * 描述：向List头部追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:17:54 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public long rPush(String key, String... value) {
		return rPush(SafeEncoder.encode(key), SafeEncoder.encodeMany(value));

	}

	/**
	 * 方法：rPush <br>
	 * 描述：向List头部追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:17:54 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public <T> long rPush(Class<T> clazz, String key, Object... value) {
		return lPush(SafeEncoder.encode(key),
				ObjectUtils.objectConvertToByte(Arrays.asList(value)).toArray(new byte[0][]));
	}

	/**
	 * 方法：rPush <br>
	 * 描述：向List头部追加记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:17:54 <br>
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public long rPush(final byte[] key, final byte[]... value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.rPush(key, value);
			}
		});
	}

	/**
	 * 方法：lRange <br>
	 * 描述：获取指定范围的记录，可以做为分页使用 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:19:47 <br>
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<byte[]> lRange(String key, int start, int end) {
		return lRange(SafeEncoder.encode(key), start, end);
	}

	/**
	 * 方法：lRange <br>
	 * 描述：获取指定范围的记录，可以做为分页使用 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:19:47 <br>
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public <T> List<T> lRangeObj(Class<T> clazz, String key, int start, int end) {
		return ObjectUtils.byteConvertToObject(clazz, lRange(SafeEncoder.encode(key), start, end));
	}

	/**
	 * 方法：lRange <br>
	 * 描述：获取指定范围的记录，可以做为分页使用 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:19:47 <br>
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<byte[]> lRange(final byte[] key, final int start, final int end) {
		return getRedisTemplate().execute(new RedisCallback<List<byte[]>>() {
			public List<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.lRange(key, start, end);
			}
		});
	}

	/**
	 * 方法：lRem <br>
	 * 描述：删除List中c条记录，被删除的记录值为value <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:21:10 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param byte[]
	 *            value 要匹配的值
	 * @return 删除后的List中的记录数
	 */
	public long lrem(String key, int c, String value) {
		return lRem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
	}

	/**
	 * 方法：lRem <br>
	 * 描述：删除List中c条记录，被删除的记录值为value <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:21:10 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param byte[]
	 *            value 要匹配的值
	 * @return 删除后的List中的记录数
	 */
	public long lRem(String key, int c, Object value) {
		return lRem(SafeEncoder.encode(key), c, SerializeUtils.serialize(value));
	}

	/**
	 * 方法：lRem <br>
	 * 描述：删除List中c条记录，被删除的记录值为value <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:21:10 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param byte[]
	 *            value 要匹配的值
	 * @return 删除后的List中的记录数
	 */
	public long lRem(final byte[] key, final int c, final byte[] value) {
		return getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.lRem(key, c, value);
			}
		});
	}

	/**
	 * 方法：ltrim <br>
	 * 描述：算是删除吧，只保留start与end之间的记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:22:49 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            start 记录的开始位置(0表示第一条记录)
	 * @param int
	 *            end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
	 * @return 执行状态码
	 */
	public boolean lTrim(final byte[] key, final int start, final int end) {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.lTrim(key, start, end);
				return true;
			}
		});
	}

	/**
	 * 方法：ltrim <br>
	 * 描述：算是删除吧，只保留start与end之间的记录 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午5:22:49 <br>
	 *
	 * @param byte[]
	 *            key
	 * @param int
	 *            start 记录的开始位置(0表示第一条记录)
	 * @param int
	 *            end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
	 * @return 执行状态码
	 */
	public boolean lTrim(String key, int start, int end) {
		return lTrim(SafeEncoder.encode(key), start, end);
	}

	/**
	 * 方法：getRedisTemplate <br>
	 * 描述：实现相应的redis数据源template <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月27日 下午2:30:43 <br>
	 *
	 * @return
	 */
	protected abstract RedisTemplate<Serializable, Serializable> getRedisTemplate();
}
