package example.config;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.options.WatchOption;
import io.etcd.jetcd.watch.WatchEvent;
import io.etcd.jetcd.watch.WatchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

/**
 * @author jerry zhang
 * @since 2026/5/19 22:59
 */
@Slf4j
@Configuration
public class EtcdConfig {

    public static final ByteSequence KEY_NAME_PREFIX = ByteSequence.from("/v1/name".getBytes());


    @Bean
    public Client etcdClient() {
        Client client = Client.builder()
                .endpoints(
                        "http://localhost:2379"
                )
                .executorService(Executors.newFixedThreadPool(10))
                .build();

        Watch.Watcher watch = client.getWatchClient().watch(
                KEY_NAME_PREFIX,
                WatchOption.newBuilder().isPrefix(true).build(),
                new Watch.Listener() {
                    @Override
                    public void onNext(WatchResponse watchResponse) {
                        for (WatchEvent event : watchResponse.getEvents()) {
                            WatchEvent.EventType eventType = event.getEventType();
                            KeyValue keyValue = event.getKeyValue();
                            String key = keyValue.getKey().toString();
                            String val = keyValue.getValue().toString();
                            switch (eventType) {
                                case PUT:
                                    log.info("PUT key:{} val:{}", key, val);
                                    break;
                                case DELETE:
                                    log.info("DELETE key:{} val:{}", key, val);
                                    break;
                                case UNRECOGNIZED:
                                    log.info("UNRECOGNIZED key:{} val:{}", key, val);
                                default:
                                    System.out.println("DEFAULT");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        log.error("onError", throwable);
                    }

                    @Override
                    public void onCompleted() {
                        log.info("onCompleted");
                    }
                }
        );
        watch.requestProgress();
        return client;
    }
}
