import java.util.ArrayList;
import java.util.List;

public class Equipo {
    List<Jugador> listado;

    public Equipo(){
        listado= new ArrayList<Jugador>();
    }

    public void agregar(Jugador jugador){
        listado.add(jugador);
    }

    public boolean editar(int id, Jugador jugador){
        int i= 0;
        int s= listado.size()-1;
        int c;

        while (i <= s){
            c= (i+s)/2;
            if(listado.get(c).getId()==id){
                listado.set(c,jugador);
                return true;
            }else if (id < listado.get(c).getId()){
                s= c-1;
            }else{
                i= c+1;
            }
        }
        return false;
    }

    public List<Jugador> todos(){
        return listado;

    }
}
