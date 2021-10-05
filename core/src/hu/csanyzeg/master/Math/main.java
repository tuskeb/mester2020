package hu.csanyzeg.master.Math;

public class main {


    public static void main(String[] args) {
        try {
            Ballistics ballistics = new Ballistics(30,30, 27);
            for(float i = 0; i < 100; i+=0.1) {
                System.out.println("t=" + i);
                System.out.println("x=" + ballistics.getXYbyTime(i, 0)[0]);
                System.out.println("y=" + ballistics.getXYbyTime(i, 0)[1]);
            }
            //System.out.println(ballistics.getAnglesByDeg()[0]);
            //System.out.println(ballistics.getAnglesByDeg()[1]);
            //ballistics.testFlight(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
