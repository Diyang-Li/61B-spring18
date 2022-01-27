import edu.princeton.cs.introcs.In;

import javax.transaction.xa.Xid;
import java.awt.*;

/**
 * @author Diyang Li
 * @create 2022-01-04 3:58 PM
 */
public class NBody {
    /**
     * Return a double corresponding to the radius of the universe in that file
     *
     * @param file
     * @return
     */
    public static double readRadius(String file) {
        double radius = 0;
        In in = new In(file);
        in.readInt();
        radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[i] = planet;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        // Universe radius
        double radius = readRadius(filename);
        // Planet array
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Planet p: planets){
            p.draw();
        }

        // Creating an Animation
        StdDraw.enableDoubleBuffering();
        double timeStart ;
        for (timeStart = 0; timeStart<=T; timeStart+=dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i=0; i<planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int j = 0; j<planets.length;j++){
                planets[j].update(dt, xForces[j], yForces[j]);
            }
//            StdDraw.clear();
            StdDraw.picture(0,0, "./images/starfield.jpg");
            for (Planet p: planets){
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(1);
        }


        StdDraw.show();

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
