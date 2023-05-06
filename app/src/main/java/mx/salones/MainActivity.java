package mx.salones;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private CSVReader csvReader;
    private ArrayList<Salon> salones = new ArrayList<>();
    private SimpleDateFormat day_of_week_name = new SimpleDateFormat("EEEE", new Locale("es", "MX"));
    private SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private SimpleDateFormat actual_time = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private Date currentTime;
    private ConstraintLayout ly_Help, ly_Main;
    private FloatingActionButton fab_ayuda;
    private boolean isHelpLayVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView lbl_actual_date = findViewById(R.id.lbl_actual_date);
        RecyclerView rcv_edificio_1 = findViewById(R.id.rcv_edif_1);
        RecyclerView rcv_edificio_2 = findViewById(R.id.rcv_edif_2);
        RecyclerView rcv_edificio_3 = findViewById(R.id.rcv_edif_3);
        fab_ayuda = findViewById(R.id.fab_info);
        ly_Help = findViewById(R.id.ly_help);
        ly_Main = findViewById(R.id.ly_main_container);

        csvReader = new CSVReader(this.getApplicationContext());
        salones = csvReader.readCSV();
        currentTime = Calendar.getInstance().getTime();
        String day_name = day_of_week_name.format(currentTime);
        String fmt_time = actual_time.format(currentTime);

        //day_name = "Jueves";
        //fmt_time = "12:30";

        lbl_actual_date.setText(getResources()
                .getString(R.string.holder_lbl_actual_date,
                        day_name.substring(0,1).toUpperCase() + day_name.substring(1).toLowerCase(),
                        date_format.format(currentTime)));

        ArrayList<Salon> e1 = null;
        ArrayList<Salon> e2 = null;
        ArrayList<Salon> e3 = null;

        fab_ayuda.setOnClickListener(v -> {
            toogleHelpPanel(!isHelpLayVisible);
        });

        try {
            e1 = csvReader.filterData(salones, "1", day_name, fmt_time);
            e2 = csvReader.filterData(salones, "2", day_name, fmt_time);
            e3 = csvReader.filterData(salones, "3", day_name, fmt_time);
        } catch (ParseException e) {
            System.out.println("Ha ocurrido un error al filtrar los datos: " + e);
            Toast.makeText(this, "Ha ocurrido un error al obtener la lista de datos", Toast.LENGTH_SHORT).show();
        }

        if (e1 != null && !e1.isEmpty()){
            rcv_edificio_1.setVisibility(View.VISIBLE);
            SalonAdapter rcv_e1 = new SalonAdapter(e1, getApplicationContext());
            rcv_edificio_1.setLayoutManager(new LinearLayoutManager(this));
            rcv_edificio_1.setAdapter(rcv_e1);
        }else{
            rcv_edificio_1.setVisibility(View.GONE);
        }

        if (e2 != null && !e2.isEmpty()){
            rcv_edificio_2.setVisibility(View.VISIBLE);
            SalonAdapter rcv_e2 = new SalonAdapter(e2, getApplicationContext());
            rcv_edificio_2.setLayoutManager(new LinearLayoutManager(this));
            rcv_edificio_2.setAdapter(rcv_e2);
        }else{
            rcv_edificio_2.setVisibility(View.GONE);
        }

        if (e3 != null && !e3.isEmpty()){
            rcv_edificio_3.setVisibility(View.VISIBLE);
            SalonAdapter rcv_e3 = new SalonAdapter(e3, getApplicationContext());
            rcv_edificio_3.setLayoutManager(new LinearLayoutManager(this));
            rcv_edificio_3.setAdapter(rcv_e3);
        }else{
            rcv_edificio_3.setVisibility(View.GONE);
        }
    }

    private void toogleHelpPanel(boolean show) {
        Transition transition = new Slide(Gravity.START);
        transition.setDuration(600);
        transition.addTarget(R.id.ly_help);
        TransitionManager.beginDelayedTransition(ly_Main, transition);
        ly_Help.setVisibility( !show ? View.VISIBLE : View.GONE );
        ly_Help.bringToFront();
        isHelpLayVisible = !isHelpLayVisible;
    }
}