package com.demo;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.demo.util.ClientFactory;

import io.etcd.jetcd.Client;
import io.etcd.jetcd.Cluster;
import io.etcd.jetcd.cluster.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cluster_Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Client client = ClientFactory.getClient();

        Cluster clusterClient = client.getClusterClient();

        List<Member> members = clusterClient.listMember().get().getMembers();
        for (Member member : members) {
            long id = member.getId();
            String name = member.getName();
            List<URI> clientURIs = member.getClientURIs();
            List<URI> peerURIs = member.getPeerURIs();
            log.info(">\nid:{}\nname:{}\nclientURIs:{}\npeerURIs:{}\n\n", id, name, clientURIs, peerURIs);
        }

        // clusterClient.addMember()
        // clusterClient.updateMember()
        // clusterClient.removeMember()

        clusterClient.close();
    }
}
