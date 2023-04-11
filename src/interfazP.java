import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class interfazP extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JTextField txtID;
    private JTextField txtCedula;
    private JTextField txtTiempo;
    private JButton btnAgregar;
    private JTextArea mostrarAgregados;
    private JButton btnDatosPredef;
    private JTextArea txtDatosPredef;
    private JButton btnHistorial;
    private JButton btnUltProceso;
    private JTextArea txtHistorial;
    private JTextField txtValue;
    private JButton btnCambiar;
    private JTextArea cambiarValor;
    private JTextArea txtRoundRobin;
    private JButton ejecutarRoundRobinButton;
    private JButton EJECUTARButton;
    private JTextArea txtDatos;
    private JButton MOSTRARDATOSButton;
    private JButton eliminarHistorialButton;
    //private JButton buttonOK;
    //private JButton buttonCancel;
    private int cuanto= 20;
    Queue<proceso> procesos= new LinkedList<>();
    Stack <proceso> historial= new Stack<proceso>();
    public interfazP() {
        setContentPane(contentPane);
        setModal(true);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // procesosPredef();
                procesos.add(new proceso(txtID.getText(), Integer.parseInt(txtCedula.getText()), Integer.parseInt(txtTiempo.getText())));
                procesos.poll();
                mostrarDatos();
            }
        });
        btnCambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarValor.setText(txtValue.getText());
                System.out.println(cambiarValor.getText());
            }
        });
        ejecutarRoundRobinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proceso pr = new proceso("",0,0);
                int tiempoT=0;
                int tiempo1=0;
                int conmutaciones=0;
                if(procesos.size()>1){
                    while(procesos.size()>1){
                        pr= procesos.poll();
                        txtRoundRobin.append("\nTiempo "+tiempo1+": "+pr.getId()+" entra en ejecución.");
                        tiempoT= pr.getTiempo()-cuanto;

                        if (tiempoT<=0){
                            tiempo1=tiempo1+pr.getTiempo();
                            txtRoundRobin.append("\nTiempo "+tiempo1+": "+pr.getId()+" finaliza la ejecución.");
                            historial.push(pr);
                        }
                        else{
                            tiempo1=tiempo1+cuanto;
                            conmutaciones++;
                            txtRoundRobin.append("\nTiempo "+tiempo1+": "+pr.getId()+" se conmuta. Pendiente por ejecutar "+tiempoT+" ms.");
                            pr.setTiempo(tiempoT);
                            procesos.offer(pr);//añade otra vez a la cola
                        }
                    }
                }
                if (procesos.size()==1){
                    while(procesos.size()>0){
                        pr= procesos.poll();
                        txtRoundRobin.append("\nTiempo "+tiempo1+": "+pr.getId()+" entra en ejecución.");
                        tiempoT= pr.getTiempo()-cuanto;
                        if (tiempoT<=0){
                            tiempo1=tiempo1+pr.getTiempo();
                            txtRoundRobin.append("\nTiempo "+tiempo1+": "+pr.getId()+" finaliza la ejecución.");
                            historial.push(pr);
                        }
                        else{
                            tiempo1=tiempo1+cuanto;
                            //conmutaciones++;
                            txtRoundRobin.append("\nTiempo "+tiempo1+": "+pr.getId()+" continúa ejecutándose");
                            pr.setTiempo(tiempoT);
                            procesos.offer(pr);
                        }

                    }
                }
                txtRoundRobin.append("------ESTADÍSTICAS GENERALES-----\n");
                txtRoundRobin.append("Total tiempo de ejecución de todos los procesos: "+tiempo1+" ms.\n");
                txtRoundRobin.append("Total de conmutaciones: "+conmutaciones);
            }
        });
        btnHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(historial);
                String cedula;
                //String tiempo;
                String cadena=" ";
                for (proceso p:historial) {
                    cedula=String.valueOf(p.getCedula());
                    //tiempo= String.valueOf(p.getTiempo());
                    cadena = cadena + "\n" + p.getId() + ", " + cedula;
                    System.out.println(cadena);
                }
                txtHistorial.setText(cadena);
            }
        });
        btnDatosPredef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesosPredef();
                mostrarDatos();

            }
        });

        MOSTRARDATOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDatos.setText("Nombres: Kristiany Cerón, Camila Vega\nCedulas: 1726349580, 1721436507\nID Banner: A00087627, A00081787");
            }
        });
        btnUltProceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtHistorial.setText(historial.peek().toString());
            }
        });
        eliminarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesos.clear();
                txtHistorial.setText(null);
            }
        });
    }

    private void mostrarDatos(){
        String cedula;
        String tiempo;
        String cadena=" ";
        for (proceso p:procesos) {
            cedula=String.valueOf(p.getCedula());
            tiempo= String.valueOf(p.getTiempo());
            cadena = cadena + "\n" + p.getId() + ", " + cedula + ", " + tiempo;
            System.out.println(cadena);
            txtDatosPredef.setText(cadena);
            mostrarAgregados.setText(cadena);
            //System.out.println(procesos.element().getId());
        }
    }
        private void procesosPredef(){
            proceso proceso1 = new proceso("P1", 1002856059, 100);
            proceso proceso2 = new proceso("P2", 1714196743, 15);
            proceso proceso3 = new proceso("P3", 1456756888, 40);
            proceso proceso4 = new proceso("P4", 1234556584, 30);
            proceso proceso5 = new proceso("P5", 1323567987, 18);
            proceso proceso6 = new proceso("P6", 1454536584, 45);
            procesos.add(proceso1);
            procesos.add(proceso2);
            procesos.add(proceso3);
            procesos.add(proceso4);
            procesos.add(proceso5);
            procesos.add(proceso6);


}

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        interfazP dialog = new interfazP();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
