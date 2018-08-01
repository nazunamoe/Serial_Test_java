package GUI;

import java.util.ArrayList;
import java.util.HashMap;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

class Func {
	
	ArrayList<SerialPort> MySerialPort = new ArrayList<SerialPort>();
	SerialPort SerialPort;
	String[] portNames;
	int number;
	
	public Func(String portName) {
		portNames = SerialPortList.getPortNames();
		for(int i=0; i<portNames.length; i++) {
			MySerialPort.add(new SerialPort(portNames[i]));
		}
		setPort();
		System.out.println("setuped"+portName);
	}
	
	public void setPort() {
		SerialPort = MySerialPort.get(number);
		System.out.println("Changed to : "+SerialPort.getPortName());
	}
	
	private void SendMessage(String message) {
		try {
		SerialPort.writeString(message);
		}catch(SerialPortException ex) {
		System.out.println(ex);
		}
	}
	
	public void Connect() {
		try {
			System.out.println("Connected : "+SerialPort.getPortName());
			SerialPort.openPort();
			SerialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
		}catch(SerialPortException ex) {
			System.out.println(ex);
		}
	}
	
	public void Disconnect() {
		try {
			System.out.println("Disconnected : "+SerialPort.getPortName());
			SerialPort.closePort();
		}catch(SerialPortException ex) {
			System.out.println(ex);
		}
	}
	
	public void Cold() {
		System.out.println("Command (AN) to : "+SerialPort.getPortName());
		SendMessage("(AN)");
	}
	
	public void Off() {
		System.out.println("Command (AO) to : "+SerialPort.getPortName());
		SendMessage("(AO)");
	}
}
