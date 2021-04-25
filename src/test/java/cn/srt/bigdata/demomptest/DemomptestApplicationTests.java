package cn.srt.bigdata.demomptest;

import cn.srt.bigdata.demomptest.entity.User;
import cn.srt.bigdata.demomptest.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

@SpringBootTest
class DemomptestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询全部记录：将User表中的所有记录都查出来
     */
    @Test
    public void findAll() {

        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    /**
     * 添加一条记录
     */
    @Test
    public void testAdd() {

        //创建一个User对象
        User user = new User();
        //设置这个对象的属性
        user.setName("李四");
        user.setAge(20);
        user.setEmail("1234@qq.com");
        int insert = userMapper.insert(user);
        //返回值表示影响的行数
        System.out.println(insert);
    }

    /**
     * 修改
     */
    @Test
    public void testUpate() {

        User user = new User();
        //设置要改的用户ID
        user.setId(1386000288677416961L);
        //设置新修改的名字
        user.setName("lucyhh");
        int count = userMapper.updateById(user);
        System.out.println(count);
    }

    /**
     * 乐观锁测试过程
     */
    @Test
    public void testOptimisticLock() {
        //1.查询操作
        User user = userMapper.selectById(1386200447352938498L);
        user.setName("张三");

        //2.更新操作
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 多个ID的查询
     */
    @Test
    public void testSelect1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    /**
     * 按照条件查询数据库中的记录
     */
    @Test
    public void testSelect2() {
        Map<String, Object> map = new HashMap<>();
        map.put("name","Billie");
        map.put("age",24);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    /**
     * 测试分页
     */
    @Test
    public void testSelectPage() {

        //1.创建分页对象
        Page<User> page = new Page(2,3);//当前第一页，每页三行
        Page<User> userPage = userMapper.selectPage(page, null);
        //总页数
        long pages = userPage.getPages();
        //当前页
        long current = userPage.getCurrent();
        //当前页数据的集合
        List<User> records = userPage.getRecords();
        //当前表的总记录数
        long total = userPage.getTotal();
        //当前页是否有上一页
        boolean b = userPage.hasPrevious();
        //当前页是否有下一页
        boolean b1 = userPage.hasNext();

        System.out.println("pages:" + pages);
        System.out.println("current:" + current);
        System.out.println("records:" + records);
        System.out.println("total:" + total);
        System.out.println("haspre:" + b);
        System.out.println("hasnext:" + b1);
    }

    /**
     * 根据ID删除
     */
    @Test
    public void testDeleteById() {

        int delete = userMapper.deleteById(1386000288677416961L);

        System.out.println(delete);
    }

    /**
     * 批量删除
     */
    @Test
    public void testDeleteBatch() {


        int i = userMapper.deleteBatchIds(Arrays.asList(2,3));

        System.out.println(i);
    }

    /**
     * 根据条件删除
     */
    @Test
    public void testDeleteCondition() {

        Map<String, Object> map = new HashMap<>();
        map.put("name","张三");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }
}