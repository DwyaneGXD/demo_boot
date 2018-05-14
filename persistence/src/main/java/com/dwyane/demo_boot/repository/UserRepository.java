package com.dwyane.demo_boot.repository;

import com.dwyane.demo_boot.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    /**
     * 针对高并发的情况
     * 采用内存型的存储方式 -> Map
     * 为了确保一致性，采用final的方式来表达
     */
    private final ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();

    /**
     * 自增长的id生成器
     */
    private final static AtomicInteger idGenerator = new AtomicInteger();

    /**
     * 保存用户对象
     * @param user 用户对象
     * @return 如果保存成功，返回true,
     *         否则返回false
     */
    public boolean save(User user){

        //id从1开始
        Integer id = idGenerator.incrementAndGet();
        //设置id
        user.setId(id);
        //map的put方法是有返回值的，如果返回null，说明保存成功了
        return repository.put(id, user) == null;

    }

    /**
     * 返回所有用户列表
     */
    public Collection<User> findAll(){
        return repository.values();
    }
}
