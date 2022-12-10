
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet b){
        double xdistance = xxPos - b.xxPos;
        double xdistancesq = xdistance * xdistance;
        double ydistance = yyPos - b.yyPos;
        double ydistancesq = ydistance * ydistance;
        double distance = Math.sqrt(xdistancesq + ydistancesq);
        return distance;
    }
    public double calcForceExertedBy(Planet b){
        double G = 6.67 * Math.pow(10, -11);
        double m1 = mass;
        double m2 = b.mass;
        double r = calcDistance(b);
        double f = G*m1*m2/(r * r);
        return f;
    }
    public double calcForceExertedByX(Planet b){
        double dx = -(xxPos - b.xxPos);
        double f = calcForceExertedBy(b);
        double r = calcDistance(b);
        double fx = f * dx / r;
        return fx;
    }
    public double calcForceExertedByY(Planet b){
        double dy = -(yyPos - b.yyPos);
        double f = calcForceExertedBy(b);
        double r = calcDistance(b);
        double fy = f * dy / r;
        return fy;
    }
    public double calcNetForceExertedByX(Planet[] args){
        double netf = 0;
        for (Planet p: args) {
            if (!(equals(p))) {
                netf += calcForceExertedByX(p);
            }
        }
        return netf;
    }
    public double calcNetForceExertedByY(Planet[] args){
        double netf = 0;
        for (Planet p: args) {
            if (!(equals(p))) {
                netf += calcForceExertedByY(p);
            }
        }
        return netf;
    }
    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }
    public void draw(){
        String image = "image/";
        StdDraw.picture(xxPos, yyPos, image + imgFileName);
        StdDraw.show();
    }
}