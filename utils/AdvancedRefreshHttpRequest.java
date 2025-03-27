import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
//戴建晖重试策略
public class RefreshHttpRequest {

    public static void main(String[] args) {
        String url = "http://example.com/api/endpoint";
        int maxRetries = 5;
        int retryInterval = 1000; // 初始重试间隔，单位为毫秒

        int retryCount = 0;
        boolean requestSucceeded = false;

        while (!requestSucceeded && retryCount < maxRetries) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    // 请求成功
                    requestSucceeded = true;
                    // 处理成功的响应
                } else {
                    // 请求失败
                    retryCount++;
                    System.out.println("Request failed with response code: " + responseCode + ". Retrying in " + retryInterval + "ms...");
                    Thread.sleep(retryInterval);
                    // 采用指数退避策略增加重试间隔
                    retryInterval *= 2;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!requestSucceeded) {
            System.out.println("Request failed after " + maxRetries + " retries.");
            // 执行重试之后请求仍然失败，执行相应的处理逻辑
        }
    }
}



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdvancedRefreshHttpRequest {

    public static void main(String[] args) {
        String url = "http://example.com/api/endpoint";
        int maxRetries = 5;
        int retryInterval = 1000; // 初始重试间隔，单位为毫秒
        int maxWaitTime = 60000; // 最大等待时间，单位为毫秒
        int retryCount = 0;
        boolean requestSucceeded = false;

        long startTime = System.currentTimeMillis();

        while (!requestSucceeded && (System.currentTimeMillis() - startTime) < maxWaitTime) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    // 请求成功
                    requestSucceeded = true;
                    // 处理成功的响应
                } else {
                    // 请求失败
                    if (retryCount < maxRetries) {
                        retryCount++;
                        System.out.println("Request failed with response code: " + responseCode
                                + ". Retrying in " + retryInterval + "ms...");
                        Thread.sleep(retryInterval);
                        // 使用指数退避算法增加重试间隔
                        retryInterval *= 2;
                    } else {
                        // 达到最大重试次数，中断重试
                        break;
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!requestSucceeded) {
            System.out.println("Request failed after " + maxRetries + " retries or reaching maximum wait time.");
            // 执行重试之后请求仍然失败，执行相应的处理逻辑
        }
    }
}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncHttpRequestExecutor {
    private final ExecutorService executorService;
    private final BlockingQueue<Runnable> taskQueue;

    public AsyncHttpRequestExecutor(int maxThreads, int maxQueueSize) {
        this.executorService = Executors.newFixedThreadPool(maxThreads);
        this.taskQueue = new LinkedBlockingQueue<>(maxQueueSize);

        startTaskConsumer();
    }

    public void executeAsyncRequest(HttpRequestTask task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void startTaskConsumer() {
        executorService.execute(() -> {
            while (true) {
                try {
                    Runnable task = taskQueue.take();
                    executorService.execute(task);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
