package Classes;

import GraphicInterface.RefPane;

import java.util.ArrayList;

public class Refeicao implements gestorRefeicao {

  private int id;
  private String nome;

  public ArrayList<Alimento> alimentos;
  public Counter counterTotal;
  public Counter counter;

  private RefPane refPaneController;

  protected Refeicao(int id, String nome, Counter cT) {
    this.id = id;
    this.nome = nome;
    this.counterTotal = cT;
    this.counter = new Counter();
    this.alimentos = new ArrayList<>();
  }

  @Override
  public void addAlimento(Alimento a) {
    alimentos.add(a);

    this.counter.updateCounter(a.getCal(),a.getLip(),a.getCarb(),a.getProt());
    this.counterTotal.updateCounter(a.getCal(),a.getLip(),a.getCarb(),a.getProt());

    System.out.println(a.getNome()+"("+a.getQty()+") foi adicionado a "+this.nome);
  }

  @Override
  public void removeAlimento(Alimento a) {
    alimentos.remove(a);

    this.counter.updateCounter(-a.getCal(),-a.getLip(),-a.getCarb(),-a.getProt());
    this.counterTotal.updateCounter(-a.getCal(),-a.getLip(),-a.getCarb(),-a.getProt());

    System.out.println(a.getNome()+"("+a.getQty()+") foi removido da "+this.nome);
  }

  @Override
  public void clearRef() {
    for(Alimento a : alimentos) {
      removeAlimento(a);
    }
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public RefPane getRefPaneController() {
    return refPaneController;
  }

  public void setRefPaneController(RefPane r) {
    this.refPaneController = r;
  }
}