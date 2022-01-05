/**
 * @author Diyang Li
 * @create 2022-01-04 11:01 AM
 */
public class Planet {
    public double xxPos;//Its current x position
    public double yyPos; // Its current y position
    public double xxVel;//ts current velocity in the x direction
    public double yyVel; //Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; //The name of the file that corresponds to the image that depicts the planet
    private static double G = 6.67e-11;


    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    /**
     * Calculates the distance between two Planets
     *
     * @param p
     * @return
     */
    public double calcDistance(Planet p) {
        return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
    }

    /**
     * Describing the force exerted on this planet by the given planet
     *
     * @param p
     * @return
     */
    public double calcForceExertedBy(Planet p) {
        double dis = calcDistance(p);
        return (G * mass * p.mass) / (dis * dis);
    }

    /**
     * Describe the force exerted in the X and Y directions
     *
     * @param p
     * @return
     */
    public double calcForceExertedByX(Planet p) {
        double r = calcDistance(p);
        double dx = (p.xxPos - xxPos);
        return calcForceExertedBy(p) * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double r = calcDistance(p);
        double dy = (p.yyPos - yyPos);
        return calcForceExertedBy(p) * dy / r;
    }

    /**
     * Calculate the net X and net Y force exerted by all planets in that array upon the current Planet
     * Ignore any planet in the array that is equal to the current planet
     *
     * @param planets
     * @return
     */
    public double calcNetForceExertedByX(Planet[] planets) {
        double netForce = 0;
        for (Planet planet : planets) {
            if (!this.equals(planet)) {
                netForce += calcForceExertedByX(planet);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForce = 0;
        for (Planet planet : planets) {
            if (!this.equals(planet)) {
                netForce += calcForceExertedByY(planet);
            }
        }
        return netForce;
    }

    /**
     * Determines how much the forces exerted on the planet will cause that planet to accelerate
     * And the resulting change in the planet’s velocity and position in a small period of time dt
     *
     * @param dt
     * @param fX
     * @param fY
     */
    public void update(double dt, double fX, double fY) {
        double accX = fX / mass;
        double accY = fY / mass;
        xxVel += accX * dt;
        yyVel += accY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }

}
