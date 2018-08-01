package GUI;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

class Func {
	
	SerialPort MySerialPort[];
	String[] portNames;
	int number;
	
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
	}
	
	private void SendMessage(String message) {
		try {
			MySerialPort[number].writeString(message);
		}catch(SerialPortException ex) {
		System.out.println(ex);
		}
	}
	
	public void Connect() {
		try {
			System.out.println("Connected : "+MySerialPort[number].getPortName());
			MySerialPort[number].openPort();
			MySerialPort[number].setParams(MySerialPort[number].BAUDRATE_9600,MySerialPort[number].DATABITS_8,MySerialPort[number].STOPBITS_1,MySerialPort[number].PARITY_NONE);
		}catch(SerialPortException ex) {
			System.out.println(ex);
		}
	}
	
	public void Disconnect() {
		try {
			System.out.println("Disconnected : "+MySerialPort[number].getPortName());
			MySerialPort[number].closePort();
		}catch(SerialPortException ex) {
			System.out.println(ex);
		}
	}
	
	public void Cold() {
		System.out.println("Command (AN) to : "+MySerialPort[number].getPortName());
		SendMessage("(AN)");
	}
	
	public void Off() {
		System.out.println("Command (AO) to : "+MySerialPort[number].getPortName());
		SendMessage("(AO)");
	}
}
