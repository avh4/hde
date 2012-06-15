#!/bin/bash

OPEN="java -jar $HOME/.m2/repository/net/avh4/util/imagediff/0.0.6/imagediff-0.0.6.jar"

for actual in "$@"; do
	expected="`find src -name "$actual"`"
	$OPEN "$expected" "$actual"
done

select approved in "$@"; do
	expected="`find src -name "$approved"`"
	mv -v "$approved" "$expected"
done
