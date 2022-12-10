public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] result = new Planet[num];
        for (int i = 0; i < 3; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            result[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return result;
    }


    public static void main(String args[]) {
        double T = new Double(args[0]);
        double dt = new Double(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        String background = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double time = 0;
        int num = planets.length;
        while (time < T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int j = 0; j < num; j++){
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int z = 0; z < num; z++){
                planets[z].update(dt, xForces[z], yForces[z]);
            }
            StdDraw.picture(0, 0 ,background);
            for (Planet s: planets){
                s.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
    }
}

