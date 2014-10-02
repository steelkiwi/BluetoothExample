package com.skd.bluetoothcontroller.connection;

import java.io.IOException;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class ServerSocketConnectionThread extends ConnectionThread {

	private BluetoothServerSocket mServerSocket;
	private SocketConnectionListener mListener;
	
	public ServerSocketConnectionThread(BluetoothAdapter adapter, SocketConnectionListener socketConnectionListener) {
		mListener = socketConnectionListener;
		try {
			mServerSocket = adapter.listenUsingRfcommWithServiceRecord(SDP_SERVICE_NAME, SERVICE_UUID);
		} catch (IOException e) {
			e.printStackTrace();
			mListener.onSocketConnectionFailed(e.getMessage());
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				BluetoothSocket socket = mServerSocket.accept();
				if(socket != null) {
					mListener.onSocketAcquired(socket);
//					mServerSocket.close();
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
				mListener.onSocketConnectionFailed(e.getMessage());
				break;
			}
		}
	}
	
	public void cancel(){
		try {
			mServerSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
