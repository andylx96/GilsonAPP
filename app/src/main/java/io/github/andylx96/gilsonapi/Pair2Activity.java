package io.github.andylx96.gilsonapi;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Pair2Activity extends AppCompatActivity {
    private TextView connectStatus;
    private TextView connectStatus2;
    private Button connectButton;

    private static final String TAG = "ClientActivity";

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_FINE_LOCATION = 2;
    private static final String UUID_STRING = "5154474C-9000-0101-0001-000000000001";
    private static final String CHAR_ID = "5154474C-9000-0101-0004-000000000000";
    private UUID uuidService = UUID.fromString(UUID_STRING);
    private UUID uuidChar = UUID.fromString(CHAR_ID);

//    private ActivityClientBinding mBinding;

    private boolean mScanning;
    private Handler mHandler;
    private Handler mLogHandler;
    private Map<String, BluetoothDevice> mScanResults;

    private boolean mConnected;
    private boolean mEchoInitialized;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private ScanCallback mScanCallback;
    private BluetoothGatt mGatt;
    String scanResultString = "";
    private TextView gatt_text;
    private String messageString = null;
    private Button disconnectButton, readDataButton;


    @Override
    protected void onResume() {
        super.onResume();

        // Check low energy support
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            // Get a newer device
            Log.d(TAG, "No LE Support.");

            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair2);


        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();


//            connectStatus = (TextView) findViewById(R.id.connectStatus);
        connectStatus2 = (TextView) findViewById(R.id.connectionStatus2);
        connectButton = (Button) findViewById(R.id.connectDeviceButton);
        gatt_text = (TextView) findViewById(R.id.gatt_data);
        disconnectButton = (Button) findViewById(R.id.disconnectButton);
        readDataButton = (Button) findViewById(R.id.readDataButton);

        setButtonListener();
    }

    private void setButtonListener() {

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                connectStatus.setText("Connected");
                connectStatus2.setText("Starting Scan\nSearching For Devices...");
                startScan();

            }
        });
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopScan();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startScan() {
        if (!hasPermissions() || mScanning) {
            return;
        }
        // TODO start the scan
        List<ScanFilter> filters = new ArrayList<>();
//        ScanFilter scanFilter = new ScanFilter.Builder()
//                .setServiceUuid(new ParcelUuid(uuidService))
//                .build();
//        filters.add(scanFilter);

        ScanFilter filter = new ScanFilter.Builder().setDeviceAddress("00:07:80:A5:47:16").build();
        filters.add(filter);
//        00:07:80:A5:47:16

        ScanSettings settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                .build();

        mScanResults = new HashMap<>();
        mScanCallback = new BtleScanCallback(mScanResults);

        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        mBluetoothLeScanner.startScan(filters, settings, mScanCallback);
        mScanning = true;
        mHandler = new Handler();
        mHandler.postDelayed(this::stopScan, 100000);

    }

    private boolean hasPermissions() {
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            requestBluetoothEnable();
            return false;
        } else if (!hasLocationPermissions()) {
            requestLocationPermission();
            return false;
        }
        return true;
    }

    private void requestBluetoothEnable() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        Log.d(TAG, "Requested user enables Bluetooth. Try starting the scan again.");
    }

    private boolean hasLocationPermissions() {
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
    }

    private class BtleScanCallback extends ScanCallback {

        private Map<String, BluetoothDevice> mScanResults;

        BtleScanCallback(Map<String, BluetoothDevice> scanResults) {
            mScanResults = scanResults;
        }

        @Override
        public void onScanResult(int callbackType, ScanResult result) {

            addScanResult(result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            for (ScanResult result : results) {
                addScanResult(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.d(TAG, "BLE Scan Failed with code " + errorCode);
        }

        private void addScanResult(ScanResult result) {
            BluetoothDevice device = result.getDevice();
            String deviceAddress = device.getAddress();

            scanResultString += deviceAddress + "\n";

            mScanResults.put(deviceAddress, device);
//            connectDevice(device);
            stopScan();
        }
    }

    private void stopScan() {
        if (mScanning && mBluetoothAdapter != null && mBluetoothAdapter.isEnabled() && mBluetoothLeScanner != null) {
            mBluetoothLeScanner.stopScan(mScanCallback);
            scanComplete();
        }

        mScanCallback = null;
        mScanning = false;
        mHandler = null;
    }

    private void scanComplete() {
        if (mScanResults.isEmpty()) {

            connectStatus2.setText(connectStatus2.getText() + "\nNo Devices Found");

            return;
        }
        for (String deviceAddress : mScanResults.keySet()) {
            Log.d(TAG, "Found device: " + deviceAddress);
            connectStatus2.setText("Devices: \n" + scanResultString);


            connectDevice(mScanResults.get(deviceAddress));
        }

        connectStatus2.setText(connectStatus2.getText() + "\nScan Stopped");

    }

    //keep auto correct false to prevent data drain
    private void connectDevice(BluetoothDevice device) {
        GattClientCallback gattClientCallback = new GattClientCallback();
        mGatt = device.connectGatt(this, false, gattClientCallback);
    }

    private class GattClientCallback extends BluetoothGattCallback {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (status == BluetoothGatt.GATT_FAILURE) {


                Log.d(TAG, "Connection Failed");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        dbloadingInfo.setVisibility(View.VISIBLE);
//                        bar.setVisibility(View.INVISIBLE);
//                        loadingText.setVisibility(View.INVISIBLE);

                        connectStatus2.setText(connectStatus2.getText() + "\nGATT Failed");

                    }
                });

                disconnectGattServer();
                return;
            } else if (status != BluetoothGatt.GATT_SUCCESS) {


                Log.d(TAG, "Gatt NOT Sucessful");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        dbloadingInfo.setVisibility(View.VISIBLE);
//                        bar.setVisibility(View.INVISIBLE);
//                        loadingText.setVisibility(View.INVISIBLE);

                connectStatus2.setText(connectStatus2.getText() + "\nGATT NOT Sucessful");

                    }
                });

                disconnectGattServer();

                return;
            }
            if (newState == BluetoothProfile.STATE_CONNECTED) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        dbloadingInfo.setVisibility(View.VISIBLE);
