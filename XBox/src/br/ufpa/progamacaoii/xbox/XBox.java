
package br.ufpa.progamacaoii.xbox;

import br.ufpa.progamacaoii.xbox.data.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Moisés Felipe
 */
public abstract class XBox extends Device {
    
    /*
     * quantidade de armazenamento na memoria primaria
     */
    protected double memRam;
    
    /*
     * quantidade de armazenamento na memoria secundaria
     */
    protected double discoLocal;
    
    /*
     * quantidade de armazenamento na gpu
     */
    protected double gpu;
    
    /*
     * quantidade de nucleos na cpu
     */
    protected double qtNucleos;
    
    /*
     * quantidade de controles atualmente conectados
     */
    protected int qtControles;
    
    /*
     * quantidade maxima de controles;
     */
    protected static final int maxControles = 4;
    
    /*
     * kinect utilizado pelo xbox
     */
    protected Kinect kinect;
    /*
     * jogo que esta sendo rodado no momento
     */
    protected String jogoRodando;
    /*
     * jogos virtuais armazenados
     */
    protected List<String> jogos;
    /*
     * data de fabricacao do XBox.
     * nao pode ser constante devido a nao permissao de inicializacao do mesmo nos construtores.
     */
    protected Data fabricacao;
    
    public XBox(){        
        discoLocal = 100000;//100 Gb
        gpu = 1000;//1 Gb
        
        //verifica se existe um kinect
        if ( (JOptionPane.showConfirmDialog(null, "Seu Xbox possui Kinect?", 
                "Verificação!", JOptionPane.YES_NO_OPTION)) == 0 )
            kinect = new Kinect();
        else
            kinect = null;
        
        memRam = 4000;//4 Gb
        nome = "XBox 360";
        qtControles = 0;
        qtNucleos = 4;
        tipo = "eletronico";
        jogoRodando = "";
        this.fabricacao = new Data(1,1,2013);
        jogos = new ArrayList<>();
    }
    /*
     * construtor de copia
     */
    public XBox(XBox xbox){
        this.discoLocal = xbox.discoLocal;
        this.gpu = xbox.gpu;
        this.jogoRodando = xbox.jogoRodando;
        this.jogos = xbox.jogos;
        this.kinect = xbox.kinect;
        this.memRam = xbox.memRam;
        this.nome = xbox.nome;
        this.qtControles = xbox.qtControles;
        this.qtNucleos = xbox.qtNucleos;
        this.tipo = xbox.tipo;
        this.fabricacao = xbox.fabricacao;
    }
    public XBox(Data data){        
        this.fabricacao = new Data(1,1,2013);
    }
    /*
     * cada XBox vai rodar os seus jogos de modo diferente
     */
    public abstract void rodarJogo();
    
