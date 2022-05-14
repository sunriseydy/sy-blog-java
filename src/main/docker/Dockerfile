FROM registry.cn-hangzhou.aliyuncs.com/choerodon-tools/javabase:0.8.0
COPY sy-blog-java-0.0.1-SNAPSHOT.jar /sy-blog-java.jar
ADD entrypoint.sh /entrypoint.sh
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8 -Duser.language=zh -Duser.region=zh_CN -Duser.country=zh_CN
ENTRYPOINT ["/entrypoint.sh"]