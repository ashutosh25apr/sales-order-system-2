#!/bin/bash
set -e

echo 'Building Backend Services...'

echo 'Building User-Service...'
export JAVA_HOME=$JAVA_21_HOME
export PATH=$JAVA_HOME/bin:$PATH
cd user-service \
    && ./mvnw clean install \
    && cd - || exit

echo 'Building Customer-Service...'
export JAVA_HOME=$JAVA_21_HOME
export PATH=$JAVA_HOME/bin:$PATH
cd customer-service \
    && ./mvnw clean install \
    && cd -

echo 'Building Order-Service...'
export JAVA_HOME=$JAVA_8_HOME
export PATH=$JAVA_HOME/bin:$PATH
cd order-service \
    && ./gradlew clean build \
    && cd -

echo 'Building Product-Service...'
export JAVA_HOME=$JAVA_21_HOME
export PATH=$JAVA_HOME/bin:$PATH
cd product-service \
    && ./mvnw clean install \
    && cd -

echo 'Building Sales Order System app...'
cd sales-order-system \
    && npm run build \
    && cd -