    /*
     * conecta a central da XBox Live
     */    
    public boolean conectaCentral() throws InterruptedException{
        
        Random gerador = new Random();
        
        JOptionPane pane = new JOptionPane("\nConectando a XBox Live.");
        final JDialog dialog = pane.createDialog(null, "Estabelecendo Conexão...");  
        dialog.setModal(true);
        //timer. Insere um tempo de vida para o JOptionPane
        Timer timer = new Timer(10 * 500, new ActionListener() {            
            public void actionPerformed(ActionEvent ev) {  
                dialog.dispose();
            }            
        });
        
        timer.setRepeats(true);
        timer.start();
        dialog.show();
        System.out.println("\nServidor conectado. Obtendo acesso a atualizações...");
        timer.stop();
        
        Thread.sleep(5000);
        
        if( gerador.nextInt(2) == 0 ) {
            JOptionPane.showMessageDialog(null, "\nDownload completado com sucesso.\nBase de dados local atualizada\nConectado a XBox Live!!\n",
                        "Banco de Dados Atualizado", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        else {
            pane = new JOptionPane("\nConexão abortada.\nExecutando Rollback...");
            final JDialog dialogAux = pane.createDialog(null, "Falha na Atualização");
            dialogAux.setModal(true);
            //timer. Insere um tempo de vida para o JOptionPane
            timer = new Timer(10 * 500, new ActionListener() {            
                public void actionPerformed(ActionEvent ev) {  
                    dialogAux.dispose();
                }            
            });

            timer.start();
            dialogAux.show();
            timer.stop();
            
            if( gerador.nextInt(2) == 0 )
                JOptionPane.showMessageDialog(null, "\nRollback executado com sucesso!!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "\nFalha ao executar Rollback. Base de dados local inconsistente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /*
     * roda a midia fisica
     */
    protected void rodaMidiaFisica(){
        
        if(jogoRodando.equals("") || jogoRodando == null){
            JOptionPane.showMessageDialog(null, "\nSem midia fisica\n","Midia Fisica",JOptionPane.ERROR_MESSAGE);
            return;
        }            
        
        JOptionPane pane = new JOptionPane("\nAtualmente executando o jogo "+jogoRodando+"\n");
        final JDialog dialog = pane.createDialog(null, "Midia Fisica");  
        dialog.setModal(true);
        //timer. Insere um tempo de vida para o JOptionPane
        Timer timer = new Timer(10 * 700, new ActionListener() {            
            public void actionPerformed(ActionEvent ev) {  
                dialog.dispose();
            }            
        });
        
        timer.setRepeats(true);
        timer.start();
        dialog.show();
        timer.stop();
    }
    /*
     * roda uma midia virtual
     */
    protected void rodaMidiaVirtual(){
        
        /*
         * verificacao de seguranca.
         * se nao houver nenhuma midia na memoria secundaria.
         */
        if(jogos.size() == 0)
            if(JOptionPane.showConfirmDialog(null, "\nSem midias virtuais no disco local.\n\nAdicionar?","Midias Virtuais",JOptionPane.YES_NO_OPTION) == 0 )
                if(!addMidiaVirtual())
                    return;        
        
        String texto = "";
        
        for(int i = 0; i<jogos.size(); i++)
            texto += (i+1)+". "+jogos.get(i) +'\n';//concatena os jogos armazenados localmente
        
        int jogo = Integer.parseInt( JOptionPane.showInputDialog(null, "\nSelecione um jogo:\n\n"+texto+"\n","Midias Virtuais",JOptionPane.INFORMATION_MESSAGE) );
        
        if(jogo <= jogos.size() && jogo > 0){
            jogoRodando = jogos.get(jogo-1);
            rodaMidiaFisica();//roda como se fosse uma midia fisica
        }
        else
            JOptionPane.showMessageDialog(null, "\nOpção inválida\n","Erro",JOptionPane.ERROR_MESSAGE);
    }
    /*
     * adiciona uma midia virtual
     */
    public boolean addMidiaVirtual(){
        String jogo = JOptionPane.showInputDialog(null, "\nInforme qual o jogo a ser armazenado\n","Adicionar Midia Virtual",JOptionPane.INFORMATION_MESSAGE);
        if(!jogo.equals("")){
            jogos.add(jogo);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "\nMidia Inválida\n","Adicionar Midia Virtual",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    public void usarKinect(){

        if (JOptionPane.showConfirmDialog(null, "\nUsar midia virtual?\n","Iniciando Kinect",JOptionPane.YES_NO_OPTION) == 0 ){
            
            JOptionPane pane = new JOptionPane("\nAtivando Kinect...\n", JOptionPane.DEFAULT_OPTION);
            
            final JDialog dialog = pane.createDialog(null, "Iniciando Kinect");  
            dialog.setModal(true);
            //timer. Insere um tempo de vida para o JOptionPane
            Timer timer = new Timer(10 * 100, new ActionListener() {
                public void actionPerformed(ActionEvent ev) {  
                    dialog.dispose();
                }            
            });

            timer.setRepeats(true);
            timer.start();
            dialog.show();
            timer.stop();
            
            if(kinect == null)
                kinect = new Kinect();
            
            kinect.setX(new Random().nextDouble());
            kinect.setY(new Random().nextDouble());
            kinect.setZ(new Random().nextDouble());

            kinect.usar(this);
            
            rodaMidiaVirtual();
            
            if(kinect.isAtivo())
                kinect.setAtivo(false);
        }
        else{
            
            JOptionPane pane = new JOptionPane("\nAtivando Kinect...\n", JOptionPane.DEFAULT_OPTION);
            final JDialog dialog = pane.createDialog(null, "Iniciando Kinect");  
            dialog.setModal(true);
            //timer. Insere um tempo de vida para o JOptionPane
            Timer timer = new Timer(10 * 100, new ActionListener() {
                public void actionPerformed(ActionEvent ev) {  
                    dialog.dispose();
                }            
            });

            timer.setRepeats(true);
            timer.start();
            dialog.show();
            timer.stop();
            
            String aux = JOptionPane.showInputDialog(null, "\nInforme qual jogo deve ser rodado com Kinect\n","Kinect",JOptionPane.DEFAULT_OPTION);
            
            if(!aux.equals("")){                
                
                if(kinect == null)
                    kinect = new Kinect();
            
                kinect.setX(new Random().nextDouble());
                kinect.setY(new Random().nextDouble());
                kinect.setZ(new Random().nextDouble());

                kinect.usar(this);
                
                jogoRodando = aux;
                rodaMidiaFisica();

                if(kinect.isAtivo())
                    kinect.setAtivo(false);
            }
        }
        
    }
        
}
