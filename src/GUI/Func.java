package GUI;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

class Func {
	
	SerialPort MySerialPort;
	
	public Func() {
		System.out.println("setup");
		MySerialPort = new SerialPort("COM3");
		try {
			MySerialPort.openPort();
			MySerialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
		}catch(SerialPortException ex) {
			System.out.println(ex);
			}
	}
	
	public void Cold() {
		System.out.println("Cold");
		try {
			MySerialPort.writeString("(AN)");
		}catch(SerialPortException ex) {
			System.out.println(ex);
		}
	}
	
	public void Off() {
		System.out.println("Off");
		try {
			MySerialPort.writeString("(AO)");
		}catch(SerialPortException ex) {
			System.out.println(ex);
		}
	}
}
