import groovy.transform.Field

@Field String lib

node('master') {
    def cdpwd = "${pwd()}@script"
    cdpwd = cdpwd.substring(cdpwd.lastIndexOf('/'))
    lib = load "..${cdpwd}/lib.groovy"
}

lib.ZKIMG = ['ZKIP', 'zookeeper:3.3.6', 1, '']
lib.KAFKAIMG = ['KAFKAIP', 'kafka:0.8.1.1', 1, "--env \"ZK_CONNECT=${-> lib.IPS['ZKIP']}:2181\" "]

lib.MODULE = 'test'
lib.MODULEOPTS = "--env MODULE=${lib.MODULE} " +
        "--env \"${lib.MODULE.toUpperCase()}_ZK_IP=${-> lib.IPS['ZKIP']}\" "

lib.ATSERVICES = [lib.ZKIMG,
                  lib.KAFKAIMG,
                  ['MODULEIP', 'st-engine:0.7.0-SNAPSHOT', "${-> lib.MODULEOPTS}"]
]

node ('master') {
    lib.startServices(lib.ATSERVICES)
}
