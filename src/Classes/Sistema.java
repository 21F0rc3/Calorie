package Classes;

import GraphicInterface.Index;

import java.io.*;
import java.sql.Ref;
import java.util.ArrayList;

public class Sistema {
    private int idRefCounter;
    private int idAlimCounter;

    private static Sistema sistema;

    private Counter counterTotal;
    private ArrayList<Alimento> listaAlimentos;
    private ArrayList<Refeicao> refeicoes;
    private Utilizador utilizador;

    private Sistema() {
        idRefCounter=0;
        idAlimCounter=0;
        counterTotal = new Counter();
        listaAlimentos = initLista();
        refeicoes = new ArrayList<>();
        utilizador = new Utilizador();
    }

    public static Sistema getInstance() {
        if(sistema==null) {
            sistema = new Sistema();
        }
        return sistema;
    }

    /**
     * Inicialia a lista de alimentos com o ficheiro com todos os alimentos registados
     */
    private ArrayList<Alimento> initLista() {
        try {
            File file = new File("C:\\Users\\Gabri\\OneDrive\\Ambiente de Trabalho\\Calorie\\src\\Assets\\alimentos.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            ArrayList<Alimento> lista = new ArrayList<>();

            int noLines = Integer.parseInt(br.readLine());
            for(int l=0; l<noLines; l++) {
                String[] alimTks = (br.readLine()).split(",");

                Alimento alimento = new Alimento(idAlimCounter++,alimTks[0],100,Integer.parseInt(alimTks[1]),Float.parseFloat(alimTks[2]),Float.parseFloat(alimTks[3]),Float.parseFloat(alimTks[4]));
                lista.add(alimento);
            }

            return lista;
        }catch (Exception e) {
            System.out.println("Sistema - getLista() : "+e.toString());
        }
        return null;
    }

    /**
     * Adiciona um painel de refeição ao sistema
     * @param nome - Nome do painel
     * @return id do painel criado
     */
    public int createRefeicao(String nome) {
        Refeicao refeicao = new Refeicao(idRefCounter++,nome,counterTotal);
        refeicoes.add(refeicao);
        System.out.println("Novo painel de refeição "+nome+" foi adicionado!");
        return idRefCounter-1;
    }

    /**
     * Procura por um painel no array de paineis
     * @param id - Id do painel que procuramos
     * @return O painel, null se não existir
     */
    public Refeicao searchRefeicao(int id) {
        for(Refeicao ref : refeicoes) {
            if(ref.getId() == id) {
                return ref;
            }
        }
        System.out.println("O painel de refeicao "+id+" não foi encontrado!");
        return null;
    }

    /**
     * Procura por um alimento na lista de alimentos
     * @param id - Id do alimento que procuramos
     * @return O alimento, null se não existir
     */
    public Alimento searchAlim(int id) {
        for(Alimento alim : listaAlimentos) {
            if(alim.getId() == id) {
                return alim;
            }
        }
        System.out.println("O alimento "+id+" não foi encontrado!");
        return null;
    }

    public ArrayList<Alimento> searchAlim(String search) {
        ArrayList<Alimento> searchResult = new ArrayList<>();
        for(Alimento alim : listaAlimentos) {
            if(alim.getNome().contains(search)) {
                searchResult.add(alim);
            }
        }
        return searchResult;
    }

    /**
     * Adiciona um alimento ao painel de refeicoes selecionado
     * @param refID - Id do painel
     * @param alimID - Id do alimento
     * @param qty - Quantidade do alimento (em g)
     */
    public void addAlimento(int refID, int alimID, int qty) {
        Refeicao ref = searchRefeicao(refID);
        Alimento alim = searchAlim(alimID);

        Alimento alimento = new Alimento(alim,qty);

        ref.addAlimento(alimento);
    }

    public Counter getCounterTotal() {
        return counterTotal;
    }

    public ArrayList<Alimento> getListaAlimentos() {
        return listaAlimentos;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void loadData(Index index) {
        Date today = new Date();

        try{
            File file = new File("C:\\Users\\Gabri\\OneDrive\\Ambiente de Trabalho\\Calorie\\src\\Assets\\Data\\"+today.toString()+".txt");
            if(!file.exists()) {
                throw new FileNotFoundException();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            int noRef = Integer.parseInt(br.readLine());
            for(int i=0; i<noRef; i++) {
                String[] refTks = br.readLine().split(",");
                int refId = index.getRefPaneController().adicionaRefeicao(refTks[0]);
                Refeicao ref = searchRefeicao(refId);

                int noAlim = Integer.parseInt(refTks[1]);
                for (int j = 0; j < noAlim; j++) {
                    String[] alimTks = br.readLine().split(",");
                    Alimento alimento = new Alimento(searchAlim(Integer.parseInt(alimTks[0])), Integer.parseInt(alimTks[1]));
                    ref.addAlimento(alimento);
                }
                Counter counter = ref.counter;
                ref.getRefPaneController().updateLabels(counter.getCal() + "", counter.getLip() + "", counter.getCarb() + "", counter.getProt() + "");
            }

            String[] userTks = br.readLine().split(",");
            index.getPerfilController().peso.setText(userTks[0]);
            index.getPerfilController().altura.setText(userTks[1]);

            index.getPerfilController().updatePeso();

            Counter counterTotal = sistema.getCounterTotal();
            index.getInicioController().updateCounterTotalLabels(counterTotal.getCal()+"",counterTotal.getLip()+"",counterTotal.getCarb()+"",counterTotal.getProt()+"");
        }catch (FileNotFoundException fe) {
            System.out.println("Sistema - loadData() : Novo dia! "+fe.toString());
            saveData(index);
        }catch (Exception e) {
            System.out.println("Sistema - loadData() : "+e.toString());
        }
    }

    public void saveData(Index index) {
        try{
            Date today = new Date();
            File file = new File("C:\\Users\\Gabri\\OneDrive\\Ambiente de Trabalho\\Calorie\\src\\Assets\\Data\\"+today.toString()+".txt");
            FileWriter fw = new FileWriter(file,false);

            int noRef = refeicoes.size();
            fw.write(noRef+"\n");
            for(Refeicao ref : refeicoes) {
                fw.write(ref.getNome()+","+ref.alimentos.size()+"\n");
                for(Alimento a : ref.alimentos) {
                    fw.write(a.getId()+","+a.getQty()+"\n");
                }
            }

            fw.write(utilizador.getPeso()+","+utilizador.getAltura());

            fw.close();

            System.out.println("Salvo com sucesso!");
        }catch (Exception e) {
            System.out.println("Sistema - saveData() : "+e.toString());
        }
    }

    public ArrayList<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void removeRefeicao(int id) {
        Refeicao ref = searchRefeicao(id);
        refeicoes.remove(ref);

        counterTotal.updateCounter(-ref.counter.getCal(),-ref.counter.getLip(),-ref.counter.getCarb(),-ref.counter.getProt());
    }
}
