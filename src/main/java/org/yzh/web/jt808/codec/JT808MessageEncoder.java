package org.yzh.web.jt808.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.yzh.framework.codec.MessageEncoder;
import org.yzh.framework.commons.transform.BitOperator;
import org.yzh.framework.message.PackageData;
import org.yzh.web.jt808.dto.basics.Header;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 808协议编码器
 *
 * @author zhihao.ye (yezhihaoo@gmail.com)
 */
public class JT808MessageEncoder extends MessageEncoder<Header> {

    public JT808MessageEncoder(Charset charset) {
        super(charset);
    }

    @Override
    public ByteBuf encodeAll(PackageData<Header> body) {
        ByteBuf bodyBuf = encode(body);

        Header header = body.getHeader();
        header.setBodyLength(bodyBuf.readableBytes());

        ByteBuf headerBuf = encode(header);

        ByteBuf allBuf = Unpooled.wrappedBuffer(headerBuf, bodyBuf);
        byte checkCode = BitOperator.xor(allBuf);

        allBuf.writeByte(checkCode);
        allBuf = escape(allBuf);

        return allBuf;
    }

    /**
     * 转义处理
     */
    private static ByteBuf escape(ByteBuf source) {
        int low = source.readerIndex();
        int high = source.writerIndex();

        int mark = source.forEachByte(low, high, value -> !(value == 0x7d || value == 0x7e));

        if (mark == -1)
            return source;

        List<ByteBuf> bufList = new ArrayList<>(5);

        int len;
        do {

            len = mark + 1 - low;
            ByteBuf[] slice = slice(source, low, len);
            bufList.add(slice[0]);
            bufList.add(slice[1]);
            low += len;

            mark = source.forEachByte(low, high - low, value -> !(value == 0x7d || value == 0x7e));
        } while (mark > 0);

        bufList.add(source.slice(low, high - low));

        ByteBuf[] bufs = bufList.toArray(new ByteBuf[bufList.size()]);

        return Unpooled.wrappedBuffer(bufs);
    }

    /**
     * 截断转义前报文，并转义
     */
    private static ByteBuf[] slice(ByteBuf byteBuf, int index, int length) {
        byte first = byteBuf.getByte(index + length - 1);

        ByteBuf[] bufs = new ByteBuf[2];
        bufs[0] = byteBuf.slice(index, length);

        if (first == 0x7d)
            // 0x01 不做处理 p47
            bufs[1] = Unpooled.wrappedBuffer(new byte[]{0x01});
        else {
            byteBuf.setByte(index + length - 1, 0x7d);
            bufs[1] = Unpooled.wrappedBuffer(new byte[]{0x02});
        }

        return bufs;
    }
}