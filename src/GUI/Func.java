package GUI;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

class Func {
	
	SerialPort MySerialPort[];
	String[] portNames;
	int number;
	String Log;
	
	public Func(String portName) {
		portNames = SerialPortList.getPortNames();
		MySerialPort = new SerialPort[portNames.length];
		for(int i=0; i<portNames.length; i++) {
			MySerialPort[i] = new SerialPort(portNames[i]);
		}
		setPort();
		System.out.println("setuped"+portName);
	}
	
	public void setPort() {
		System.out.println("Changed to : "+MySerialPort[number].getPortName());
		Log = "Changed to : "+MySerialPort[number].getPortName();
	}
	
	private void SendMessage(String message) {
		try {
			MySerialPort[number].writeString(message);
		}catch(SerialPortException ex) {
		System.out.println(ex);
		Log = ex.getPortName()+ex.getExceptionType();
		}
	}
	
	public void Connect() {
		try {
			System.out.println("Connected : "+MySerialPort[number].getPortName());
			Log = "Connected to : "+MySerialPort[number].getPortName();
			MySerialPort[number].openPort();
			MySerialPort[number].setParams(MySerialPort[number].BAUDRATE_9600,MySerialPort[number].DATABITS_8,MySerialPort[number].STOPBITS_1,MySerialPort[number].PARITY_NONE);
		}catch(SerialPortException ex) {
			System.out.println(ex);
			Log = ex.getPortName()+ex.getExceptionType();
		}
	}
	
	public void Disconnect() {
		try {
			System.out.println("Disconnected : "+MySerialPort[number].getPortName());
			Log = "Disconnected from : "+MySerialPort[number].getPortName();
			MySerialPort[number].closePort();
		}catch(SerialPortException ex) {
			System.out.println(ex);
			Log = ex.getPortName()+ex.getExceptionType();
		}
	}
	
	public void Cold() {
		System.out.println("Command (AN) to : "+MySerialPort[number].getPortName());
		Log = "Command (AN) to : "+MySerialPort[number].getPortName();
		SendMessage("(AN)");
	}
	
	public void Off() {
		System.out.println("Command (AO) to : "+MySerialPort[number].getPortName());
		Log = "Command (AO) to : "+MySerialPort[number].getPortName();
		SendMessage("(AO)");
	}
}
