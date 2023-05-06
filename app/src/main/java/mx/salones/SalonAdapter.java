package mx.salones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.ViewHolder> {

    private ArrayList<Salon> salonesList;
    private Context ctx;

    public SalonAdapter(ArrayList<Salon> data, Context ctx){
        this.salonesList = data;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.rcv_item_info, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Salon actualSalon = this.salonesList.get(position);
        holder.lbl_edificio.setText(actualSalon.getEdificio_fmt());
        holder.lbl_horario.setText(
                this.ctx.getResources().getString(
                        R.string.holder_lbl_actual_date,
                        actualSalon.getHora_inicio(),
                        actualSalon.getHora_fin()
                )
        );
        holder.lbl_no_salon.setText(
                this.ctx.getResources().getString(
                        R.string.rcv_lbl_no_salon,
                        actualSalon.getNo_salon_org()
                )
        );
        holder.lbl_piso.setText(actualSalon.getPiso_fmt());
    }


    @Override
    public int getItemCount() {
        return this.salonesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lbl_no_salon;
        public TextView lbl_horario;
        public TextView lbl_edificio;
        public TextView lbl_piso;
        public ViewHolder(View itemView) {
            super(itemView);
            lbl_no_salon = itemView.findViewById(R.id.rcv_lbl_no_salon);
            lbl_horario = itemView.findViewById(R.id.rcv_lbl_horario);
            lbl_edificio = itemView.findViewById(R.id.rcv_lbl_edificio);
            lbl_piso = itemView.findViewById(R.id.rcv_lbl_piso);
        }
    }


}
