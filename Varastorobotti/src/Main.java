
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.pathfinding.AstarSearchAlgorithm;
import lejos.robotics.pathfinding.FourWayGridMesh;
import lejos.robotics.pathfinding.NodePathFinder;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Main {


	private static double x;
	private static double y;


	public void setX(double x){
		Main.x = x;
	}

	public void setY(double y){
		Main.y = y;
	}

	public double getX(){
		return x;
	}
	public double getY(){
		return y;
		}



	public static void main (String[] args) throws IOException{

		Rectangle sKulmio = new Rectangle(-10,-10,160,160); // 0,0,x,y

		Line[] janat = new Line[8];{
		//suorakulmion sivut
		janat[0] = new Line(-10, 40,-10, 160); //x,y,x,y A
		janat[1] = new Line(-10, 160, 160, 160); // B
		janat[2] = new Line(160, 160, 160, -10); // C
		janat[3] = new Line(160, -10, 30, -10); // D
		// väliseinät
		janat[4] = new Line(-10, 100, 35, 100); // E
		janat[5] = new Line(-10, 50, 35, 50); // F
		janat[6] = new Line(160, 100, 120, 100); // G
		janat[7] = new Line(160, 50, 120, 50); // H
		}


		RegulatedMotor B = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor C = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor A = new EV3LargeRegulatedMotor(MotorPort.A);
		DifferentialPilot pilot = new	DifferentialPilot(2, 13 , B, C, true);
		LineMap map = new LineMap(janat, sKulmio);
		FourWayGridMesh navigaatioverkko = new FourWayGridMesh(map, 20, 10);
		AstarSearchAlgorithm algoritmi = new AstarSearchAlgorithm();
		NodePathFinder polunEtsija = new NodePathFinder(algoritmi, navigaatioverkko);
		Navigator navi = new Navigator(pilot);


		ServerSocket serveri = new ServerSocket(1111);
		System.out.println("accept");
		Socket s = serveri.accept();
		DataInputStream in = new DataInputStream(s.getInputStream());
		DataOutputStream out = new DataOutputStream(s.getOutputStream());


		x = in.readDouble();
		y = in.readDouble();



		Main m = new Main();

		m.setX(x);
		m.setY(y);


			System.out.println("Sijainti x:  "+ x);
			System.out.println("Sijainti y:  "+ y);
			Delay.msDelay(3000);

			Behavior b1 = new StopKäyttis(A, B, C, serveri, s, in, out);

			Behavior b3 = new NaviMeno(B, C, pilot, map, navigaatioverkko, algoritmi, polunEtsija, navi);
			Behavior b4 = new Nosto(A);
			Behavior b5 = new NaviPaluu(B, C, pilot, map, navigaatioverkko,algoritmi, polunEtsija, navi);
			Behavior b6 = new Lasku(A, B, C);


			Behavior[] bArray = {b1, b3, b4, b5, b6};

			Arbitrator arby = new Arbitrator(bArray);
			arby.go();


			in.close();
			out.close();
			s.close();
			serveri.close();

			}


	}



