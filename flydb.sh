#!/bin/bash

mvn flyway:baseline
mvn flyway:migrate
