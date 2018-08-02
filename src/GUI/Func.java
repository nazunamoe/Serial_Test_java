package GUI;

import GUI.Serial.SerialPortReader;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

class Func {
	
	SerialPort MySerialPort[];
	String[] portNames;
	int number;
	String Log;
	static String CommandByte="";
	static String Command1;
	static String Command2;
	
	public Func(String portName) {
		portNames = SerialPortList.getPortNames();
		MySerialPort = new SerialPort[portNames.length];
		for(int i=0; i<portNames.length; i++) {
			MySerialPort[i] = new SerialPort(portNames[i]);
		}
		setPort(0);
	}
	
	public static void getCommand(String input) {
		System.out.println("COMMAND1 : "+input);
		if(input != null) {
			if(input.contains("TH1")) {
				Command1 = input;
				System.out.println("COMMAND1 : "+input);
			}else if(input.contains("TH2")) {	
				Command2 = input;
				System.out.println("COMMAND2 : "+input);
			}else {

			}
		}
	}
	
	public void changePort(int num) {
		if(MySerialPort[number].isOpened()) {
			try {
				MySerialPort[number].removeEventListener();
				MySerialPort[number].closePort();
				System.out.println("Removed listener from "+MySerialPort[number].getPortName());
			} catch(SerialPortException ex) {
				System.out.println(ex);
				Log = ex.getPortName()+ex.getExceptionType();
			}
		}
		setPort(num);
	}
	
	public void setPort(int num) {
		this.number = num;
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
        int mask = SerialPort.MASK_RXCHAR;
		try {
			System.out.println("Connected : "+MySerialPort[number].getPortName());
			Log = "Connected to : "+MySerialPort[number].getPortName();
			MySerialPort[number].openPort();
			MySerialPort[number].setParams(MySerialPort[number].BAUDRATE_9600,MySerialPort[number].DATABITS_8,MySerialPort[number].STOPBITS_1,MySerialPort[number].PARITY_NONE);
			MySerialPort[number].setEventsMask(mask);
			MySerialPort[number].addEventListener(new SerialPortReader(MySerialPort[number]));
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
