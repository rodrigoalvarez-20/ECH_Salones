package mx.salones;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;

public class ViewDialog {

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.popup_layout);

        Button dialogButton = dialog.findViewById(R.id.btnClosePopup);
        dialogButton.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

}
