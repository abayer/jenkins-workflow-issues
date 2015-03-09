import groovy.transform.Field

@Field String MODULE

@Field Map<String, String> IPS = ['ZKIP'       : '',
                                  'KAFKAIP'    : '',
                                  'MODULEIP'   : '']

@Field List<String> ZKIMG
@Field List<String> KAFKAIMG

@Field String MODULEOPTS
@Field List<List<String>> ATSERVICES
@Field String ATPARAMS

def startServices(SERVICES) {
    for (int i = 0; i < SERVICES.size(); i++) {
        IMG = SERVICES[i][1]
        ADDITIONALPARAM = SERVICES[i][3]        
        sh "echo ${MODULE} ${IMG} '${ADDITIONALPARAM}' && echo ${i} > IP"

        IPS[SERVICES[i][0]] = readFile('IP').trim()
    }
}

return this;
