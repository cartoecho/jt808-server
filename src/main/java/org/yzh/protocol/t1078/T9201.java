package org.yzh.protocol.t1078;

import org.yzh.framework.orm.annotation.Field;
import org.yzh.framework.orm.annotation.Message;
import org.yzh.framework.orm.model.AbstractMessage;
import org.yzh.framework.orm.model.DataType;
import org.yzh.protocol.basics.Header;
import org.yzh.protocol.commons.Charsets;
import org.yzh.protocol.commons.JT1078;

/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
@Message(JT1078.平台下发远程录像回放请求)
public class T9201 extends AbstractMessage<Header> {

    private int ipLength;
    private String ip;
    private int tcpPort;
    private int udpPort;
    private int channelNo;
    private int dataType;
    private int streamType;
    private int storageType;
    private int playbackMode;
    private int playbackSpeed;
    private String startTime;
    private String endTime;

    @Field(index = 0, type = DataType.BYTE, desc = "服务器IP地址长度")
    public int getIpLength() {
        return ipLength;
    }

    public void setIpLength(int ipLength) {
        this.ipLength = ipLength;
    }

    @Field(index = 1, lengthName = "ipLength", type = DataType.STRING, desc = "服务器IP地址")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
        this.ipLength = ip.getBytes(Charsets.GBK).length;
    }

    @Field(index = 1, indexOffsetName = "ipLength", type = DataType.WORD, desc = "实时视频服务器TCP端口号")
    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    @Field(index = 3, indexOffsetName = "ipLength", type = DataType.WORD, desc = "实时视频服务器UDP端口号")
    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    @Field(index = 5, indexOffsetName = "ipLength", type = DataType.BYTE, desc = "逻辑通道号")
    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }

    @Field(index = 6, indexOffsetName = "ipLength", type = DataType.BYTE, desc = "音视频类型")
    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    @Field(index = 7, indexOffsetName = "ipLength", type = DataType.BYTE, desc = "码流类型")
    public int getStreamType() {
        return streamType;
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }

    @Field(index = 8, indexOffsetName = "ipLength", type = DataType.BYTE, desc = "存储器类型")
    public int getStorageType() {
        return storageType;
    }

    public void setStorageType(int storageType) {
        this.storageType = storageType;
    }

    @Field(index = 9, indexOffsetName = "ipLength", type = DataType.BYTE, desc = "回放方式")
    public int getPlaybackMode() {
        return playbackMode;
    }

    public void setPlaybackMode(int playbackMode) {
        this.playbackMode = playbackMode;
    }

    @Field(index = 10, indexOffsetName = "ipLength", type = DataType.BYTE, desc = "快进或快退倍数")
    public int getPlaybackSpeed() {
        return playbackSpeed;
    }

    public void setPlaybackSpeed(int playbackSpeed) {
        this.playbackSpeed = playbackSpeed;
    }

    @Field(index = 11, indexOffsetName = "ipLength", type = DataType.BCD8421, length = 6, desc = "开始时间")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Field(index = 17, indexOffsetName = "ipLength", type = DataType.BCD8421, length = 6, desc = "结束时间")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}