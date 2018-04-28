package com.example.webeleven.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webeleven.R;
import com.example.webeleven.model.Music;
import com.squareup.picasso.Picasso;

public class DlgDetalhesMusic extends AppCompatDialogFragment implements View.OnClickListener {
    private LinearLayout lnlFechar = null;
    private ImageView imgDetalhe = null;
    private TextView lblTitulo = null;
    private TextView lblGrupo = null;
    private TextView lblType = null;
    private TextView lblGenre = null;
    private TextView lblPrice = null;
    private Button cmdComprar = null;

    public static Music music = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dlg_detalhes_resultado, null);
        builder.setView(view);
        
        lnlFechar = view.findViewById(R.id.lnlFechar);
        imgDetalhe = view.findViewById(R.id.imgDetalhe);
        lblTitulo = view.findViewById(R.id.lblTitulo);
        lblGrupo = view.findViewById(R.id.lblGrupo);
        lblType = view.findViewById(R.id.lblType);
        lblGenre = view.findViewById(R.id.lblGenre);
        lblPrice = view.findViewById(R.id.lblPrice);
        cmdComprar = view.findViewById(R.id.cmdComprar);

        lnlFechar.setOnClickListener(this);
        cmdComprar.setOnClickListener(this);

        carregaDados();

        return builder.create();
    }

    public void carregaDados() {
        if(music != null) {
            Picasso.get().load(music.getArtworkUrl100()).into(imgDetalhe);
            lblTitulo.setText(music.getTrackName());
            lblGrupo.setText(music.getArtistName());
            lblType.setText(music.getKind());
            lblGenre.setText(music.getPrimaryGenreName());
            lblPrice.setText(String.format("R$ %.2f", Double.parseDouble(music.getTrackPrice())));
        }
    }

    @Override
    public void onClick(View v) {
        try
        {
            switch (v.getId())
            {
                case R.id.cmdComprar:
                {
                    break;
                }
                case R.id.lnlFechar:
                {
                    dismiss();
                    break;
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
