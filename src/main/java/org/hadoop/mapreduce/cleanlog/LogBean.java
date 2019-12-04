package org.hadoop.mapreduce.cleanlog;

import lombok.*;
import org.apache.commons.lang.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogBean {
    private String remote_addr;// 记录客户端的ip地址
    private String remote_user;// 记录客户端用户名称,忽略属性"-"
    private String time_local;// 记录访问时间与时区
    private String request;// 记录请求的url与http协议
    private String status;// 记录请求状态；成功是200
    private String body_bytes_sent;// 记录发送给客户端文件主体内容大小
    private String http_referer;// 用来记录从那个页面链接访问过来的
    private String http_user_agent;// 记录客户浏览器的相关信息

    private boolean valid = true;// 判断数据是否合法

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.valid);
        stringBuilder.append("\001").append(remote_addr);
        stringBuilder.append("\001").append(remote_user);
        stringBuilder.append("\001").append(time_local);
        stringBuilder.append("\001").append(request);
        stringBuilder.append("\001").append(status);
        stringBuilder.append("\001").append(body_bytes_sent);
        stringBuilder.append("\001").append(http_referer);
        stringBuilder.append("\001").append(http_user_agent);
        return stringBuilder.toString();
    }

    public boolean isValid(){
        if(StringUtils.isEmpty(status)||Integer.parseInt(status)>=400){
            return false;
        }
        return true;
    }


}