//                        bar.setVisibility(View.INVISIBLE);
//                        loadingText.setVisibility(View.INVISIBLE);

                        connectStatus2.setText(connectStatus2.getText() + "\nConnected");

                        Log.d(TAG, "Connected");
                    }
                });

                mConnected = true;


                gatt.discoverServices();

                Log.i(TAG, "Attempting to start service discovery:" +
                        gatt.discoverServices());

            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        dbloadingInfo.setVisibility(View.VISIBLE);
////                        bar.setVisibility(View.INVISIBLE);
////                        loadingText.setVisibility(View.INVISIBLE);

                connectStatus2.setText(connectStatus2.getText() + "\nDis-Connected");

                Log.d(TAG, "DisConnection Sucessful");
//                    }
//                });
                disconnectGattServer();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if (status != BluetoothGatt.GATT_SUCCESS) {
                return;
            }
            BluetoothGattService service = gatt.getService(uuidService);
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuidChar);
//            characteristic;
            gatt.readCharacteristic(characteristic);
//            onCharacteristicRead();
//gatt.readDescriptor(characteristic.getDescriptor(uuidChar)
//            gatt.readCharacteristic(characteristic);
//            onCharacteristicWrite();
            gatt.setCharacteristicNotification(characteristic, true);

            readDataButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gatt.readCharacteristic(characteristic);
                }
            });

