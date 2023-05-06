package mx.salones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Salon {
    private String edificio_org;
    private String edificio_fmt;
    private String dia_org;
    private String dia_fmt;
    private String piso_org;
    private String piso_fmt;
    private String no_salon_org;
    private String no_salon_fmt;
    private String hora_inicio;
    private String hora_fin;
    public static final long HOUR = 3600*1000; // in milli-seconds.

    public Salon(String info_comp, String dia, String hora){
        String[] components = info_comp.split("");
        this.edificio_org = components[0];
        this.edificio_fmt = "Edificio " + components[0];
        this.piso_org = components[1];
        switch (components[1]){
            case "1":
                this.piso_fmt = "Primer ";
                break;
            case "2":
                this.piso_fmt = "Segundo ";
                break;
            case "3":
                this.piso_fmt = "Tercer ";
                break;
            case "4":
                this.piso_fmt = "Cuarto ";
                break;
            default:
                this.piso_fmt = "?? ";
                break;
        }

        this.piso_fmt += "piso";
        this.no_salon_org = info_comp;
        this.no_salon_fmt = components[2] + components[3];

        switch (dia){
            case "1":
                this.dia_fmt = "Lunes";
                break;
            case "2":
                this.dia_fmt = "Martes";
                break;
            case "3":
                this.dia_fmt = "Miercoles";
                break;
            case "4":
                this.dia_fmt = "Jueves";
                break;
            case "5":
                this.dia_fmt = "Viernes";
                break;
        }

        String tmp_time;
        String[] time_parts = hora.split("\\.");
        String hora_entero = time_parts[0];

        tmp_time = String.format("%1$2s", hora_entero).replace(' ', '0');
        tmp_time += ":";
        tmp_time += time_parts.length == 2 ? "30" : "00";
        this.hora_inicio = tmp_time;

        int tmp_h = Integer.parseInt(this.hora_inicio.split(":")[0]);
        int tmp_m = Integer.parseInt(this.hora_inicio.split(":")[1]);
        if (tmp_m == 30){
            this.hora_fin = String.format("%1$2s", tmp_h + 2).replace(' ', '0') + ":00";
        }else {
            this.hora_fin = String.format("%1$2s", tmp_h + 1).replace(' ', '0') + ":30";
        }
    }

    public String getEdificio_org() {
        return edificio_org;
    }

    public void setEdificio_org(String edificio_org) {
        this.edificio_org = edificio_org;
    }

    public String getEdificio_fmt() {
        return edificio_fmt;
    }

    public void setEdificio_fmt(String edificio_fmt) {
        this.edificio_fmt = edificio_fmt;
    }

    public String getDia_org() {
        return dia_org;
    }

    public void setDia_org(String dia_org) {
        this.dia_org = dia_org;
    }

    public String getDia_fmt() {
        return dia_fmt;
    }

    public void setDia_fmt(String dia_fmt) {
        this.dia_fmt = dia_fmt;
    }

    public String getPiso_org() {
        return piso_org;
    }

    public void setPiso_org(String piso_org) {
        this.piso_org = piso_org;
    }

    public String getPiso_fmt() {
        return piso_fmt;
    }

    public void setPiso_fmt(String piso_fmt) {
        this.piso_fmt = piso_fmt;
    }

    public String getNo_salon_org() {
        return no_salon_org;
    }

    public void setNo_salon_org(String no_salon_org) {
        this.no_salon_org = no_salon_org;
    }

    public String getNo_salon_fmt() {
        return no_salon_fmt;
    }

    public void setNo_salon_fmt(String no_salon_fmt) {
        this.no_salon_fmt = no_salon_fmt;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
}
