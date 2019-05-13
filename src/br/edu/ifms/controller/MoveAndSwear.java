package br.edu.ifms.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import br.edu.ifms.ev3.exercicios.GuidedDriver;
import br.edu.ifms.ev3.wrappers.TouchSensorWrapper;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class MoveAndSwear {
	
	
	private String[] names = {"audio11.wav", "audio22.wav"};
	private Integer[] angles = {180, 270, 90, 45};
	
	public MoveAndSwear() {
		TouchSensorWrapper touch = new TouchSensorWrapper(SensorPort.S1);
		EV3LargeRegulatedMotor me = new EV3LargeRegulatedMotor(MotorPort.C);
		EV3LargeRegulatedMotor md = new EV3LargeRegulatedMotor(MotorPort.B);
		GuidedDriver gd			  = new GuidedDriver(me, md);
		
		File f;
		Random rand = new Random();
		
		Sound.setVolume(100);
		
		while (Button.ESCAPE.isUp()) {
			if(touch.isPressed()) {
				String fName = names[rand.nextInt(2)];
				int angle = rand.nextInt(4);
				
				
				gd.move(0, -600f);
				Delay.msDelay(200);
				
				me.rotate(angles[angle], true);
				md.rotate(-angles[angle], true);
				System.out.println("bump! " + angles[angle]);
				Delay.msDelay(200);
				
				while(md.isMoving() || me.isMoving());
				
				/*try {
					f = new File(fName);
					Sound.playSample( f );
				}
				catch (Exception e) {
					System.out.println("algo deu errado!");
				}*/
				
			}
			
			gd.move(0, 600f);
		}
		
		
		/*if(!f.exists()) {
			System.out.println("arquivo nao encontrado");
			Delay.msDelay(100);
			System.exit(-1);
		}*/
		
		//int val = Sound.playSample( f );
		//System.out.println("res "+ val);
		
	}
	
	
	public static void main(String[] args) {
		
		new MoveAndSwear();
		
	}

}
