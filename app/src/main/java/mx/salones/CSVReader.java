package mx.salones;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CSVReader {
    private final Context ctx;
    private SimpleDateFormat actual_time = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public CSVReader(Context mctx){
        this.ctx = mctx;
    }

    public ArrayList<Salon> readCSV(){
        ArrayList<Salon> dataFromFile = new ArrayList<>();
        final Resources res_data =  this.ctx.getResources();
        InputStream is = res_data.openRawResource(R.raw.dataset);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] line_content = line.split(",");
                dataFromFile.add(new Salon(line_content[0], line_content[1], line_content[2]));
            }
        }catch(IOException ex){
            System.out.println("Ha ocurrido un error al leer el archivo: " + ex);
        } finally {
            try {
                reader.close();
            }catch (IOException close_err){
                System.out.println("Ha ocurrido un error al tratar de cerrar el archiv: " + close_err);
            }
        }

        return dataFromFile;
    }

    public ArrayList<Salon> filterData(ArrayList<Salon> data, String edificio, String dia, String hora) throws ParseException {
        Date fmt_filter_time = actual_time.parse(hora);
        ArrayList<Salon> filteredData = new ArrayList<>();
        for (Salon c : data){
            Date fmt_salon_inicio_time = actual_time.parse(c.getHora_inicio());
            Date fmt_salon_fin_time = actual_time.parse(c.getHora_fin());
            if (c.getDia_fmt().toLowerCase(Locale.ROOT).equals(dia.toLowerCase(Locale.ROOT))
                    && c.getEdificio_org().equals(edificio)
                    && fmt_filter_time.after(fmt_salon_inicio_time)
                    && fmt_salon_fin_time.after(fmt_filter_time)){
                filteredData.add(c);
            }
        }
        return filteredData;
    }

}
