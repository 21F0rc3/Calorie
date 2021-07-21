package Classes;

import GraphicInterface.Index;

import java.util.ArrayList;

public class Counter {

  private int cal;
  private float lip;
  private float carb;
  private float prot;

  public Counter() {
    cal = 0;
    lip = 0;
    carb = 0;
    prot = 0;
  }

  public void updateCounter(int cal, float lip, float carb, float prot) {
    this.cal += cal;
    this.lip += lip;
    this.carb += carb;
    this.prot += prot;
  }

  public void displayCounter() {
    System.out.println("Cal : "+cal);
    System.out.println("Lip : "+lip);
    System.out.println("Carb : "+carb);
    System.out.println("Prot : "+prot);
  }

  public int getCal() {
    return cal;
  }

  public float getLip() {
    return lip;
  }

  public float getCarb() {
    return carb;
  }

  public float getProt() {
    return prot;
  }
}