
import org.apache.ibatis.annotations.Param
import cn.apex.stepapp.po.User


public interface UserDao {

	
	public User findUser(@Param("id") Long id);
	
	
	public Integer addUser(@Param("username") String username,@Param("password") String password);
	
	
}