import {Etcd3} from 'etcd3';


const client = new Etcd3({
    hosts: ["localhost:3479"],
    // auth: {
    //     username: "",
    //     password: ""
    // }
});
