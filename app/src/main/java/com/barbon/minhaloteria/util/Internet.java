package com.barbon.minhaloteria.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Barbon on 01/05/2015.
 */
public  class Internet {

    public static boolean existeConexao(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null)
        {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            if (netInfo == null) {
                return false;
            }

            int netType = netInfo.getType();

            if (netType == ConnectivityManager.TYPE_WIFI ||
                    netType == ConnectivityManager.TYPE_MOBILE) {

                return netInfo.isConnected();

            } else {
                return false;
            }

        }else{
            return false;
        }
    }

    public static boolean conectadoWifi(Context context){

        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null)
        {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            if (netInfo == null) {
                return false;
            }

            int netType = netInfo.getType();

            if (netType == ConnectivityManager.TYPE_WIFI) {
                return netInfo.isConnected();
            } else {
                return false;
            }

        }else{
            return false;
        }

    }
}