//            Log.d(TAG, "OnService Discovered");
//
//            Log.d(TAG, "msg" +characteristic.getValue().toString());
//
//            byte[] messageBytes = characteristic.getValue();
//            String messageString = null;
//            try {
//                messageString = new String(messageBytes, "UTF-8");
////                new String();
//            } catch (UnsupportedEncodingException e) {
//                Log.e(TAG, "Unable to convert message bytes to string");
//            }
//            Log.d(TAG, "Received message in Discover: " + messageString);
//            disconnectGattServer();

        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);

            Log.d(TAG, "OnService Discovered");

            Log.d(TAG, "msg" + characteristic.getValue().toString());

            byte[] messageBytes = characteristic.getValue();
            byte[] convertByte = new byte[]{messageBytes[1], messageBytes[0], messageBytes[3], messageBytes[2], messageBytes[5], messageBytes[4], messageBytes[7], messageBytes[6], messageBytes[9], messageBytes[8], messageBytes[11], messageBytes[10], messageBytes[13], messageBytes[12], messageBytes[15], messageBytes[14]};

            byte[] axByte = new byte[]{messageBytes[1], messageBytes[0]};

            byte[] ayByte = new byte[]{messageBytes[3], messageBytes[2]};

            byte[] azByte = new byte[]{messageBytes[5], messageBytes[4]};

            byte[] gxByte = new byte[]{messageBytes[7], messageBytes[6]};

            byte[] gyByte = new byte[]{messageBytes[9], messageBytes[8]};

            byte[] gzByte = new byte[]{messageBytes[11], messageBytes[9]};

            byte[] teByte = new byte[]{messageBytes[13], messageBytes[12]};

            byte[] naByte = new byte[]{messageBytes[15], messageBytes[14]};

            byte[] test = new byte[]{messageBytes[0]};

            float ax = (short) (((axByte[0] & 0xFF) << 8) | (axByte[1] & 0xFF));

            float ay = (short) (((ayByte[0] & 0xFF) << 8) | (ayByte[1] & 0xFF));

            float az = (short) (((azByte[0] & 0xFF) << 8) | (azByte[1] & 0xFF));

            float gx = (short) (((gxByte[0] & 0xFF) << 8) | (gxByte[1] & 0xFF));

            float gy = (short) (((gyByte[0] & 0xFF) << 8) | (gyByte[1] & 0xFF));

            float gz = (short) (((gzByte[0] & 0xFF) << 8) | (gzByte[1] & 0xFF));

            float te = (short) (((teByte[0] & 0xFF) << 8) | (teByte[1] & 0xFF));


            messageString = new String(test);
//                new String();
            Log.d(TAG, "Received message in On Read: " + ax + "\nLength" + messageBytes.length);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                        dbloadingInfo.setVisibility(View.VISIBLE);
//                        bar.setVisibility(View.INVISIBLE);
//                        loadingText.setVisibility(View.INVISIBLE);

                    gatt_text.setText(gatt_text.getText() + "\nAX: " + ax/32768*8 + " AY: " + ay/32768*8 + " AZ: " + az/32768*8
                            + "\nGX: " + gx/32768*2000 + " GY: " + gy/32768*2000 + " GZ: " + gz/32768*2000 + "\nte: " + te + "\n");

                    Log.d(TAG, "Connected");
                }
            });


//            disconnectGattServer();
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            byte[] messageBytes = characteristic.getValue();
            String messageString = null;
            try {
                messageString = new String(messageBytes, "UTF-8");
//                new String();
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Unable to convert message bytes to string");
            }
            Log.d(TAG, "Received message on Char Changed: " + messageString);
        }


    }

    public void disconnectGattServer() {
        mConnected = false;
        if (mGatt != null) {
            mGatt.disconnect();
            mGatt.close();
        }
        Log.d(TAG, "DC");
    }


//    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//        super.onServicesDiscovered(gatt, status);
//        if (status != BluetoothGatt.GATT_SUCCESS) {
//            return;
//        }
//        BluetoothGattService service = gatt.getService(uuidService);
//        BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuidChar);
//        characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
//        mInitialized = gatt.setCharacteristicNotification(characteristic, true);
//    }

//    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
//        super.onCharacteristicChanged(gatt, characteristic);
//        byte[] messageBytes = characteristic.getValue();
//        String messageString = null;
//        try {
//            messageString = new String(bytes, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            Log.e(TAG, "Unable to convert message bytes to string");
//        }
//        Log.d("Received message: " + messageString);
//    }

}

