package com.digital.harrier.common.helper;

import redis.clients.jedis.*;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisHelper implements JedisCommands{

    private JedisPool jedisPool;

    //jedis集群
    private JedisCluster jedisCluster;

    // 是否为集群,默认不是集群
    private boolean isCluster = false;

    public JedisHelper(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public JedisHelper(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
        isCluster = true;
    }

    @Override
    public String get(final String key) {
        if(isCluster) {
            return jedisCluster.get(key);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public String set(String s, String s1) {
        if(isCluster) {
            return jedisCluster.set(s,s1);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(s,s1);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public String set(String s, String s1, SetParams setParams) {
        if(isCluster) {
            return jedisCluster.set(s,s1,setParams);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(s,s1,setParams);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public Boolean exists(String s) {
        if(isCluster) {
            return jedisCluster.exists(s);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(s);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public Long persist(String s) {
        return null;
    }

    @Override
    public String type(String s) {
        return null;
    }

    @Override
    public byte[] dump(String s) {
        return new byte[0];
    }

    @Override
    public String restore(String s, int i, byte[] bytes) {
        return null;
    }

    @Override
    public String restoreReplace(String s, int i, byte[] bytes) {
        return null;
    }

    @Override
    public Long expire(String s, int i) {
        return null;
    }

    @Override
    public Long pexpire(String s, long l) {
        return null;
    }

    @Override
    public Long expireAt(String s, long l) {
        return null;
    }

    @Override
    public Long pexpireAt(String s, long l) {
        return null;
    }

    @Override
    public Long ttl(String s) {
        return null;
    }

    @Override
    public Long pttl(String s) {
        return null;
    }

    @Override
    public Long touch(String s) {
        return null;
    }

    @Override
    public Boolean setbit(String s, long l, boolean b) {
        return null;
    }

    @Override
    public Boolean setbit(String s, long l, String s1) {
        return null;
    }

    @Override
    public Boolean getbit(String s, long l) {
        return null;
    }

    @Override
    public Long setrange(String s, long l, String s1) {
        return null;
    }

    @Override
    public String getrange(String s, long l, long l1) {
        return null;
    }

    @Override
    public String getSet(String s, String s1) {
        return null;
    }

    @Override
    public Long setnx(String s, String s1) {
        if(isCluster) {
            return jedisCluster.setnx(s,s1);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setnx(s,s1);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public String setex(String s, int i, String s1) {
        if(isCluster) {
            return jedisCluster.setex(s,i,s1);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(s,i,s1);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public String psetex(String s, long l, String s1) {
        return null;
    }

    @Override
    public Long decrBy(String s, long l) {
        return null;
    }

    @Override
    public Long decr(String s) {
        if(isCluster) {
            return jedisCluster.decr(s);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(s);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public Long incrBy(String s, long l) {
        return null;
    }

    @Override
    public Double incrByFloat(String s, double v) {
        return null;
    }

    @Override
    public Long incr(String s) {
        if(isCluster) {
            return jedisCluster.incr(s);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(s);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public Long append(String s, String s1) {
        return null;
    }

    @Override
    public String substr(String s, int i, int i1) {
        return null;
    }

    @Override
    public Long hset(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Long hset(String s, Map<String, String> map) {
        return null;
    }

    @Override
    public String hget(String s, String s1) {
        return null;
    }

    @Override
    public Long hsetnx(String s, String s1, String s2) {
        return null;
    }

    @Override
    public String hmset(String s, Map<String, String> map) {
        return null;
    }

    @Override
    public List<String> hmget(String s, String... strings) {
        return null;
    }

    @Override
    public Long hincrBy(String s, String s1, long l) {
        return null;
    }

    @Override
    public Double hincrByFloat(String s, String s1, double v) {
        return null;
    }

    @Override
    public Boolean hexists(String s, String s1) {
        return null;
    }

    @Override
    public Long hdel(String s, String... strings) {
        return null;
    }

    @Override
    public Long hlen(String s) {
        return null;
    }

    @Override
    public Set<String> hkeys(String s) {
        return null;
    }

    @Override
    public List<String> hvals(String s) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String s) {
        return null;
    }

    @Override
    public Long rpush(String s, String... strings) {
        if(isCluster) {
            return jedisCluster.rpush(s,strings);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.rpush(s,strings);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public Long lpush(String s, String... strings) {
        if(isCluster) {
            return jedisCluster.lpush(s,strings);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpush(s,strings);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public Long llen(String s) {
        if(isCluster) {
            return jedisCluster.llen(s);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.llen(s);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public List<String> lrange(String s, long l, long l1) {
        return null;
    }

    @Override
    public String ltrim(String s, long l, long l1) {
        return null;
    }

    @Override
    public String lindex(String s, long l) {
        return null;
    }

    @Override
    public String lset(String s, long l, String s1) {
        return null;
    }

    @Override
    public Long lrem(String s, long l, String s1) {
        return null;
    }

    @Override
    public String lpop(String s) {
        if(isCluster) {
            return jedisCluster.lpop(s);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpop(s);
        } finally {
            if(jedis != null) jedis.close();
        }
    }

    @Override
    public String rpop(String s) {
        return null;
    }

    @Override
    public Long sadd(String s, String... strings) {
        return null;
    }

    @Override
    public Set<String> smembers(String s) {
        return null;
    }

    @Override
    public Long srem(String s, String... strings) {
        return null;
    }

    @Override
    public String spop(String s) {
        return null;
    }

    @Override
    public Set<String> spop(String s, long l) {
        return null;
    }

    @Override
    public Long scard(String s) {
        return null;
    }

    @Override
    public Boolean sismember(String s, String s1) {
        return null;
    }

    @Override
    public String srandmember(String s) {
        return null;
    }

    @Override
    public List<String> srandmember(String s, int i) {
        return null;
    }

    @Override
    public Long strlen(String s) {
        return null;
    }

    @Override
    public Long zadd(String s, double v, String s1) {
        return null;
    }

    @Override
    public Long zadd(String s, double v, String s1, ZAddParams zAddParams) {
        return null;
    }

    @Override
    public Long zadd(String s, Map<String, Double> map) {
        return null;
    }

    @Override
    public Long zadd(String s, Map<String, Double> map, ZAddParams zAddParams) {
        return null;
    }

    @Override
    public Set<String> zrange(String s, long l, long l1) {
        return null;
    }

    @Override
    public Long zrem(String s, String... strings) {
        return null;
    }

    @Override
    public Double zincrby(String s, double v, String s1) {
        return null;
    }

    @Override
    public Double zincrby(String s, double v, String s1, ZIncrByParams zIncrByParams) {
        return null;
    }

    @Override
    public Long zrank(String s, String s1) {
        return null;
    }

    @Override
    public Long zrevrank(String s, String s1) {
        return null;
    }

    @Override
    public Set<String> zrevrange(String s, long l, long l1) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String s, long l, long l1) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String s, long l, long l1) {
        return null;
    }

    @Override
    public Long zcard(String s) {
        return null;
    }

    @Override
    public Double zscore(String s, String s1) {
        return null;
    }

    @Override
    public Tuple zpopmax(String s) {
        return null;
    }

    @Override
    public Set<Tuple> zpopmax(String s, int i) {
        return null;
    }

    @Override
    public Tuple zpopmin(String s) {
        return null;
    }

    @Override
    public Set<Tuple> zpopmin(String s, int i) {
        return null;
    }

    @Override
    public List<String> sort(String s) {
        return null;
    }

    @Override
    public List<String> sort(String s, SortingParams sortingParams) {
        return null;
    }

    @Override
    public Long zcount(String s, double v, double v1) {
        return null;
    }

    @Override
    public Long zcount(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String s, double v, double v1) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String s, double v, double v1) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String s, double v, double v1, int i, int i1) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String s, double v, double v1, int i, int i1) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String s, double v, double v1) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v1) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String s, double v, double v1, int i, int i1) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v1, int i, int i1) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    @Override
    public Long zremrangeByRank(String s, long l, long l1) {
        return null;
    }

    @Override
    public Long zremrangeByScore(String s, double v, double v1) {
        return null;
    }

    @Override
    public Long zremrangeByScore(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Long zlexcount(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<String> zrangeByLex(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<String> zrangeByLex(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByLex(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByLex(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    @Override
    public Long zremrangeByLex(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Long linsert(String s, ListPosition listPosition, String s1, String s2) {
        return null;
    }

    @Override
    public Long lpushx(String s, String... strings) {
        return null;
    }

    @Override
    public Long rpushx(String s, String... strings) {
        return null;
    }

    @Override
    public List<String> blpop(int i, String s) {
        return null;
    }

    @Override
    public List<String> brpop(int i, String s) {
        return null;
    }

    @Override
    public Long del(String s) {
        return null;
    }

    @Override
    public Long unlink(String s) {
        return null;
    }

    @Override
    public String echo(String s) {
        return null;
    }

    @Override
    public Long move(String s, int i) {
        return null;
    }

    @Override
    public Long bitcount(String s) {
        return null;
    }

    @Override
    public Long bitcount(String s, long l, long l1) {
        return null;
    }

    @Override
    public Long bitpos(String s, boolean b) {
        return null;
    }

    @Override
    public Long bitpos(String s, boolean b, BitPosParams bitPosParams) {
        return null;
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String s, String s1) {
        return null;
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String s, String s1, ScanParams scanParams) {
        return null;
    }

    @Override
    public ScanResult<String> sscan(String s, String s1) {
        return null;
    }

    @Override
    public ScanResult<Tuple> zscan(String s, String s1) {
        return null;
    }

    @Override
    public ScanResult<Tuple> zscan(String s, String s1, ScanParams scanParams) {
        return null;
    }

    @Override
    public ScanResult<String> sscan(String s, String s1, ScanParams scanParams) {
        return null;
    }

    @Override
    public Long pfadd(String s, String... strings) {
        return null;
    }

    @Override
    public long pfcount(String s) {
        return 0;
    }

    @Override
    public Long geoadd(String s, double v, double v1, String s1) {
        return null;
    }

    @Override
    public Long geoadd(String s, Map<String, GeoCoordinate> map) {
        return null;
    }

    @Override
    public Double geodist(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Double geodist(String s, String s1, String s2, GeoUnit geoUnit) {
        return null;
    }

    @Override
    public List<String> geohash(String s, String... strings) {
        return null;
    }

    @Override
    public List<GeoCoordinate> geopos(String s, String... strings) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String s, double v, double v1, double v2, GeoUnit geoUnit) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String s, double v, double v1, double v2, GeoUnit geoUnit) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String s, double v, double v1, double v2, GeoUnit geoUnit, GeoRadiusParam geoRadiusParam) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String s, double v, double v1, double v2, GeoUnit geoUnit, GeoRadiusParam geoRadiusParam) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String s, String s1, double v, GeoUnit geoUnit) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String s, String s1, double v, GeoUnit geoUnit) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String s, String s1, double v, GeoUnit geoUnit, GeoRadiusParam geoRadiusParam) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String s, String s1, double v, GeoUnit geoUnit, GeoRadiusParam geoRadiusParam) {
        return null;
    }

    @Override
    public List<Long> bitfield(String s, String... strings) {
        return null;
    }

    @Override
    public Long hstrlen(String s, String s1) {
        return null;
    }

    @Override
    public StreamEntryID xadd(String s, StreamEntryID streamEntryID, Map<String, String> map) {
        return null;
    }

    @Override
    public StreamEntryID xadd(String s, StreamEntryID streamEntryID, Map<String, String> map, long l, boolean b) {
        return null;
    }

    @Override
    public Long xlen(String s) {
        return null;
    }

    @Override
    public List<StreamEntry> xrange(String s, StreamEntryID streamEntryID, StreamEntryID streamEntryID1, int i) {
        return null;
    }

    @Override
    public List<StreamEntry> xrevrange(String s, StreamEntryID streamEntryID, StreamEntryID streamEntryID1, int i) {
        return null;
    }

    @Override
    public long xack(String s, String s1, StreamEntryID... streamEntryIDS) {
        return 0;
    }

    @Override
    public String xgroupCreate(String s, String s1, StreamEntryID streamEntryID, boolean b) {
        return null;
    }

    @Override
    public String xgroupSetID(String s, String s1, StreamEntryID streamEntryID) {
        return null;
    }

    @Override
    public long xgroupDestroy(String s, String s1) {
        return 0;
    }

    @Override
    public String xgroupDelConsumer(String s, String s1, String s2) {
        return null;
    }

    @Override
    public List<StreamPendingEntry> xpending(String s, String s1, StreamEntryID streamEntryID, StreamEntryID streamEntryID1, int i, String s2) {
        return null;
    }

    @Override
    public long xdel(String s, StreamEntryID... streamEntryIDS) {
        return 0;
    }

    @Override
    public long xtrim(String s, long l, boolean b) {
        return 0;
    }

    @Override
    public List<StreamEntry> xclaim(String s, String s1, String s2, long l, long l1, int i, boolean b, StreamEntryID... streamEntryIDS) {
        return null;
    }
}
