package com.lla.emarsyssdk;

import android.content.Context;
import android.util.Log;
import com.emarsys.Emarsys;
import com.emarsys.config.EmarsysConfig;
import com.emarsys.mobileengage.api.event.EventHandler;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@CapacitorPlugin(name = "EmarsysSDKCustom")
public class EmarsysSDKCustomPlugin extends Plugin implements EventHandler {

    public EmarsysConfig config;

    private EmarsysSDKCustom implementation = new EmarsysSDKCustom();
    EmarsysPushNotification emarsysPushNotification = new EmarsysPushNotification();

    @Override
    public void load() {
        config =
            new EmarsysConfig.Builder()
                .application(this.getActivity().getApplication()) //
                .applicationCode("EMSD5-99166")
                .merchantId("1F634D68EE4C9C7A")
                .enableVerboseConsoleLogging()
                .build();

        Log.d("Emersys", config.toString());

        Emarsys.setup(config);
        Log.d("Emersys", "EMERSYS has been configured... ");

        Emarsys.getPush().setNotificationEventHandler(this);
        Emarsys.getInApp().setEventHandler(this);
    }

    @Override
    public void handleEvent(Context context, @NotNull String eventName, @Nullable JSONObject payload) {
        if ("App Event".equals(eventName)) {
            try {
                String campaignId = payload.getString("campaign_id");
                if (campaignId != null) {
                    // navigate to the Sales campaign view
                }
            } catch (Exception e) {
                System.out.println("Error When Trying to run the handle Event for App Event ");
            }
        }

        if ("Deeplink".equals(eventName)) {
            try {
                String campaignId = payload.getString("campaign_id");
                if (campaignId != null) {
                    // navigate to the Sales campaign view
                }
            } catch (Exception e) {
                System.out.println("Error When Trying to run the handle Event for Deeplink ");
            }
        }
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    // setPushTokenFirebase
    @PluginMethod
    public void setPushTokenFirebase(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.initializeEmarsys(value));
        System.out.println("push token value " + value);

        emarsysPushNotification.onNewToken(value);

        call.resolve(ret);
    }

    @PluginMethod
    public void setUser(PluginCall call) {
        String value = call.getString("value");
        System.out.println("SET USER IN THE PLUGIN " + value);

        emarsysPushNotification.setContactUser(value);
    }

    @PluginMethod
    public void clearUser(PluginCall call) {
        String value = call.getString("value");
        System.out.println("CLEAR USER IN THE PLUGIN " + value);

        emarsysPushNotification.clearContactUser();
    }

    @PluginMethod
    public void setPushEvent(PluginCall call) {
        String eventName = call.getString("eventName");
        JSONObject payload = call.getObject("payload");
        System.out.println("CLEAR USER IN THE PLUGIN " + eventName);
        System.out.println("Get payload in the Plugin " + payload);

        handleEvent(null, eventName, payload);
    }
}
