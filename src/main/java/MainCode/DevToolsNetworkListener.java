package MainCode;

import java.lang.classfile.ClassFile.Option;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.ResponseReceived;



public class DevToolsNetworkListener {


    public static int getAPIStatus(WebDriver driver){

        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();



        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        AtomicReference<Integer> apiStatusAtomic = new AtomicReference<>(null);
        CountDownLatch latch = new CountDownLatch(1);

        devTools.addListener(Network.responseReceived(), new Consumer<ResponseReceived>() {
            
            @Override
            public void accept(ResponseReceived responseReceived){

                String url = responseReceived.getResponse().getUrl();
                int status = responseReceived.getResponse().getStatus().intValue();
                
                if (url.contains("tp_criteria_search")){
                    apiStatusAtomic.set(status);
                    latch.countDown();
                }
            
            }
            
        });


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return apiStatusAtomic.get();

    }


}
