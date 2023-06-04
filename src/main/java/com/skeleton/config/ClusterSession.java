package com.skeleton.config;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.ha.session.ClusterSessionListener;
import org.apache.catalina.ha.session.DeltaManager;
import org.apache.catalina.ha.session.JvmRouteBinderValve;
import org.apache.catalina.ha.tcp.ReplicationValve;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.group.interceptors.MessageDispatchInterceptor;
import org.apache.catalina.tribes.group.interceptors.TcpFailureDetector;
import org.apache.catalina.tribes.group.interceptors.TcpPingInterceptor;
import org.apache.catalina.tribes.membership.McastService;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

//https://tomcat.apache.org/tomcat-9.0-doc/cluster-howto.html
@Configuration
public class ClusterSession implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    public void customize(final TomcatServletWebServerFactory factory){
        factory.addContextCustomizers(new TomcatClusterContextCustomizer());
    }

}

class TomcatClusterContextCustomizer implements TomcatContextCustomizer{
    public void customize(final Context context){
        context.setDistributable(true);

        DeltaManager dm = new DeltaManager();
        dm.setExpireSessionsOnShutdown(false);
        dm.setNotifyContainerListenersOnReplication(true);
        context.setManager(dm);

        configureCluster((Engine) context.getParent().getParent());
    }

    private void configureCluster(Engine engine){
        SimpleTcpCluster cluster = new SimpleTcpCluster();
        cluster.setChannelSendOptions(8);

        //channel
        GroupChannel channel = new GroupChannel();
        //mcastService
        McastService ms = new McastService();
        ms.setAddress("228.0.0.4");
        ms.setPort(45564);
        ms.setFrequency(500);
        ms.setDropTime(3000);
        channel.setMembershipService(ms);

        //receiver
        NioReceiver nr = new NioReceiver();
        nr.setAddress("auto");
        nr.setMaxThreads(6);
        nr.setPort(4000);
        nr.setAutoBind(100);
        nr.setSelectorTimeout(5000);
        channel.setChannelReceiver(nr);

        channel.addInterceptor(new TcpPingInterceptor());
        channel.addInterceptor(new TcpFailureDetector());
        channel.addInterceptor(new MessageDispatchInterceptor());

        cluster.addValve(new ReplicationValve());
        cluster.addValve(new JvmRouteBinderValve());
        cluster.setChannel(channel);
        cluster.addClusterListener(new ClusterSessionListener());
        engine.setCluster(cluster);
    }

}
