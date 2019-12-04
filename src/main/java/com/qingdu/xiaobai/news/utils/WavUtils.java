package com.qingdu.xiaobai.news.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WavUtils {

    public static final String SOURCE_FILE = "/Users/KoreQ/Desktop/lenovo_audio.wav";
    public static final String DEST_DIR = "/Users/KoreQ/Desktop";
    public static final String DEST_FILE_NAME = "wave_to_pcm_result.pcm";

    public static void main(String[] args) {

        wavToPcm(SOURCE_FILE, DEST_DIR + File.separator + DEST_FILE_NAME);
        System.out.println("wavToPcm finished!~~~~\n");

        byte[] waveFileHeader = getWaveFileHeader(16000, 6, 16, 12 * 16000, 35390988, 16);
        System.out.println(bytesToHex(waveFileHeader));
        System.out.println();

        byte[] data = {'d', 'a', 't', 'a'};
        System.out.println("data : " + bytesToHex(data));

        byte[] riff = {'R', 'I', 'F', 'F'};
        System.out.println("riff : " + bytesToHex(riff));

        try {
            byte[] dateLen1 = {(byte) 0x02, (byte) 0x1B, (byte) 0xEF, (byte) 0x68};
            System.out.println(bytes2long(dateLen1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            byte[] dateLen2 = {(byte) 0x02, (byte) 0x1c, (byte) 0x05, (byte) 0xE0};
            System.out.println(bytes2long(dateLen2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wavToPcm(String sourcePath, String targetPath) {
        try {
            FileInputStream fis = new FileInputStream(sourcePath);
            FileOutputStream fos = new FileOutputStream(targetPath);
            byte[] buf = new byte[1024 * 4];
            int size = fis.read(buf);
            int headLength = getWaveHeadLen(buf[16]);               // 根据format chunk的size判断wav header 长度
            System.out.println("headLength = " + headLength);
            fos.write(buf, headLength, size - headLength);

            while ((size = fis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 传入formatChunk的size， 来判断wav header的长度
     *
     * @param formatChunkSize size = 16 bytes or 18 bytes
     * @return the num of byte
     */
    private static int getWaveHeadLen(byte formatChunkSize) {
        if (formatChunkSize == 0x10) {
            return 44;
        } else if (formatChunkSize == 0x12) {
            return 46;
        }
        return 44;
    }

    /**
     * @param sampleRate           采样率，如44100
     * @param channels             通道数，如立体声为2
     * @param bitsPerSample        采样精度，即每个采样所占数据位数，如16，表示每个采样16bit数据，即2个字节
     * @param bytePerSecond        音频数据传送速率, 单位是字节。其值为采样率×每次采样大小。播放软件利用此值可以估计缓冲区的大小。
     *                             bytePerSecond = sampleRate * (bitsPerSample / 8) * channels
     * @param fileLenIncludeHeader wav文件总数据大小，包括44（46）字节wave文件头大小
     * @param formatChunkSize      FormatChunk块大小，可能是16bytes或18bytes
     * @return wavHeader
     */
    private static byte[] getWaveFileHeader(int sampleRate, int channels,
                                            int bitsPerSample, int bytePerSecond, long fileLenIncludeHeader, int formatChunkSize) {

        int wavHeadLen = 44;
        if (formatChunkSize == 0x10) {
            wavHeadLen = 44;
        } else if (formatChunkSize == 0x12) {
            wavHeadLen = 46;
        }

        byte[] wavHeader = new byte[wavHeadLen];
        long totalDataLen = fileLenIncludeHeader - 8;
        long audioDataLen = fileLenIncludeHeader - wavHeadLen;

//                ------------------------------------------------
//                |             RIFF WAVE Chunk                  |
//                |             ID  = 'RIFF'                     |
//                |             RiffType = 'WAVE'                |
//                ------------------------------------------------
//                |             Format Chunk                     |
//                |             ID = 'fmt '                      |
//                ------------------------------------------------
//                |             Fact Chunk(optional)             |
//                |             ID = 'fact'                      |
//                ------------------------------------------------
//                |             Data Chunk                       |
//                |             ID = 'data'                      |
//                ------------------------------------------------

//        RIFF WAVE Chunk    
        //ckid：4字节 RIFF 标志，大写
        wavHeader[0] = 'R';
        wavHeader[1] = 'I';
        wavHeader[2] = 'F';
        wavHeader[3] = 'F';

        //cksize：4字节文件长度，这个长度不包括"RIFF"标志(4字节)和文件长度本身所占字节(4字节),即该长度等于整个文件长度 - 8
        wavHeader[4] = (byte) (totalDataLen & 0xff);
        wavHeader[5] = (byte) ((totalDataLen >> 8) & 0xff);
        wavHeader[6] = (byte) ((totalDataLen >> 16) & 0xff);
        wavHeader[7] = (byte) ((totalDataLen >> 24) & 0xff);

        //fcc type：4字节 "WAVE" 类型块标识, 大写
        wavHeader[8] = 'W';
        wavHeader[9] = 'A';
        wavHeader[10] = 'V';
        wavHeader[11] = 'E';

        //ckid：4字节 表示"fmt" chunk的开始,此块中包括文件内部格式信息，小写, 最后一个字符是空格
        wavHeader[12] = 'f';
        wavHeader[13] = 'm';
        wavHeader[14] = 't';
        wavHeader[15] = ' ';

//        Format Chunk
        //cksize：4字节
        wavHeader[16] = (byte) formatChunkSize;       // 该块数据长度：0x10 or 0x12；如果为0x12 表示在BitsPerSample之后会有两个字节的附加信息
        wavHeader[17] = 0;
        wavHeader[18] = 0;
        wavHeader[19] = 0;

        //FormatTag：2字节，音频数据的编码方式，1：表示是PCM 编码
        wavHeader[20] = 1;
        wavHeader[21] = 0;

        //Channels：2字节，声道数，单声道为1，双声道为2
        wavHeader[22] = (byte) channels;
        wavHeader[23] = 0;

        //SamplesPerSec：4字节，采样率，如44100
        wavHeader[24] = (byte) (sampleRate & 0xff);
        wavHeader[25] = (byte) ((sampleRate >> 8) & 0xff);
        wavHeader[26] = (byte) ((sampleRate >> 16) & 0xff);
        wavHeader[27] = (byte) ((sampleRate >> 24) & 0xff);

        //BytesPerSec：4字节，音频数据传送速率, 单位是字节。其值为采样率×每次采样大小。播放软件利用此值可以估计缓冲区的大小；
        //bytePerSecond = sampleRate * (bitsPerSample / 8) * channels
        wavHeader[28] = (byte) (bytePerSecond & 0xff);
        wavHeader[29] = (byte) ((bytePerSecond >> 8) & 0xff);
        wavHeader[30] = (byte) ((bytePerSecond >> 16) & 0xff);
        wavHeader[31] = (byte) ((bytePerSecond >> 24) & 0xff);

        //BlockAlign：2字节，每次采样的大小 = 采样精度*声道数/8(单位是字节); 这也是字节对齐的最小单位, 譬如 16bit 立体声在这里的值是 4 字节。
        //播放软件需要一次处理多个该值大小的字节数据，以便将其值用于缓冲区的调整
        wavHeader[32] = (byte) (bitsPerSample * channels / 8);
        wavHeader[33] = 0;

        //BitsPerSample：2字节，每个声道的采样精度; 譬如 16bit 在这里的值就是16。如果有多个声道，则每个声道的采样精度大小都一样的；
        wavHeader[34] = (byte) bitsPerSample;
        wavHeader[35] = 0;

        int index = 36;
        if (formatChunkSize == 0x10) {
        } else if (formatChunkSize == 0x12) {
            wavHeader[index++] = 0;
            wavHeader[index++] = 0;
        }

//        Data Chunk
        //ckid：4字节，数据标志符（data），表示 "data" chunk的开始。此块中包含音频数据，小写；
        wavHeader[index++] = 'd';
        wavHeader[index++] = 'a';
        wavHeader[index++] = 't';
        wavHeader[index++] = 'a';

        //cksize：音频数据的长度，4字节，audioDataLen = totalDataLen - 36 = fileLenIncludeHeader - 44
        wavHeader[index++] = (byte) (audioDataLen & 0xff);
        wavHeader[index++] = (byte) ((audioDataLen >> 8) & 0xff);
        wavHeader[index++] = (byte) ((audioDataLen >> 16) & 0xff);
        wavHeader[index] = (byte) ((audioDataLen >> 24) & 0xff);

        return wavHeader;
    }

    /**
     * 字节转十六进制
     *
     * @param b 需要进行转换的byte字节
     * @return 转换后的Hex字符串
     */
    public static String byteToHex(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }


    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
            sb.append(" ");
        }
        return sb.toString();
    }
//    52 49 46 46 04 06 1C 02 57 41 56 45 66 6D 74 20 12 00 00 00 01 00 06 00 80 3E 00 00 00 EE 02 00 0C 00 10 00 00 00 64 61 74 61 68 EF 1B 02
//    52 49 46 46 04 06 1C 02 57 41 56 45 66 6D 74 20 10 00 00 00 01 00 06 00 80 3E 00 00 00 EE 02 00 0C 00 10 00 64 61 74 61 E0 05 1C 02

    /**
     * 数值的高位在字节数组的低位
     *
     * @param bs
     * @return
     * @throws Exception
     */
    public static long bytes2long(byte[] bs) throws Exception {
        int bytes = bs.length;
        if (bytes > 1) {
            if ((bytes % 2) != 0 || bytes > 8) {
                throw new Exception("not support");
            }
        }
        switch (bytes) {
            case 0:
                return 0;
            case 1:
                return (long) ((bs[0] & 0xff));
            case 2:
                return (long) ((bs[0] & 0xff) << 8 | (bs[1] & 0xff));
            case 4:
                return (long) ((bs[0] & 0xffL) << 24 | (bs[1] & 0xffL) << 16 | (bs[2] & 0xffL) << 8 | (bs[3] & 0xffL));
            case 8:
                return (long) ((bs[0] & 0xffL) << 56 | (bs[1] & 0xffL) << 48 | (bs[2] & 0xffL) << 40 | (bs[3] & 0xffL) << 32 |
                        (bs[4] & 0xffL) << 24 | (bs[5] & 0xffL) << 16 | (bs[6] & 0xffL) << 8 | (bs[7] & 0xffL));
            default:
                throw new Exception("not support");
        }
        //return 0;
    }
}

