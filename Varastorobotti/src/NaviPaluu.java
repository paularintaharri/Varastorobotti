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

public class NaviPaluu implements Behavior {

	private volatile boolean suppressed = false;
	public static boolean laske = false;

	RegulatedMotor B;
	RegulatedMotor C;
	DifferentialPilot pilot;
	LineMap map;
	FourWayGridMesh navigaatioverkko;
	AstarSearchAlgorithm algoritmi;
	NodePathFinder polunEtsija;
	Navigator navi;

	public static boolean getL(){
		return laske;
	}

	public NaviPaluu(RegulatedMotor B, RegulatedMotor C,
			DifferentialPilot pilot, LineMap map,
			FourWayGridMesh navigaatioverkko,
			AstarSearchAlgorithm algoritmi,NodePathFinder polunEtsija, Navigator navi ){

		this.B = B;
		this.C = C;
		this.pilot = pilot;
		this.map = map;
		this.navigaatioverkko = navigaatioverkko;
		this.algoritmi = algoritmi;
		this.polunEtsija= polunEtsija;
		this.navi = navi;
	}

	@Override
	public boolean takeControl() {

	return Nosto.getP();
}

	@Override
	public void action() {
		suppressed = false;


	//Navigointi ohjelma

		Main main= new Main();
		float p1 = (float) main.getX();
		float p2 = (float) main.getY();

		Pose paluu = new Pose(p1, p2, 0);
		Waypoint laatikko = new Waypoint(0,0);
		NaviMeno meno = new NaviMeno();
		Path polku = meno.getPolku();

		try { // Meno
			polku = polunEtsija.findRoute(paluu, laatikko);
		} catch (DestinationUnreachableException e) { e.printStackTrace();}

			navi.setPath(polku);
			navi.followPath();
			navi.waitForStop();
			laske = true;
			System.out.println("Perillä lähdössä");



	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
