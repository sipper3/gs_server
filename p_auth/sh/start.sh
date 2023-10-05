JAVA_HOME=/usr/lib/jvm/java
PRJ_PROGRAM=p_auth-0.0.1-SNAPSHOT.war
PRJ_HOME=/home/fingate/app/auth
nohup $JAVA_HOME/bin/java -Dspring.profiles.active=dev -jar $PRJ_HOME/$PRJ_PROGRAM > /dev/null 2>&1 &