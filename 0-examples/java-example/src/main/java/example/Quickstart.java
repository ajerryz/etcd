package example;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.options.PutOption;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author jerry zhang
 * @since 2026/5/19 21:55
 */
public class Quickstart {


    public static final ByteSequence KEY = ByteSequence.from("/v1/name".getBytes());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Client client = Client.builder()
                .endpoints(
                        "http://localhost:2379"
                )
                .build();

        KV kvClient = client.getKVClient();


        CompletableFuture<PutResponse> putRespFuture = kvClient.put(KEY, ByteSequence.from("zs".getBytes()));
        PutResponse putResp = putRespFuture.get();

        if (putResp.hasPrevKv()) {
            KeyValue prevKv = putResp.getPrevKv();
            String prevVal = prevKv.getValue().toString();
            System.out.println("prevVal:" + prevVal);
        }

        for (KeyValue kv : kvClient.get(KEY).get().getKvs()) {
            System.out.println(kv.getValue().toString());
        }


        putRespFuture = kvClient.put(
                ByteSequence.from("/v1/name".getBytes()),
                ByteSequence.from("lisi".getBytes()),
                PutOption.newBuilder().withPrevKV().build()
        );
        putResp = putRespFuture.get();
        if (putResp.hasPrevKv()) {
            KeyValue prevKv = putResp.getPrevKv();
            String prevVal = prevKv.getValue().toString();
            System.out.println("prevVal:" + prevVal);
        }

        //kvClient.delete(KEY).get();


        client.close();
    }
}
