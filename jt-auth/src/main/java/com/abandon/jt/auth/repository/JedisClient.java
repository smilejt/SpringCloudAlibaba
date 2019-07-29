package com.abandon.jt.auth.repository;

/**
 * @createData: 2019-06-24 17:17
 * @author: LongJunTao
 * @Description:
 */
public interface JedisClient {
    /**
     * get
     * @param key key
     * @return String
     */
    String get(String key);

    /**
     * set
     * @param key key
     * @param value value
     * @return String
     */
    String set(String key, String value);

    /**
     * hGet
     * @param hKey hKey
     * @param key key
     * @return String
     */
    String hGet(String hKey, String key);

    /**
     * hSet
     * @param hKey hKey
     * @param key key
     * @param value value
     * @return long
     */
    long hSet(String hKey, String key, String value);

    /**
     * del
     * @param key key
     * @return long
     */
    long del(String key);

    /**
     * hDel
     * @param hKey hKey
     * @param key key
     * @return long
     */
    long hDel(String hKey, String key);

    /**
     * expire
     * @param key key
     * @param second second
     * @return long
     */
    long expire(String key, int second);
}
