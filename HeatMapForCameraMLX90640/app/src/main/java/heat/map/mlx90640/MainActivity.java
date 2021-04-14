package heat.map.mlx90640;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements SerialInputOutputManager.Listener {

    TextView[][] arrayTextView = new TextView[24][32];
    private View decorView;

    UsbSerialPort port;
    private static final String ACTION_USB_PERMISSION = "com.android.recipes.USB_PERMISSION";
    private static final String INTENT_ACTION_GRANT_USB = BuildConfig.APPLICATION_ID + ".GRANT_USB";

    private void initUSBPort(){
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);

        if (availableDrivers.isEmpty()) {
            Log.d("UART", "UART is not available");

        }else {
            Log.d("UART", "UART is available");

            UsbSerialDriver driver = availableDrivers.get(0);
            UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
            if (connection == null) {

                PendingIntent usbPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(INTENT_ACTION_GRANT_USB), 0);
                manager.requestPermission(driver.getDevice(), usbPermissionIntent);

                manager.requestPermission(driver.getDevice(), PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0));

                return;
            } else {

                port = driver.getPorts().get(0);
                try {
                    Log.d("UART", "openned succesful");
                    port.open(connection);
                    port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

                    //port.write("ABC#".getBytes(), 1000);

                    SerialInputOutputManager usbIoManager = new SerialInputOutputManager(port, this);
                    Executors.newSingleThreadExecutor().submit(usbIoManager);

                } catch (Exception e) {
                    Log.d("UART", "There is error");
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    hideSystemBar();
                }
            }
        });

        arrayTextView[0][0] = findViewById(R.id.hello_0_0);
        arrayTextView[0][1] = findViewById(R.id.hello_0_1);
        arrayTextView[0][2] = findViewById(R.id.hello_0_2);
        arrayTextView[0][3] = findViewById(R.id.hello_0_3);
        arrayTextView[0][4] = findViewById(R.id.hello_0_4);
        arrayTextView[0][5] = findViewById(R.id.hello_0_5);
        arrayTextView[0][6] = findViewById(R.id.hello_0_6);
        arrayTextView[0][7] = findViewById(R.id.hello_0_7);
        arrayTextView[0][8] = findViewById(R.id.hello_0_8);
        arrayTextView[0][9] = findViewById(R.id.hello_0_9);
        arrayTextView[0][10] = findViewById(R.id.hello_0_10);
        arrayTextView[0][11] = findViewById(R.id.hello_0_11);
        arrayTextView[0][12] = findViewById(R.id.hello_0_12);
        arrayTextView[0][13] = findViewById(R.id.hello_0_13);
        arrayTextView[0][14] = findViewById(R.id.hello_0_14);
        arrayTextView[0][15] = findViewById(R.id.hello_0_15);
        arrayTextView[0][16] = findViewById(R.id.hello_0_16);
        arrayTextView[0][17] = findViewById(R.id.hello_0_17);
        arrayTextView[0][18] = findViewById(R.id.hello_0_18);
        arrayTextView[0][19] = findViewById(R.id.hello_0_19);
        arrayTextView[0][20] = findViewById(R.id.hello_0_20);
        arrayTextView[0][21] = findViewById(R.id.hello_0_21);
        arrayTextView[0][22] = findViewById(R.id.hello_0_22);
        arrayTextView[0][23] = findViewById(R.id.hello_0_23);
        arrayTextView[0][24] = findViewById(R.id.hello_0_24);
        arrayTextView[0][25] = findViewById(R.id.hello_0_25);
        arrayTextView[0][26] = findViewById(R.id.hello_0_26);
        arrayTextView[0][27] = findViewById(R.id.hello_0_27);
        arrayTextView[0][28] = findViewById(R.id.hello_0_28);
        arrayTextView[0][29] = findViewById(R.id.hello_0_29);
        arrayTextView[0][30] = findViewById(R.id.hello_0_30);
        arrayTextView[0][31] = findViewById(R.id.hello_0_31);

        arrayTextView[1][0] = findViewById(R.id.hello_1_0);
        arrayTextView[1][1] = findViewById(R.id.hello_1_1);
        arrayTextView[1][2] = findViewById(R.id.hello_1_2);
        arrayTextView[1][3] = findViewById(R.id.hello_1_3);
        arrayTextView[1][4] = findViewById(R.id.hello_1_4);
        arrayTextView[1][5] = findViewById(R.id.hello_1_5);
        arrayTextView[1][6] = findViewById(R.id.hello_1_6);
        arrayTextView[1][7] = findViewById(R.id.hello_1_7);
        arrayTextView[1][8] = findViewById(R.id.hello_1_8);
        arrayTextView[1][9] = findViewById(R.id.hello_1_9);
        arrayTextView[1][10] = findViewById(R.id.hello_1_10);
        arrayTextView[1][11] = findViewById(R.id.hello_1_11);
        arrayTextView[1][12] = findViewById(R.id.hello_1_12);
        arrayTextView[1][13] = findViewById(R.id.hello_1_13);
        arrayTextView[1][14] = findViewById(R.id.hello_1_14);
        arrayTextView[1][15] = findViewById(R.id.hello_1_15);
        arrayTextView[1][16] = findViewById(R.id.hello_1_16);
        arrayTextView[1][17] = findViewById(R.id.hello_1_17);
        arrayTextView[1][18] = findViewById(R.id.hello_1_18);
        arrayTextView[1][19] = findViewById(R.id.hello_1_19);
        arrayTextView[1][20] = findViewById(R.id.hello_1_20);
        arrayTextView[1][21] = findViewById(R.id.hello_1_21);
        arrayTextView[1][22] = findViewById(R.id.hello_1_22);
        arrayTextView[1][23] = findViewById(R.id.hello_1_23);
        arrayTextView[1][24] = findViewById(R.id.hello_1_24);
        arrayTextView[1][25] = findViewById(R.id.hello_1_25);
        arrayTextView[1][26] = findViewById(R.id.hello_1_26);
        arrayTextView[1][27] = findViewById(R.id.hello_1_27);
        arrayTextView[1][28] = findViewById(R.id.hello_1_28);
        arrayTextView[1][29] = findViewById(R.id.hello_1_29);
        arrayTextView[1][30] = findViewById(R.id.hello_1_30);
        arrayTextView[1][31] = findViewById(R.id.hello_1_31);

        arrayTextView[2][0] = findViewById(R.id.hello_2_0);
        arrayTextView[2][1] = findViewById(R.id.hello_2_1);
        arrayTextView[2][2] = findViewById(R.id.hello_2_2);
        arrayTextView[2][3] = findViewById(R.id.hello_2_3);
        arrayTextView[2][4] = findViewById(R.id.hello_2_4);
        arrayTextView[2][5] = findViewById(R.id.hello_2_5);
        arrayTextView[2][6] = findViewById(R.id.hello_2_6);
        arrayTextView[2][7] = findViewById(R.id.hello_2_7);
        arrayTextView[2][8] = findViewById(R.id.hello_2_8);
        arrayTextView[2][9] = findViewById(R.id.hello_2_9);
        arrayTextView[2][10] = findViewById(R.id.hello_2_10);
        arrayTextView[2][11] = findViewById(R.id.hello_2_11);
        arrayTextView[2][12] = findViewById(R.id.hello_2_12);
        arrayTextView[2][13] = findViewById(R.id.hello_2_13);
        arrayTextView[2][14] = findViewById(R.id.hello_2_14);
        arrayTextView[2][15] = findViewById(R.id.hello_2_15);
        arrayTextView[2][16] = findViewById(R.id.hello_2_16);
        arrayTextView[2][17] = findViewById(R.id.hello_2_17);
        arrayTextView[2][18] = findViewById(R.id.hello_2_18);
        arrayTextView[2][19] = findViewById(R.id.hello_2_19);
        arrayTextView[2][20] = findViewById(R.id.hello_2_20);
        arrayTextView[2][21] = findViewById(R.id.hello_2_21);
        arrayTextView[2][22] = findViewById(R.id.hello_2_22);
        arrayTextView[2][23] = findViewById(R.id.hello_2_23);
        arrayTextView[2][24] = findViewById(R.id.hello_2_24);
        arrayTextView[2][25] = findViewById(R.id.hello_2_25);
        arrayTextView[2][26] = findViewById(R.id.hello_2_26);
        arrayTextView[2][27] = findViewById(R.id.hello_2_27);
        arrayTextView[2][28] = findViewById(R.id.hello_2_28);
        arrayTextView[2][29] = findViewById(R.id.hello_2_29);
        arrayTextView[2][30] = findViewById(R.id.hello_2_30);
        arrayTextView[2][31] = findViewById(R.id.hello_2_31);

        arrayTextView[3][0] = findViewById(R.id.hello_3_0);
        arrayTextView[3][1] = findViewById(R.id.hello_3_1);
        arrayTextView[3][2] = findViewById(R.id.hello_3_2);
        arrayTextView[3][3] = findViewById(R.id.hello_3_3);
        arrayTextView[3][4] = findViewById(R.id.hello_3_4);
        arrayTextView[3][5] = findViewById(R.id.hello_3_5);
        arrayTextView[3][6] = findViewById(R.id.hello_3_6);
        arrayTextView[3][7] = findViewById(R.id.hello_3_7);
        arrayTextView[3][8] = findViewById(R.id.hello_3_8);
        arrayTextView[3][9] = findViewById(R.id.hello_3_9);
        arrayTextView[3][10] = findViewById(R.id.hello_3_10);
        arrayTextView[3][11] = findViewById(R.id.hello_3_11);
        arrayTextView[3][12] = findViewById(R.id.hello_3_12);
        arrayTextView[3][13] = findViewById(R.id.hello_3_13);
        arrayTextView[3][14] = findViewById(R.id.hello_3_14);
        arrayTextView[3][15] = findViewById(R.id.hello_3_15);
        arrayTextView[3][16] = findViewById(R.id.hello_3_16);
        arrayTextView[3][17] = findViewById(R.id.hello_3_17);
        arrayTextView[3][18] = findViewById(R.id.hello_3_18);
        arrayTextView[3][19] = findViewById(R.id.hello_3_19);
        arrayTextView[3][20] = findViewById(R.id.hello_3_20);
        arrayTextView[3][21] = findViewById(R.id.hello_3_21);
        arrayTextView[3][22] = findViewById(R.id.hello_3_22);
        arrayTextView[3][23] = findViewById(R.id.hello_3_23);
        arrayTextView[3][24] = findViewById(R.id.hello_3_24);
        arrayTextView[3][25] = findViewById(R.id.hello_3_25);
        arrayTextView[3][26] = findViewById(R.id.hello_3_26);
        arrayTextView[3][27] = findViewById(R.id.hello_3_27);
        arrayTextView[3][28] = findViewById(R.id.hello_3_28);
        arrayTextView[3][29] = findViewById(R.id.hello_3_29);
        arrayTextView[3][30] = findViewById(R.id.hello_3_30);
        arrayTextView[3][31] = findViewById(R.id.hello_3_31);

        arrayTextView[4][0] = findViewById(R.id.hello_4_0);
        arrayTextView[4][1] = findViewById(R.id.hello_4_1);
        arrayTextView[4][2] = findViewById(R.id.hello_4_2);
        arrayTextView[4][3] = findViewById(R.id.hello_4_3);
        arrayTextView[4][4] = findViewById(R.id.hello_4_4);
        arrayTextView[4][5] = findViewById(R.id.hello_4_5);
        arrayTextView[4][6] = findViewById(R.id.hello_4_6);
        arrayTextView[4][7] = findViewById(R.id.hello_4_7);
        arrayTextView[4][8] = findViewById(R.id.hello_4_8);
        arrayTextView[4][9] = findViewById(R.id.hello_4_9);
        arrayTextView[4][10] = findViewById(R.id.hello_4_10);
        arrayTextView[4][11] = findViewById(R.id.hello_4_11);
        arrayTextView[4][12] = findViewById(R.id.hello_4_12);
        arrayTextView[4][13] = findViewById(R.id.hello_4_13);
        arrayTextView[4][14] = findViewById(R.id.hello_4_14);
        arrayTextView[4][15] = findViewById(R.id.hello_4_15);
        arrayTextView[4][16] = findViewById(R.id.hello_4_16);
        arrayTextView[4][17] = findViewById(R.id.hello_4_17);
        arrayTextView[4][18] = findViewById(R.id.hello_4_18);
        arrayTextView[4][19] = findViewById(R.id.hello_4_19);
        arrayTextView[4][20] = findViewById(R.id.hello_4_20);
        arrayTextView[4][21] = findViewById(R.id.hello_4_21);
        arrayTextView[4][22] = findViewById(R.id.hello_4_22);
        arrayTextView[4][23] = findViewById(R.id.hello_4_23);
        arrayTextView[4][24] = findViewById(R.id.hello_4_24);
        arrayTextView[4][25] = findViewById(R.id.hello_4_25);
        arrayTextView[4][26] = findViewById(R.id.hello_4_26);
        arrayTextView[4][27] = findViewById(R.id.hello_4_27);
        arrayTextView[4][28] = findViewById(R.id.hello_4_28);
        arrayTextView[4][29] = findViewById(R.id.hello_4_29);
        arrayTextView[4][30] = findViewById(R.id.hello_4_30);
        arrayTextView[4][31] = findViewById(R.id.hello_4_31);

        arrayTextView[5][0] = findViewById(R.id.hello_5_0);
        arrayTextView[5][1] = findViewById(R.id.hello_5_1);
        arrayTextView[5][2] = findViewById(R.id.hello_5_2);
        arrayTextView[5][3] = findViewById(R.id.hello_5_3);
        arrayTextView[5][4] = findViewById(R.id.hello_5_4);
        arrayTextView[5][5] = findViewById(R.id.hello_5_5);
        arrayTextView[5][6] = findViewById(R.id.hello_5_6);
        arrayTextView[5][7] = findViewById(R.id.hello_5_7);
        arrayTextView[5][8] = findViewById(R.id.hello_5_8);
        arrayTextView[5][9] = findViewById(R.id.hello_5_9);
        arrayTextView[5][10] = findViewById(R.id.hello_5_10);
        arrayTextView[5][11] = findViewById(R.id.hello_5_11);
        arrayTextView[5][12] = findViewById(R.id.hello_5_12);
        arrayTextView[5][13] = findViewById(R.id.hello_5_13);
        arrayTextView[5][14] = findViewById(R.id.hello_5_14);
        arrayTextView[5][15] = findViewById(R.id.hello_5_15);
        arrayTextView[5][16] = findViewById(R.id.hello_5_16);
        arrayTextView[5][17] = findViewById(R.id.hello_5_17);
        arrayTextView[5][18] = findViewById(R.id.hello_5_18);
        arrayTextView[5][19] = findViewById(R.id.hello_5_19);
        arrayTextView[5][20] = findViewById(R.id.hello_5_20);
        arrayTextView[5][21] = findViewById(R.id.hello_5_21);
        arrayTextView[5][22] = findViewById(R.id.hello_5_22);
        arrayTextView[5][23] = findViewById(R.id.hello_5_23);
        arrayTextView[5][24] = findViewById(R.id.hello_5_24);
        arrayTextView[5][25] = findViewById(R.id.hello_5_25);
        arrayTextView[5][26] = findViewById(R.id.hello_5_26);
        arrayTextView[5][27] = findViewById(R.id.hello_5_27);
        arrayTextView[5][28] = findViewById(R.id.hello_5_28);
        arrayTextView[5][29] = findViewById(R.id.hello_5_29);
        arrayTextView[5][30] = findViewById(R.id.hello_5_30);
        arrayTextView[5][31] = findViewById(R.id.hello_5_31);

        arrayTextView[6][0] = findViewById(R.id.hello_6_0);
        arrayTextView[6][1] = findViewById(R.id.hello_6_1);
        arrayTextView[6][2] = findViewById(R.id.hello_6_2);
        arrayTextView[6][3] = findViewById(R.id.hello_6_3);
        arrayTextView[6][4] = findViewById(R.id.hello_6_4);
        arrayTextView[6][5] = findViewById(R.id.hello_6_5);
        arrayTextView[6][6] = findViewById(R.id.hello_6_6);
        arrayTextView[6][7] = findViewById(R.id.hello_6_7);
        arrayTextView[6][8] = findViewById(R.id.hello_6_8);
        arrayTextView[6][9] = findViewById(R.id.hello_6_9);
        arrayTextView[6][10] = findViewById(R.id.hello_6_10);
        arrayTextView[6][11] = findViewById(R.id.hello_6_11);
        arrayTextView[6][12] = findViewById(R.id.hello_6_12);
        arrayTextView[6][13] = findViewById(R.id.hello_6_13);
        arrayTextView[6][14] = findViewById(R.id.hello_6_14);
        arrayTextView[6][15] = findViewById(R.id.hello_6_15);
        arrayTextView[6][16] = findViewById(R.id.hello_6_16);
        arrayTextView[6][17] = findViewById(R.id.hello_6_17);
        arrayTextView[6][18] = findViewById(R.id.hello_6_18);
        arrayTextView[6][19] = findViewById(R.id.hello_6_19);
        arrayTextView[6][20] = findViewById(R.id.hello_6_20);
        arrayTextView[6][21] = findViewById(R.id.hello_6_21);
        arrayTextView[6][22] = findViewById(R.id.hello_6_22);
        arrayTextView[6][23] = findViewById(R.id.hello_6_23);
        arrayTextView[6][24] = findViewById(R.id.hello_6_24);
        arrayTextView[6][25] = findViewById(R.id.hello_6_25);
        arrayTextView[6][26] = findViewById(R.id.hello_6_26);
        arrayTextView[6][27] = findViewById(R.id.hello_6_27);
        arrayTextView[6][28] = findViewById(R.id.hello_6_28);
        arrayTextView[6][29] = findViewById(R.id.hello_6_29);
        arrayTextView[6][30] = findViewById(R.id.hello_6_30);
        arrayTextView[6][31] = findViewById(R.id.hello_6_31);

        arrayTextView[7][0] = findViewById(R.id.hello_7_0);
        arrayTextView[7][1] = findViewById(R.id.hello_7_1);
        arrayTextView[7][2] = findViewById(R.id.hello_7_2);
        arrayTextView[7][3] = findViewById(R.id.hello_7_3);
        arrayTextView[7][4] = findViewById(R.id.hello_7_4);
        arrayTextView[7][5] = findViewById(R.id.hello_7_5);
        arrayTextView[7][6] = findViewById(R.id.hello_7_6);
        arrayTextView[7][7] = findViewById(R.id.hello_7_7);
        arrayTextView[7][8] = findViewById(R.id.hello_7_8);
        arrayTextView[7][9] = findViewById(R.id.hello_7_9);
        arrayTextView[7][10] = findViewById(R.id.hello_7_10);
        arrayTextView[7][11] = findViewById(R.id.hello_7_11);
        arrayTextView[7][12] = findViewById(R.id.hello_7_12);
        arrayTextView[7][13] = findViewById(R.id.hello_7_13);
        arrayTextView[7][14] = findViewById(R.id.hello_7_14);
        arrayTextView[7][15] = findViewById(R.id.hello_7_15);
        arrayTextView[7][16] = findViewById(R.id.hello_7_16);
        arrayTextView[7][17] = findViewById(R.id.hello_7_17);
        arrayTextView[7][18] = findViewById(R.id.hello_7_18);
        arrayTextView[7][19] = findViewById(R.id.hello_7_19);
        arrayTextView[7][20] = findViewById(R.id.hello_7_20);
        arrayTextView[7][21] = findViewById(R.id.hello_7_21);
        arrayTextView[7][22] = findViewById(R.id.hello_7_22);
        arrayTextView[7][23] = findViewById(R.id.hello_7_23);
        arrayTextView[7][24] = findViewById(R.id.hello_7_24);
        arrayTextView[7][25] = findViewById(R.id.hello_7_25);
        arrayTextView[7][26] = findViewById(R.id.hello_7_26);
        arrayTextView[7][27] = findViewById(R.id.hello_7_27);
        arrayTextView[7][28] = findViewById(R.id.hello_7_28);
        arrayTextView[7][29] = findViewById(R.id.hello_7_29);
        arrayTextView[7][30] = findViewById(R.id.hello_7_30);
        arrayTextView[7][31] = findViewById(R.id.hello_7_31);

        arrayTextView[8][0] = findViewById(R.id.hello_8_0);
        arrayTextView[8][1] = findViewById(R.id.hello_8_1);
        arrayTextView[8][2] = findViewById(R.id.hello_8_2);
        arrayTextView[8][3] = findViewById(R.id.hello_8_3);
        arrayTextView[8][4] = findViewById(R.id.hello_8_4);
        arrayTextView[8][5] = findViewById(R.id.hello_8_5);
        arrayTextView[8][6] = findViewById(R.id.hello_8_6);
        arrayTextView[8][7] = findViewById(R.id.hello_8_7);
        arrayTextView[8][8] = findViewById(R.id.hello_8_8);
        arrayTextView[8][9] = findViewById(R.id.hello_8_9);
        arrayTextView[8][10] = findViewById(R.id.hello_8_10);
        arrayTextView[8][11] = findViewById(R.id.hello_8_11);
        arrayTextView[8][12] = findViewById(R.id.hello_8_12);
        arrayTextView[8][13] = findViewById(R.id.hello_8_13);
        arrayTextView[8][14] = findViewById(R.id.hello_8_14);
        arrayTextView[8][15] = findViewById(R.id.hello_8_15);
        arrayTextView[8][16] = findViewById(R.id.hello_8_16);
        arrayTextView[8][17] = findViewById(R.id.hello_8_17);
        arrayTextView[8][18] = findViewById(R.id.hello_8_18);
        arrayTextView[8][19] = findViewById(R.id.hello_8_19);
        arrayTextView[8][20] = findViewById(R.id.hello_8_20);
        arrayTextView[8][21] = findViewById(R.id.hello_8_21);
        arrayTextView[8][22] = findViewById(R.id.hello_8_22);
        arrayTextView[8][23] = findViewById(R.id.hello_8_23);
        arrayTextView[8][24] = findViewById(R.id.hello_8_24);
        arrayTextView[8][25] = findViewById(R.id.hello_8_25);
        arrayTextView[8][26] = findViewById(R.id.hello_8_26);
        arrayTextView[8][27] = findViewById(R.id.hello_8_27);
        arrayTextView[8][28] = findViewById(R.id.hello_8_28);
        arrayTextView[8][29] = findViewById(R.id.hello_8_29);
        arrayTextView[8][30] = findViewById(R.id.hello_8_30);
        arrayTextView[8][31] = findViewById(R.id.hello_8_31);

        arrayTextView[9][0] = findViewById(R.id.hello_9_0);
        arrayTextView[9][1] = findViewById(R.id.hello_9_1);
        arrayTextView[9][2] = findViewById(R.id.hello_9_2);
        arrayTextView[9][3] = findViewById(R.id.hello_9_3);
        arrayTextView[9][4] = findViewById(R.id.hello_9_4);
        arrayTextView[9][5] = findViewById(R.id.hello_9_5);
        arrayTextView[9][6] = findViewById(R.id.hello_9_6);
        arrayTextView[9][7] = findViewById(R.id.hello_9_7);
        arrayTextView[9][8] = findViewById(R.id.hello_9_8);
        arrayTextView[9][9] = findViewById(R.id.hello_9_9);
        arrayTextView[9][10] = findViewById(R.id.hello_9_10);
        arrayTextView[9][11] = findViewById(R.id.hello_9_11);
        arrayTextView[9][12] = findViewById(R.id.hello_9_12);
        arrayTextView[9][13] = findViewById(R.id.hello_9_13);
        arrayTextView[9][14] = findViewById(R.id.hello_9_14);
        arrayTextView[9][15] = findViewById(R.id.hello_9_15);
        arrayTextView[9][16] = findViewById(R.id.hello_9_16);
        arrayTextView[9][17] = findViewById(R.id.hello_9_17);
        arrayTextView[9][18] = findViewById(R.id.hello_9_18);
        arrayTextView[9][19] = findViewById(R.id.hello_9_19);
        arrayTextView[9][20] = findViewById(R.id.hello_9_20);
        arrayTextView[9][21] = findViewById(R.id.hello_9_21);
        arrayTextView[9][22] = findViewById(R.id.hello_9_22);
        arrayTextView[9][23] = findViewById(R.id.hello_9_23);
        arrayTextView[9][24] = findViewById(R.id.hello_9_24);
        arrayTextView[9][25] = findViewById(R.id.hello_9_25);
        arrayTextView[9][26] = findViewById(R.id.hello_9_26);
        arrayTextView[9][27] = findViewById(R.id.hello_9_27);
        arrayTextView[9][28] = findViewById(R.id.hello_9_28);
        arrayTextView[9][29] = findViewById(R.id.hello_9_29);
        arrayTextView[9][30] = findViewById(R.id.hello_9_30);
        arrayTextView[9][31] = findViewById(R.id.hello_9_31);

        arrayTextView[10][0] = findViewById(R.id.hello_10_0);
        arrayTextView[10][1] = findViewById(R.id.hello_10_1);
        arrayTextView[10][2] = findViewById(R.id.hello_10_2);
        arrayTextView[10][3] = findViewById(R.id.hello_10_3);
        arrayTextView[10][4] = findViewById(R.id.hello_10_4);
        arrayTextView[10][5] = findViewById(R.id.hello_10_5);
        arrayTextView[10][6] = findViewById(R.id.hello_10_6);
        arrayTextView[10][7] = findViewById(R.id.hello_10_7);
        arrayTextView[10][8] = findViewById(R.id.hello_10_8);
        arrayTextView[10][9] = findViewById(R.id.hello_10_9);
        arrayTextView[10][10] = findViewById(R.id.hello_10_10);
        arrayTextView[10][11] = findViewById(R.id.hello_10_11);
        arrayTextView[10][12] = findViewById(R.id.hello_10_12);
        arrayTextView[10][13] = findViewById(R.id.hello_10_13);
        arrayTextView[10][14] = findViewById(R.id.hello_10_14);
        arrayTextView[10][15] = findViewById(R.id.hello_10_15);
        arrayTextView[10][16] = findViewById(R.id.hello_10_16);
        arrayTextView[10][17] = findViewById(R.id.hello_10_17);
        arrayTextView[10][18] = findViewById(R.id.hello_10_18);
        arrayTextView[10][19] = findViewById(R.id.hello_10_19);
        arrayTextView[10][20] = findViewById(R.id.hello_10_20);
        arrayTextView[10][21] = findViewById(R.id.hello_10_21);
        arrayTextView[10][22] = findViewById(R.id.hello_10_22);
        arrayTextView[10][23] = findViewById(R.id.hello_10_23);
        arrayTextView[10][24] = findViewById(R.id.hello_10_24);
        arrayTextView[10][25] = findViewById(R.id.hello_10_25);
        arrayTextView[10][26] = findViewById(R.id.hello_10_26);
        arrayTextView[10][27] = findViewById(R.id.hello_10_27);
        arrayTextView[10][28] = findViewById(R.id.hello_10_28);
        arrayTextView[10][29] = findViewById(R.id.hello_10_29);
        arrayTextView[10][30] = findViewById(R.id.hello_10_30);
        arrayTextView[10][31] = findViewById(R.id.hello_10_31);

        arrayTextView[11][0] = findViewById(R.id.hello_11_0);
        arrayTextView[11][1] = findViewById(R.id.hello_11_1);
        arrayTextView[11][2] = findViewById(R.id.hello_11_2);
        arrayTextView[11][3] = findViewById(R.id.hello_11_3);
        arrayTextView[11][4] = findViewById(R.id.hello_11_4);
        arrayTextView[11][5] = findViewById(R.id.hello_11_5);
        arrayTextView[11][6] = findViewById(R.id.hello_11_6);
        arrayTextView[11][7] = findViewById(R.id.hello_11_7);
        arrayTextView[11][8] = findViewById(R.id.hello_11_8);
        arrayTextView[11][9] = findViewById(R.id.hello_11_9);
        arrayTextView[11][10] = findViewById(R.id.hello_11_10);
        arrayTextView[11][11] = findViewById(R.id.hello_11_11);
        arrayTextView[11][12] = findViewById(R.id.hello_11_12);
        arrayTextView[11][13] = findViewById(R.id.hello_11_13);
        arrayTextView[11][14] = findViewById(R.id.hello_11_14);
        arrayTextView[11][15] = findViewById(R.id.hello_11_15);
        arrayTextView[11][16] = findViewById(R.id.hello_11_16);
        arrayTextView[11][17] = findViewById(R.id.hello_11_17);
        arrayTextView[11][18] = findViewById(R.id.hello_11_18);
        arrayTextView[11][19] = findViewById(R.id.hello_11_19);
        arrayTextView[11][20] = findViewById(R.id.hello_11_20);
        arrayTextView[11][21] = findViewById(R.id.hello_11_21);
        arrayTextView[11][22] = findViewById(R.id.hello_11_22);
        arrayTextView[11][23] = findViewById(R.id.hello_11_23);
        arrayTextView[11][24] = findViewById(R.id.hello_11_24);
        arrayTextView[11][25] = findViewById(R.id.hello_11_25);
        arrayTextView[11][26] = findViewById(R.id.hello_11_26);
        arrayTextView[11][27] = findViewById(R.id.hello_11_27);
        arrayTextView[11][28] = findViewById(R.id.hello_11_28);
        arrayTextView[11][29] = findViewById(R.id.hello_11_29);
        arrayTextView[11][30] = findViewById(R.id.hello_11_30);
        arrayTextView[11][31] = findViewById(R.id.hello_11_31);

        arrayTextView[12][0] = findViewById(R.id.hello_12_0);
        arrayTextView[12][1] = findViewById(R.id.hello_12_1);
        arrayTextView[12][2] = findViewById(R.id.hello_12_2);
        arrayTextView[12][3] = findViewById(R.id.hello_12_3);
        arrayTextView[12][4] = findViewById(R.id.hello_12_4);
        arrayTextView[12][5] = findViewById(R.id.hello_12_5);
        arrayTextView[12][6] = findViewById(R.id.hello_12_6);
        arrayTextView[12][7] = findViewById(R.id.hello_12_7);
        arrayTextView[12][8] = findViewById(R.id.hello_12_8);
        arrayTextView[12][9] = findViewById(R.id.hello_12_9);
        arrayTextView[12][10] = findViewById(R.id.hello_12_10);
        arrayTextView[12][11] = findViewById(R.id.hello_12_11);
        arrayTextView[12][12] = findViewById(R.id.hello_12_12);
        arrayTextView[12][13] = findViewById(R.id.hello_12_13);
        arrayTextView[12][14] = findViewById(R.id.hello_12_14);
        arrayTextView[12][15] = findViewById(R.id.hello_12_15);
        arrayTextView[12][16] = findViewById(R.id.hello_12_16);
        arrayTextView[12][17] = findViewById(R.id.hello_12_17);
        arrayTextView[12][18] = findViewById(R.id.hello_12_18);
        arrayTextView[12][19] = findViewById(R.id.hello_12_19);
        arrayTextView[12][20] = findViewById(R.id.hello_12_20);
        arrayTextView[12][21] = findViewById(R.id.hello_12_21);
        arrayTextView[12][22] = findViewById(R.id.hello_12_22);
        arrayTextView[12][23] = findViewById(R.id.hello_12_23);
        arrayTextView[12][24] = findViewById(R.id.hello_12_24);
        arrayTextView[12][25] = findViewById(R.id.hello_12_25);
        arrayTextView[12][26] = findViewById(R.id.hello_12_26);
        arrayTextView[12][27] = findViewById(R.id.hello_12_27);
        arrayTextView[12][28] = findViewById(R.id.hello_12_28);
        arrayTextView[12][29] = findViewById(R.id.hello_12_29);
        arrayTextView[12][30] = findViewById(R.id.hello_12_30);
        arrayTextView[12][31] = findViewById(R.id.hello_12_31);

        arrayTextView[13][0] = findViewById(R.id.hello_13_0);
        arrayTextView[13][1] = findViewById(R.id.hello_13_1);
        arrayTextView[13][2] = findViewById(R.id.hello_13_2);
        arrayTextView[13][3] = findViewById(R.id.hello_13_3);
        arrayTextView[13][4] = findViewById(R.id.hello_13_4);
        arrayTextView[13][5] = findViewById(R.id.hello_13_5);
        arrayTextView[13][6] = findViewById(R.id.hello_13_6);
        arrayTextView[13][7] = findViewById(R.id.hello_13_7);
        arrayTextView[13][8] = findViewById(R.id.hello_13_8);
        arrayTextView[13][9] = findViewById(R.id.hello_13_9);
        arrayTextView[13][10] = findViewById(R.id.hello_13_10);
        arrayTextView[13][11] = findViewById(R.id.hello_13_11);
        arrayTextView[13][12] = findViewById(R.id.hello_13_12);
        arrayTextView[13][13] = findViewById(R.id.hello_13_13);
        arrayTextView[13][14] = findViewById(R.id.hello_13_14);
        arrayTextView[13][15] = findViewById(R.id.hello_13_15);
        arrayTextView[13][16] = findViewById(R.id.hello_13_16);
        arrayTextView[13][17] = findViewById(R.id.hello_13_17);
        arrayTextView[13][18] = findViewById(R.id.hello_13_18);
        arrayTextView[13][19] = findViewById(R.id.hello_13_19);
        arrayTextView[13][20] = findViewById(R.id.hello_13_20);
        arrayTextView[13][21] = findViewById(R.id.hello_13_21);
        arrayTextView[13][22] = findViewById(R.id.hello_13_22);
        arrayTextView[13][23] = findViewById(R.id.hello_13_23);
        arrayTextView[13][24] = findViewById(R.id.hello_13_24);
        arrayTextView[13][25] = findViewById(R.id.hello_13_25);
        arrayTextView[13][26] = findViewById(R.id.hello_13_26);
        arrayTextView[13][27] = findViewById(R.id.hello_13_27);
        arrayTextView[13][28] = findViewById(R.id.hello_13_28);
        arrayTextView[13][29] = findViewById(R.id.hello_13_29);
        arrayTextView[13][30] = findViewById(R.id.hello_13_30);
        arrayTextView[13][31] = findViewById(R.id.hello_13_31);

        arrayTextView[14][0] = findViewById(R.id.hello_14_0);
        arrayTextView[14][1] = findViewById(R.id.hello_14_1);
        arrayTextView[14][2] = findViewById(R.id.hello_14_2);
        arrayTextView[14][3] = findViewById(R.id.hello_14_3);
        arrayTextView[14][4] = findViewById(R.id.hello_14_4);
        arrayTextView[14][5] = findViewById(R.id.hello_14_5);
        arrayTextView[14][6] = findViewById(R.id.hello_14_6);
        arrayTextView[14][7] = findViewById(R.id.hello_14_7);
        arrayTextView[14][8] = findViewById(R.id.hello_14_8);
        arrayTextView[14][9] = findViewById(R.id.hello_14_9);
        arrayTextView[14][10] = findViewById(R.id.hello_14_10);
        arrayTextView[14][11] = findViewById(R.id.hello_14_11);
        arrayTextView[14][12] = findViewById(R.id.hello_14_12);
        arrayTextView[14][13] = findViewById(R.id.hello_14_13);
        arrayTextView[14][14] = findViewById(R.id.hello_14_14);
        arrayTextView[14][15] = findViewById(R.id.hello_14_15);
        arrayTextView[14][16] = findViewById(R.id.hello_14_16);
        arrayTextView[14][17] = findViewById(R.id.hello_14_17);
        arrayTextView[14][18] = findViewById(R.id.hello_14_18);
        arrayTextView[14][19] = findViewById(R.id.hello_14_19);
        arrayTextView[14][20] = findViewById(R.id.hello_14_20);
        arrayTextView[14][21] = findViewById(R.id.hello_14_21);
        arrayTextView[14][22] = findViewById(R.id.hello_14_22);
        arrayTextView[14][23] = findViewById(R.id.hello_14_23);
        arrayTextView[14][24] = findViewById(R.id.hello_14_24);
        arrayTextView[14][25] = findViewById(R.id.hello_14_25);
        arrayTextView[14][26] = findViewById(R.id.hello_14_26);
        arrayTextView[14][27] = findViewById(R.id.hello_14_27);
        arrayTextView[14][28] = findViewById(R.id.hello_14_28);
        arrayTextView[14][29] = findViewById(R.id.hello_14_29);
        arrayTextView[14][30] = findViewById(R.id.hello_14_30);
        arrayTextView[14][31] = findViewById(R.id.hello_14_31);

        arrayTextView[15][0] = findViewById(R.id.hello_15_0);
        arrayTextView[15][1] = findViewById(R.id.hello_15_1);
        arrayTextView[15][2] = findViewById(R.id.hello_15_2);
        arrayTextView[15][3] = findViewById(R.id.hello_15_3);
        arrayTextView[15][4] = findViewById(R.id.hello_15_4);
        arrayTextView[15][5] = findViewById(R.id.hello_15_5);
        arrayTextView[15][6] = findViewById(R.id.hello_15_6);
        arrayTextView[15][7] = findViewById(R.id.hello_15_7);
        arrayTextView[15][8] = findViewById(R.id.hello_15_8);
        arrayTextView[15][9] = findViewById(R.id.hello_15_9);
        arrayTextView[15][10] = findViewById(R.id.hello_15_10);
        arrayTextView[15][11] = findViewById(R.id.hello_15_11);
        arrayTextView[15][12] = findViewById(R.id.hello_15_12);
        arrayTextView[15][13] = findViewById(R.id.hello_15_13);
        arrayTextView[15][14] = findViewById(R.id.hello_15_14);
        arrayTextView[15][15] = findViewById(R.id.hello_15_15);
        arrayTextView[15][16] = findViewById(R.id.hello_15_16);
        arrayTextView[15][17] = findViewById(R.id.hello_15_17);
        arrayTextView[15][18] = findViewById(R.id.hello_15_18);
        arrayTextView[15][19] = findViewById(R.id.hello_15_19);
        arrayTextView[15][20] = findViewById(R.id.hello_15_20);
        arrayTextView[15][21] = findViewById(R.id.hello_15_21);
        arrayTextView[15][22] = findViewById(R.id.hello_15_22);
        arrayTextView[15][23] = findViewById(R.id.hello_15_23);
        arrayTextView[15][24] = findViewById(R.id.hello_15_24);
        arrayTextView[15][25] = findViewById(R.id.hello_15_25);
        arrayTextView[15][26] = findViewById(R.id.hello_15_26);
        arrayTextView[15][27] = findViewById(R.id.hello_15_27);
        arrayTextView[15][28] = findViewById(R.id.hello_15_28);
        arrayTextView[15][29] = findViewById(R.id.hello_15_29);
        arrayTextView[15][30] = findViewById(R.id.hello_15_30);
        arrayTextView[15][31] = findViewById(R.id.hello_15_31);

        arrayTextView[16][0] = findViewById(R.id.hello_16_0);
        arrayTextView[16][1] = findViewById(R.id.hello_16_1);
        arrayTextView[16][2] = findViewById(R.id.hello_16_2);
        arrayTextView[16][3] = findViewById(R.id.hello_16_3);
        arrayTextView[16][4] = findViewById(R.id.hello_16_4);
        arrayTextView[16][5] = findViewById(R.id.hello_16_5);
        arrayTextView[16][6] = findViewById(R.id.hello_16_6);
        arrayTextView[16][7] = findViewById(R.id.hello_16_7);
        arrayTextView[16][8] = findViewById(R.id.hello_16_8);
        arrayTextView[16][9] = findViewById(R.id.hello_16_9);
        arrayTextView[16][10] = findViewById(R.id.hello_16_10);
        arrayTextView[16][11] = findViewById(R.id.hello_16_11);
        arrayTextView[16][12] = findViewById(R.id.hello_16_12);
        arrayTextView[16][13] = findViewById(R.id.hello_16_13);
        arrayTextView[16][14] = findViewById(R.id.hello_16_14);
        arrayTextView[16][15] = findViewById(R.id.hello_16_15);
        arrayTextView[16][16] = findViewById(R.id.hello_16_16);
        arrayTextView[16][17] = findViewById(R.id.hello_16_17);
        arrayTextView[16][18] = findViewById(R.id.hello_16_18);
        arrayTextView[16][19] = findViewById(R.id.hello_16_19);
        arrayTextView[16][20] = findViewById(R.id.hello_16_20);
        arrayTextView[16][21] = findViewById(R.id.hello_16_21);
        arrayTextView[16][22] = findViewById(R.id.hello_16_22);
        arrayTextView[16][23] = findViewById(R.id.hello_16_23);
        arrayTextView[16][24] = findViewById(R.id.hello_16_24);
        arrayTextView[16][25] = findViewById(R.id.hello_16_25);
        arrayTextView[16][26] = findViewById(R.id.hello_16_26);
        arrayTextView[16][27] = findViewById(R.id.hello_16_27);
        arrayTextView[16][28] = findViewById(R.id.hello_16_28);
        arrayTextView[16][29] = findViewById(R.id.hello_16_29);
        arrayTextView[16][30] = findViewById(R.id.hello_16_30);
        arrayTextView[16][31] = findViewById(R.id.hello_16_31);

        arrayTextView[17][0] = findViewById(R.id.hello_17_0);
        arrayTextView[17][1] = findViewById(R.id.hello_17_1);
        arrayTextView[17][2] = findViewById(R.id.hello_17_2);
        arrayTextView[17][3] = findViewById(R.id.hello_17_3);
        arrayTextView[17][4] = findViewById(R.id.hello_17_4);
        arrayTextView[17][5] = findViewById(R.id.hello_17_5);
        arrayTextView[17][6] = findViewById(R.id.hello_17_6);
        arrayTextView[17][7] = findViewById(R.id.hello_17_7);
        arrayTextView[17][8] = findViewById(R.id.hello_17_8);
        arrayTextView[17][9] = findViewById(R.id.hello_17_9);
        arrayTextView[17][10] = findViewById(R.id.hello_17_10);
        arrayTextView[17][11] = findViewById(R.id.hello_17_11);
        arrayTextView[17][12] = findViewById(R.id.hello_17_12);
        arrayTextView[17][13] = findViewById(R.id.hello_17_13);
        arrayTextView[17][14] = findViewById(R.id.hello_17_14);
        arrayTextView[17][15] = findViewById(R.id.hello_17_15);
        arrayTextView[17][16] = findViewById(R.id.hello_17_16);
        arrayTextView[17][17] = findViewById(R.id.hello_17_17);
        arrayTextView[17][18] = findViewById(R.id.hello_17_18);
        arrayTextView[17][19] = findViewById(R.id.hello_17_19);
        arrayTextView[17][20] = findViewById(R.id.hello_17_20);
        arrayTextView[17][21] = findViewById(R.id.hello_17_21);
        arrayTextView[17][22] = findViewById(R.id.hello_17_22);
        arrayTextView[17][23] = findViewById(R.id.hello_17_23);
        arrayTextView[17][24] = findViewById(R.id.hello_17_24);
        arrayTextView[17][25] = findViewById(R.id.hello_17_25);
        arrayTextView[17][26] = findViewById(R.id.hello_17_26);
        arrayTextView[17][27] = findViewById(R.id.hello_17_27);
        arrayTextView[17][28] = findViewById(R.id.hello_17_28);
        arrayTextView[17][29] = findViewById(R.id.hello_17_29);
        arrayTextView[17][30] = findViewById(R.id.hello_17_30);
        arrayTextView[17][31] = findViewById(R.id.hello_17_31);

        arrayTextView[18][0] = findViewById(R.id.hello_18_0);
        arrayTextView[18][1] = findViewById(R.id.hello_18_1);
        arrayTextView[18][2] = findViewById(R.id.hello_18_2);
        arrayTextView[18][3] = findViewById(R.id.hello_18_3);
        arrayTextView[18][4] = findViewById(R.id.hello_18_4);
        arrayTextView[18][5] = findViewById(R.id.hello_18_5);
        arrayTextView[18][6] = findViewById(R.id.hello_18_6);
        arrayTextView[18][7] = findViewById(R.id.hello_18_7);
        arrayTextView[18][8] = findViewById(R.id.hello_18_8);
        arrayTextView[18][9] = findViewById(R.id.hello_18_9);
        arrayTextView[18][10] = findViewById(R.id.hello_18_10);
        arrayTextView[18][11] = findViewById(R.id.hello_18_11);
        arrayTextView[18][12] = findViewById(R.id.hello_18_12);
        arrayTextView[18][13] = findViewById(R.id.hello_18_13);
        arrayTextView[18][14] = findViewById(R.id.hello_18_14);
        arrayTextView[18][15] = findViewById(R.id.hello_18_15);
        arrayTextView[18][16] = findViewById(R.id.hello_18_16);
        arrayTextView[18][17] = findViewById(R.id.hello_18_17);
        arrayTextView[18][18] = findViewById(R.id.hello_18_18);
        arrayTextView[18][19] = findViewById(R.id.hello_18_19);
        arrayTextView[18][20] = findViewById(R.id.hello_18_20);
        arrayTextView[18][21] = findViewById(R.id.hello_18_21);
        arrayTextView[18][22] = findViewById(R.id.hello_18_22);
        arrayTextView[18][23] = findViewById(R.id.hello_18_23);
        arrayTextView[18][24] = findViewById(R.id.hello_18_24);
        arrayTextView[18][25] = findViewById(R.id.hello_18_25);
        arrayTextView[18][26] = findViewById(R.id.hello_18_26);
        arrayTextView[18][27] = findViewById(R.id.hello_18_27);
        arrayTextView[18][28] = findViewById(R.id.hello_18_28);
        arrayTextView[18][29] = findViewById(R.id.hello_18_29);
        arrayTextView[18][30] = findViewById(R.id.hello_18_30);
        arrayTextView[18][31] = findViewById(R.id.hello_18_31);

        arrayTextView[19][0] = findViewById(R.id.hello_19_0);
        arrayTextView[19][1] = findViewById(R.id.hello_19_1);
        arrayTextView[19][2] = findViewById(R.id.hello_19_2);
        arrayTextView[19][3] = findViewById(R.id.hello_19_3);
        arrayTextView[19][4] = findViewById(R.id.hello_19_4);
        arrayTextView[19][5] = findViewById(R.id.hello_19_5);
        arrayTextView[19][6] = findViewById(R.id.hello_19_6);
        arrayTextView[19][7] = findViewById(R.id.hello_19_7);
        arrayTextView[19][8] = findViewById(R.id.hello_19_8);
        arrayTextView[19][9] = findViewById(R.id.hello_19_9);
        arrayTextView[19][10] = findViewById(R.id.hello_19_10);
        arrayTextView[19][11] = findViewById(R.id.hello_19_11);
        arrayTextView[19][12] = findViewById(R.id.hello_19_12);
        arrayTextView[19][13] = findViewById(R.id.hello_19_13);
        arrayTextView[19][14] = findViewById(R.id.hello_19_14);
        arrayTextView[19][15] = findViewById(R.id.hello_19_15);
        arrayTextView[19][16] = findViewById(R.id.hello_19_16);
        arrayTextView[19][17] = findViewById(R.id.hello_19_17);
        arrayTextView[19][18] = findViewById(R.id.hello_19_18);
        arrayTextView[19][19] = findViewById(R.id.hello_19_19);
        arrayTextView[19][20] = findViewById(R.id.hello_19_20);
        arrayTextView[19][21] = findViewById(R.id.hello_19_21);
        arrayTextView[19][22] = findViewById(R.id.hello_19_22);
        arrayTextView[19][23] = findViewById(R.id.hello_19_23);
        arrayTextView[19][24] = findViewById(R.id.hello_19_24);
        arrayTextView[19][25] = findViewById(R.id.hello_19_25);
        arrayTextView[19][26] = findViewById(R.id.hello_19_26);
        arrayTextView[19][27] = findViewById(R.id.hello_19_27);
        arrayTextView[19][28] = findViewById(R.id.hello_19_28);
        arrayTextView[19][29] = findViewById(R.id.hello_19_29);
        arrayTextView[19][30] = findViewById(R.id.hello_19_30);
        arrayTextView[19][31] = findViewById(R.id.hello_19_31);

        arrayTextView[20][0] = findViewById(R.id.hello_20_0);
        arrayTextView[20][1] = findViewById(R.id.hello_20_1);
        arrayTextView[20][2] = findViewById(R.id.hello_20_2);
        arrayTextView[20][3] = findViewById(R.id.hello_20_3);
        arrayTextView[20][4] = findViewById(R.id.hello_20_4);
        arrayTextView[20][5] = findViewById(R.id.hello_20_5);
        arrayTextView[20][6] = findViewById(R.id.hello_20_6);
        arrayTextView[20][7] = findViewById(R.id.hello_20_7);
        arrayTextView[20][8] = findViewById(R.id.hello_20_8);
        arrayTextView[20][9] = findViewById(R.id.hello_20_9);
        arrayTextView[20][10] = findViewById(R.id.hello_20_10);
        arrayTextView[20][11] = findViewById(R.id.hello_20_11);
        arrayTextView[20][12] = findViewById(R.id.hello_20_12);
        arrayTextView[20][13] = findViewById(R.id.hello_20_13);
        arrayTextView[20][14] = findViewById(R.id.hello_20_14);
        arrayTextView[20][15] = findViewById(R.id.hello_20_15);
        arrayTextView[20][16] = findViewById(R.id.hello_20_16);
        arrayTextView[20][17] = findViewById(R.id.hello_20_17);
        arrayTextView[20][18] = findViewById(R.id.hello_20_18);
        arrayTextView[20][19] = findViewById(R.id.hello_20_19);
        arrayTextView[20][20] = findViewById(R.id.hello_20_20);
        arrayTextView[20][21] = findViewById(R.id.hello_20_21);
        arrayTextView[20][22] = findViewById(R.id.hello_20_22);
        arrayTextView[20][23] = findViewById(R.id.hello_20_23);
        arrayTextView[20][24] = findViewById(R.id.hello_20_24);
        arrayTextView[20][25] = findViewById(R.id.hello_20_25);
        arrayTextView[20][26] = findViewById(R.id.hello_20_26);
        arrayTextView[20][27] = findViewById(R.id.hello_20_27);
        arrayTextView[20][28] = findViewById(R.id.hello_20_28);
        arrayTextView[20][29] = findViewById(R.id.hello_20_29);
        arrayTextView[20][30] = findViewById(R.id.hello_20_30);
        arrayTextView[20][31] = findViewById(R.id.hello_20_31);

        arrayTextView[21][0] = findViewById(R.id.hello_21_0);
        arrayTextView[21][1] = findViewById(R.id.hello_21_1);
        arrayTextView[21][2] = findViewById(R.id.hello_21_2);
        arrayTextView[21][3] = findViewById(R.id.hello_21_3);
        arrayTextView[21][4] = findViewById(R.id.hello_21_4);
        arrayTextView[21][5] = findViewById(R.id.hello_21_5);
        arrayTextView[21][6] = findViewById(R.id.hello_21_6);
        arrayTextView[21][7] = findViewById(R.id.hello_21_7);
        arrayTextView[21][8] = findViewById(R.id.hello_21_8);
        arrayTextView[21][9] = findViewById(R.id.hello_21_9);
        arrayTextView[21][10] = findViewById(R.id.hello_21_10);
        arrayTextView[21][11] = findViewById(R.id.hello_21_11);
        arrayTextView[21][12] = findViewById(R.id.hello_21_12);
        arrayTextView[21][13] = findViewById(R.id.hello_21_13);
        arrayTextView[21][14] = findViewById(R.id.hello_21_14);
        arrayTextView[21][15] = findViewById(R.id.hello_21_15);
        arrayTextView[21][16] = findViewById(R.id.hello_21_16);
        arrayTextView[21][17] = findViewById(R.id.hello_21_17);
        arrayTextView[21][18] = findViewById(R.id.hello_21_18);
        arrayTextView[21][19] = findViewById(R.id.hello_21_19);
        arrayTextView[21][20] = findViewById(R.id.hello_21_20);
        arrayTextView[21][21] = findViewById(R.id.hello_21_21);
        arrayTextView[21][22] = findViewById(R.id.hello_21_22);
        arrayTextView[21][23] = findViewById(R.id.hello_21_23);
        arrayTextView[21][24] = findViewById(R.id.hello_21_24);
        arrayTextView[21][25] = findViewById(R.id.hello_21_25);
        arrayTextView[21][26] = findViewById(R.id.hello_21_26);
        arrayTextView[21][27] = findViewById(R.id.hello_21_27);
        arrayTextView[21][28] = findViewById(R.id.hello_21_28);
        arrayTextView[21][29] = findViewById(R.id.hello_21_29);
        arrayTextView[21][30] = findViewById(R.id.hello_21_30);
        arrayTextView[21][31] = findViewById(R.id.hello_21_31);

        arrayTextView[22][0] = findViewById(R.id.hello_22_0);
        arrayTextView[22][1] = findViewById(R.id.hello_22_1);
        arrayTextView[22][2] = findViewById(R.id.hello_22_2);
        arrayTextView[22][3] = findViewById(R.id.hello_22_3);
        arrayTextView[22][4] = findViewById(R.id.hello_22_4);
        arrayTextView[22][5] = findViewById(R.id.hello_22_5);
        arrayTextView[22][6] = findViewById(R.id.hello_22_6);
        arrayTextView[22][7] = findViewById(R.id.hello_22_7);
        arrayTextView[22][8] = findViewById(R.id.hello_22_8);
        arrayTextView[22][9] = findViewById(R.id.hello_22_9);
        arrayTextView[22][10] = findViewById(R.id.hello_22_10);
        arrayTextView[22][11] = findViewById(R.id.hello_22_11);
        arrayTextView[22][12] = findViewById(R.id.hello_22_12);
        arrayTextView[22][13] = findViewById(R.id.hello_22_13);
        arrayTextView[22][14] = findViewById(R.id.hello_22_14);
        arrayTextView[22][15] = findViewById(R.id.hello_22_15);
        arrayTextView[22][16] = findViewById(R.id.hello_22_16);
        arrayTextView[22][17] = findViewById(R.id.hello_22_17);
        arrayTextView[22][18] = findViewById(R.id.hello_22_18);
        arrayTextView[22][19] = findViewById(R.id.hello_22_19);
        arrayTextView[22][20] = findViewById(R.id.hello_22_20);
        arrayTextView[22][21] = findViewById(R.id.hello_22_21);
        arrayTextView[22][22] = findViewById(R.id.hello_22_22);
        arrayTextView[22][23] = findViewById(R.id.hello_22_23);
        arrayTextView[22][24] = findViewById(R.id.hello_22_24);
        arrayTextView[22][25] = findViewById(R.id.hello_22_25);
        arrayTextView[22][26] = findViewById(R.id.hello_22_26);
        arrayTextView[22][27] = findViewById(R.id.hello_22_27);
        arrayTextView[22][28] = findViewById(R.id.hello_22_28);
        arrayTextView[22][29] = findViewById(R.id.hello_22_29);
        arrayTextView[22][30] = findViewById(R.id.hello_22_30);
        arrayTextView[22][31] = findViewById(R.id.hello_22_31);

        arrayTextView[23][0] = findViewById(R.id.hello_23_0);
        arrayTextView[23][1] = findViewById(R.id.hello_23_1);
        arrayTextView[23][2] = findViewById(R.id.hello_23_2);
        arrayTextView[23][3] = findViewById(R.id.hello_23_3);
        arrayTextView[23][4] = findViewById(R.id.hello_23_4);
        arrayTextView[23][5] = findViewById(R.id.hello_23_5);
        arrayTextView[23][6] = findViewById(R.id.hello_23_6);
        arrayTextView[23][7] = findViewById(R.id.hello_23_7);
        arrayTextView[23][8] = findViewById(R.id.hello_23_8);
        arrayTextView[23][9] = findViewById(R.id.hello_23_9);
        arrayTextView[23][10] = findViewById(R.id.hello_23_10);
        arrayTextView[23][11] = findViewById(R.id.hello_23_11);
        arrayTextView[23][12] = findViewById(R.id.hello_23_12);
        arrayTextView[23][13] = findViewById(R.id.hello_23_13);
        arrayTextView[23][14] = findViewById(R.id.hello_23_14);
        arrayTextView[23][15] = findViewById(R.id.hello_23_15);
        arrayTextView[23][16] = findViewById(R.id.hello_23_16);
        arrayTextView[23][17] = findViewById(R.id.hello_23_17);
        arrayTextView[23][18] = findViewById(R.id.hello_23_18);
        arrayTextView[23][19] = findViewById(R.id.hello_23_19);
        arrayTextView[23][20] = findViewById(R.id.hello_23_20);
        arrayTextView[23][21] = findViewById(R.id.hello_23_21);
        arrayTextView[23][22] = findViewById(R.id.hello_23_22);
        arrayTextView[23][23] = findViewById(R.id.hello_23_23);
        arrayTextView[23][24] = findViewById(R.id.hello_23_24);
        arrayTextView[23][25] = findViewById(R.id.hello_23_25);
        arrayTextView[23][26] = findViewById(R.id.hello_23_26);
        arrayTextView[23][27] = findViewById(R.id.hello_23_27);
        arrayTextView[23][28] = findViewById(R.id.hello_23_28);
        arrayTextView[23][29] = findViewById(R.id.hello_23_29);
        arrayTextView[23][30] = findViewById(R.id.hello_23_30);
        arrayTextView[23][31] = findViewById(R.id.hello_23_31);

        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 32; j++) {
                int[] color = colorCell(30);
                arrayTextView[i][j].setBackgroundColor(Color.rgb(color[0], color[1], 3));
            }
        }
        initUSBPort();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBar());
        }
    }

    private int hideSystemBar() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    int[] colorCell(int heat) {
        int lowestHeat = 0;
        int highestHeat = 60;
        int averHeat = (lowestHeat + highestHeat) / 2;
        if (heat > averHeat) {
            int r = 255;
            int g = (int) (255 * ((highestHeat - heat) / (float) averHeat));
            return (new int[] {r, g});
        }

        int g = 255;
        int r = (int) (255 * (heat / (float) averHeat));
        return (new int[] {r, g});
    }

    private String buffer = "";
    @Override
    public void onNewData(byte[] data) {
        buffer += new String(data);
        //Log.d("UART", "Received: " + new String(data));
        if (buffer.contains("#") && buffer.contains("!")) {
            try {
                int index_soc = buffer.indexOf("#");
                int index_eoc = buffer.indexOf("!");
                String arrayData = buffer.substring(index_soc + 1, index_eoc - 1);
                buffer = "";
                String[] numbers = arrayData.split("\\s");
                if (numbers.length == 768) {
                    for (int i = 0; i < 768; i++) {
                        int u = (i / 32);
                        int v = i % 32;
                        int[] color = colorCell(Integer.valueOf(numbers[i]));
                        arrayTextView[u][v].setBackgroundColor(Color.rgb(color[0], color[1], 3));
                        Log.d("UART", "Heat: " + Integer.toString(color[0]) + " " + Integer.toString(color[1]));
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onRunError(Exception e) {

    }
}
