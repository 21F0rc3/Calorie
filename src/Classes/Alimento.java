package Classes;

public class Alimento {
  private int id;
  private String nome;
  private int qty;
  private int cal;
  private float lip;
  private float carb;
  private float prot;

  protected Alimento(int id, String nome, int qty, int cal, float lip, float carb, float prot) {
    this.id = id;
    this.nome = nome;
    this.qty = qty;
    this.cal = cal;
    this.lip = lip;
    this.carb = carb;
    this.prot = prot;
  }

  public Alimento(Alimento a, int qty) {
    this.id = a.getId();
    this.nome = a.getNome();
    this.qty = qty;
    this.cal = (a.getCal() * qty) / 100;
    this.lip = (a.getLip() * qty) / 100;
    this.carb = (a.getCarb() * qty) / 100;
    this.prot = (a.getProt() * qty) / 100;
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public int getQty() {
    return qty;
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

  public String toString() {
    return this.nome + " " + this.cal + " " + this.lip + " " + this.carb + " " + this.prot;
  }
}