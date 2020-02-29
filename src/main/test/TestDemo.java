import com.itdr.common.ServerResponse;
import com.itdr.mapper.UsersMapper;
import com.itdr.pojo.Users;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


public class TestDemo {

    @Test
    public void test(){
        for (int i = 0; i < 5; i++) {
            String s = UUID.randomUUID().toString();
            System.out.println(s);
        }
    }


    @Test
    public void test2(){

    }
}
