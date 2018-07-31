package GUI;

import java.util.HashMap;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

class Func {
	
	SerialPort MySerialPort;
	String[] portNames;
	String portName;
	HashMap<String,Boolean> status = new HashMap<String,Boolean>();
	
	public Func(String portName) {
		portNames = SerialPortList.getPortNames();
		for(int i=0; i<portNames.length; i++) {
			status.put(portNames[i], false);
		}
		setPort(portName);
		System.out.println("setuped"+portName);
	}
	
	public void setPort(String portName) {
		MySerialPort = new SerialPort(portName);
		this.portName = portName;
	}
	
	private void SendMessage(String message) {
		try {
		MySerialPort.writeString(message);
		}catch(SerialPortException ex) {
		System.out.println(ex);
		}
	}
	
	public void Connect() {
		try {
			System.out.println("Connect : "+portName);
			MySerialPort.openPort();
			MySerialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			status.put(portName,true);
		}catch(SerialPortException ex) {
			System.out.println(ex);
		}
	}
	
	public void Disconnect() {
		try {
			System.out.println("Disconnect"+portName);
			MySerialPort.closePort();
			status.put(portName,false);
		}catch(SerialPortException ex) {
			System.out.println(ex);
		}
	}
	
	public void Cold() {
		System.out.println("Cold"+portName);
		SendMessage("(AN)");
	}
	
	public void Off() {
		System.out.println("Off"+portName);
		SendMessage("(AO)");
	}
}
