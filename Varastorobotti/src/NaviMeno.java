import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.AstarSearchAlgorithm;
import lejos.robotics.pathfinding.FourWayGridMesh;
import lejos.robotics.pathfinding.NodePathFinder;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class NaviMeno implements Behavior{

	private volatile boolean suppressed = false;
	public static boolean nosta = false;

	Path polku = null;
	RegulatedMotor B;
	RegulatedMotor C;
	DifferentialPilot pilot;
	LineMap map;
	FourWayGridMesh navigaatioverkko;
	AstarSearchAlgorithm algoritmi;
	NodePathFinder polunEtsija;
	Navigator navi;

	public NaviMeno(RegulatedMotor B, RegulatedMotor C, DifferentialPilot pilo, LineMap map,
			FourWayGridMesh navigaatioverkko,
			AstarSearchAlgorithm algoritmi,NodePathFinder polunEtsija, Navigator navi ){
		this.B= B;
		this.C = C;
		this.pilot = pilot;
		this.map = map;
		this.navigaatioverkko = navigaatioverkko;
		this.algoritmi = algoritmi;
		this.polunEtsija= polunEtsija;
		this.navi = navi;
	}

	public NaviMeno(){

	}

	public static boolean getN(){
		return nosta;
	}


	public Path getPolku(){
		return polku;
	}

	public void setPolku(Path polku){
		this.polku = polku;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;


	//Navigointi ohjelma

		Main main = new Main();


		Waypoint laatikko = new Waypoint(main.getX(), main.getY());
		Pose meno = new Pose(0, 0, 0); 	// Lähtöpiste



		try { // Meno
			polku = polunEtsija.findRoute(meno, laatikko);

		} catch (DestinationUnreachableException e) { e.printStackTrace();}

			navi.setPath(polku);
			navi.followPath();
			navi.waitForStop();
			nosta = true;

			System.out.println("Perilla laatikonluona");
	}


	@Override
	public void suppress() {
		suppressed = true;
	}

}
