package com.mycompany.soplanificadorrr;

public class Simulador {

    //atributos
    private final Datos datos;
    private final Cpu cpu;
    private AdminProcesosListos llegadaP;
    private final Cola colaProcesosListos;
    private final Cola colaProcesosTerminados;

    //constructores
    public Simulador(Datos datos) {
        this.datos = datos;
        this.colaProcesosListos = new Cola("Cola de procesos listos"); //Instancia e inicializa la cola de procesos listos
        this.llegadaP = new AdminProcesosListos(datos.colaProcesos, colaProcesosListos, datos.memoriaRAM);
        this.colaProcesosTerminados = new Cola("Cola de procesos terminados");
        this.cpu = new Cpu(datos.quantum, llegadaP, colaProcesosTerminados);
    }

    //Metodos
    public void iniciar() {
        //Si hay procesos, ejecuta los dos hilos llegadaP y cpu
        if (datos.numProcesos != 0) {
            System.out.println("Iniciando...");

            llegadaP.start();
            cpu.start();

            try {
                cpu.join();
            } catch (InterruptedException ex) {
            }

            colaProcesosTerminados.imprimirDatosFinales();
            System.out.println("Simulacion terminada.");

        } else {
            System.out.println("No hay procesos que hacer.");
        }
    }


}

