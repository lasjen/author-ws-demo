#!/bin/bash
rm -rf target

#sh dbgen.sh
#sh flydb.sh
#sh codegen.sh
mvn package

cp target/pay-ws-demo-1.0-SNAPSHOT.war ~/docker-vms/ora12c-db-02/deploy/.
