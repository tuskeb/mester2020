package hu.csanyzeg.master.Math;

public class main {


    public static void main(String[] args) {
        try {
            Ballistics2 ballistics = new Ballistics2(10, 0.2f, 2f, 1f);
            for(float i = 0; i <= ballistics.get_end(); i+=0.1) {
                //System.out.println("x=" + i + "\t" + "y=" + ballistics.y(i));
                System.out.println(ballistics.get_position(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
