#!/bin/sh
mvn clean package payara-micro:bundle
mvn payara-micro:start