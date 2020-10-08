package com.welearn.websocket;

import com.welearn.websocket.handler.WebSocketTextFrameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/19.
 */
@Slf4j
@Service
public class WebSocketServer {

    @PostConstruct
    public void startInSpringBoot(){
        new Thread(this::run).start();
    }

    /**
     * 定期清理超时未认证的 WebSocket, 限制是 15s 以内未认证 则会被关闭
     */
    @Scheduled(fixedRate=15000)
    public void cleanUnAuthSocket(){
        long currentTime = new Date().getTime();
        WebSocketTextFrameHandler.UN_AUTH_CHANNELS.forEach((id, channelInfo)->{
            if ((currentTime - channelInfo.getCreatedAt().getTime())/1000 >= 15){
                channelInfo.getContext().channel().close();
            }
        });
    }

    private void run(){
        log.info("WebSocket Netty Server Start...");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WebSocketChannelInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(9190)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            log.info("WebSocket Netty Server Stop...");
        }
    }
}
