import java.util.concurrent.locks.ReentrantLock;
public class Ejecutor implements Runnable{
    private String nombre;
    private static ReentrantLock cerrojo=new ReentrantLock();
    public Ejecutor(String nombre){
        this.nombre=nombre;

    }
    
    @Override
    public void run() {
        //ejecutar un contador que se incremente mil veces de uno en uno.
        //y que se muestre por pantalla cada 10 incrementos el valor del contador principal.
        Contador miContador= new Contador();
        for(int i=0;i<1000;i++){
            cerrojo.lock();
            Contador.incrementarContadorPrincipal();
            cerrojo.unlock();
            miContador.incrementarContadorIndividual();

            if (miContador.getContadorIndividual() % 10==0) {
                cerrojo.lock();
                System.out.println("El Ejecutor:" + this.nombre + "ha incrementado el contador Principal hasta:"+
                Contador.getContadorPrincipal()+"y su contador Individual es:"+miContador.getContadorIndividual());
                cerrojo.unlock();
            }
        }
    }
}
