import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThread {
    public static void main(String[] args) throws SQLException {
        List<User> allUsers = test.getAllUsers();
        long startCallAPI = System.currentTimeMillis();

        // 3. 遍历项目列表, 使用多线程的方式. 线程池
        //    ExecutorService 有两种提交任务的操作.
        //    a) execute: 不关注任务的结果
        //    b) submit: 关注任务的结果
        //    此处使用 submit 最主要的目的是为了能够知道当前线程池中所有任务啥时候能全完成
        //    等到全都完成之后再保存数据
        List<Future<?>> taskResults = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (User user : allUsers) {
            Future<?> taskResult = executorService.submit(new CrawlerThread(user));
            taskResults.add(taskResult);
        }
        // 等待所有线程池中的任务执行结束, 再进行下一步操作
        for (Future<?> taskResult : taskResults) {
            // 调用 get 方法就会阻塞, 阻塞到该任务执行完毕, get 才会返回
            try {
                taskResult.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 代码执行到这一步, 说明所有任务都执行完毕了, 结束线程池
        // 这个操作就是卸磨杀驴, 避免出现线程在这干吃饭不干活. 一定要及时裁员
        executorService.shutdown();

        long finishCallAPI = System.currentTimeMillis();
        // 这个时间是 22s
        System.out.println("用Restful API 爬完所有用户数据的时间: " + (finishCallAPI - startCallAPI) + " ms");

        // 4. 保存到数据库
        GoodLoader.ImportUsersInfo(allUsers);
    }
}
